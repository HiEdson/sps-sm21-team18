package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Topic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;

//get all topic from db
@WebServlet("/list-topics")

public final class TopicListServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("Topic").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Topic> allTopics = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();
      long id = entity.getKey().getId();
      String name = entity.getString("name");
      String description = entity.getString("description");
      String resources = entity.getString("resources");
      long timestamp = entity.getLong("timestamp");
      String formattedDate = sdf.format(new Date(timestamp));
      Topic oneTopic = new Topic(id, name, description, resources, formattedDate);
      allTopics.add(oneTopic);
    }
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(allTopics));
    System.out.println(gson.toJson(allTopics));
  }
}
