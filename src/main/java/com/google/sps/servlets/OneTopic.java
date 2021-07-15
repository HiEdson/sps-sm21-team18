package com.google.sps.servlets;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;
import com.google.sps.data.Topic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import java.util.Date;

//get a single topic from db
@WebServlet("/one-topic")

public final class OneTopic extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // String topic = request.getParameter("name");
        String topic = Jsoup.clean(request.getParameter("name"), Whitelist.none());
        System.out.println("from server topic:" + topic);

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        // search it based on it's name
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Topic").setFilter(PropertyFilter.eq("name", topic))
                .setOrderBy(OrderBy.desc("timestamp")).build();
        QueryResults<Entity> results = datastore.run(query);

        Gson gson = new Gson();
        List<Topic> singleTopic = new ArrayList<>();
        // check if the name exists or not. for test purpose I'm using it, it probably
        // won't be necessary
        if (results.hasNext() == false) {
            response.setContentType("text/html;");
            PrintWriter out = response.getWriter();
            out.println("<h1 style=\"text-align:center; margin-top:10px;\"> 404 : Ups, topic not "
                    + "found, search for a valid topic or <a href=\'/index.html\'>create a new one</a></h1>");
        } else {
            // if so, display it in a new page
            //while (results.hasNext()) {
                Entity entity = results.next();
                long id = entity.getKey().getId();
                String name = entity.getString("name");
                String description = entity.getString("description");
                // display the timestamp
                long timestamp = entity.getLong("timestamp");
                Topic oneTopic = new Topic(id, name, description, timestamp);
                singleTopic.add(oneTopic);
            //}

        }
        System.out.println(gson.toJson(singleTopic));
        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(singleTopic));
        //response.sendRedirect("/topic.html?name=" + topic);

    }
}

/**
 * response.setContentType("text/html;"); PrintWriter out =
 * response.getWriter(); out.println("<h1 style=\"text-align:center; color:red;
 * margin-top:10px;\">" + name + "</h1>"); out.println("<div
 * style=\"padding:1%;\">" + "<div>" + "<img
 * src=\'https://gundem.emu.edu.tr/en/wp-content/uploads/2020/03/banner.png\'" +
 * "style=\"text-align:center; display: block; margin-left: auto; margin-right:
 * auto;" + "width:100%;\">" + "</div>" +
 * 
 * "<div style=\"text-align:justify; width:100%; font-size:20px;\">" + "
 * <p >
 * " + description + "
 * </p>
 * " + "</div>" +
 * 
 * "</div>"); out.println("<h1 style=\"text-align:center; margin-top:10px;\"><a
 * href=\'/index.html\'>Go back</a></h1>");
 */