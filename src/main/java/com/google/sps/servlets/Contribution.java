package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

//save new topic in DB
@WebServlet("/contribution")
public class Contribution extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String firstname = Jsoup.clean(request.getParameter("firstname"), Whitelist.none());
    String type = Jsoup.clean(request.getParameter("type"), Whitelist.none());
    String subject = Jsoup.clean(request.getParameter("subject"), Whitelist.none());
    String topicName = Jsoup.clean(request.getParameter("redirectName"), Whitelist.none());
    String postId = request.getParameter("postId");
    System.out.println(firstname + " from contribution " + type + " " + subject + " " + postId);
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contribution");
    FullEntity contributionEntity = Entity.newBuilder(keyFactory.newKey())
    .set("firstname", firstname)
    .set("type", type)
    .set("subject", subject)
    .set("postId", postId)
    .set("timestamp", timestamp)
    .build();
    datastore.put(contributionEntity);

    // System.out.println("Until here, just fine");
    response.sendRedirect("/topic.html?name="+topicName);
  }
}
