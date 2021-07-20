package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.gson.Gson;
import com.google.sps.data.Contribution;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import java.text.SimpleDateFormat;
import java.util.Date;

//get all topic from db
@WebServlet("/getcontrib")

public final class getContrib extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
    String postIdSearch = Jsoup.clean(request.getParameter("postId"), Whitelist.none());
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contribution").setFilter(PropertyFilter.eq("postId", postIdSearch))
    .setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);
    
    List<Contribution> allContrib = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();
      long id = entity.getKey().getId();
      String firstname = entity.getString("firstname");
      String type = entity.getString("type");
      String subject = entity.getString("subject");
      String postId = entity.getString("postId");
      long timestamp = entity.getLong("timestamp");
      String formattedDate = sdf.format(new Date(timestamp));

      Contribution oneContrib = new Contribution(id, firstname, type, subject, postId, formattedDate);
      allContrib.add(oneContrib);
    }

    Gson gson = new Gson();
    response.setContentType("application/json;");
     System.out.println(gson.toJson(allContrib));
    response.getWriter().println(gson.toJson(allContrib));
  }
}
