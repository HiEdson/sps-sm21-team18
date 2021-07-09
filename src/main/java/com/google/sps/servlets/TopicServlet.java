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
@WebServlet("/new-topic")
public class TopicServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String description = Jsoup.clean(request.getParameter("description"), Whitelist.none());
    //String user = get the current logged in user! 
    long timestamp = System.currentTimeMillis();


    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Topic");
    FullEntity topicEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("description", description)
            //.set("user", blablabla)
            .set("timestamp", timestamp)
            .build();
    datastore.put(topicEntity);

    //System.out.println("Until here, just fine");
    response.sendRedirect("/index.html");
    
  }
}

