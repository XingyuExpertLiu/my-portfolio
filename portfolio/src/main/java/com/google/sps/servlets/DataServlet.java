// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class Comment {
//   public long id;
  public String nickname;
  public String content;

  public Comment(String _nickname, String _content)
  {
    // id = _id;
    nickname = _nickname;
    content = _content;
  }
}

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  int comID = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // strList.add("Hello XingyuLiu!");
    // strList.add("0 error(s)!");
    // strList.add("0 warning(s)!");
    ArrayList<Comment> comList = new ArrayList<>();
    ArrayList<String> strList = new ArrayList<>();

    Query query = new Query("Comment").addSort("ID", SortDirection.DESCENDING);;
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
      // long id = (long) entity.getProperty("ID");
      String nickname = (String) entity.getProperty("nickname");
      String content = (String) entity.getProperty("content");

      Comment comment = new Comment(nickname, content);
      // comList.add(comment);

      strList.add(comment.nickname);
      strList.add(comment.content);
    }
    
    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(strList));

    // String json = convertToJsonUsingGson(strList);

    // response.setContentType("text/html;");
    // response.getWriter().println("Hello XingyuLiu!");
    // response.setContentType("application/json;");
    // response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
    // Get the input from the form.
    long ID = ++comID;
    String nickname = getNickname(request);
    String content = getContent(request);

    // add it to strList.
    // strList.add(comment);

    Entity taskEntity = new Entity("Comment");
    taskEntity.setProperty("ID", ID);
    taskEntity.setProperty("nickname", nickname);
    taskEntity.setProperty("content", content);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(taskEntity);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }

  // private String convertToJsonUsingGson(ArrayList<String> strList) {
  //   Gson gson = new Gson();
  //   String json = gson.toJson(strList);
  //   return json;
  // }
  
  private String getNickname(HttpServletRequest request) {
    String nickname = request.getParameter("nickname");
    return nickname;
  }

  private String getContent(HttpServletRequest request) {
    String content = request.getParameter("content");
    return content;
  }
}
