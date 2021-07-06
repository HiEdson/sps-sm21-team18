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
//get all topic from db
@WebServlet("/list-topics")

public final class TopicListServlet extends HttpServlet {   
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Topic").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Topic> allTopics = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();
      //Entity task = datastore.get();
      //System.out.println(task);

      long id = entity.getKey().getId();
      String name = entity.getString("name");
      String description = entity.getString("description");
      long timestamp = entity.getLong("timestamp");

      Topic oneTopic = new Topic(id, name, description, timestamp);
      allTopics.add(oneTopic);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(allTopics));
    }
}
