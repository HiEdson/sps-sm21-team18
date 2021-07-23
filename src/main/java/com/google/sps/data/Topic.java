package com.google.sps.data;
public final class Topic {

  private final long id;
  private final String name;
  private final String description;
  private final String resources;
  private final String date;

  public Topic(long id, String name,String description,String resources, String date) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.resources = resources;
    this.date = date;
  }
}
