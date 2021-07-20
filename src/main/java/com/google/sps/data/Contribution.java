package com.google.sps.data;
public final class Contribution {

  private final long id;
  private final String firstname;
  private final String type;
  private final String subject;
  private final String postId;
  private final String date;

  public Contribution(long id, String firstname,String type, String subject, String postId, String date) {
    this.id = id;
    this.firstname = firstname;
    this.type = type;
    this.subject = subject;
    this.postId = postId;
    this.date = date;
  }
}