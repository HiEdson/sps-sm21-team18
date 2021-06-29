package com.google.sps.data;
public final class Topic {

  private final long id;
  private final String name;
  private final String description;
  private final long timestamp;

  public Topic(long id, String name,String description, long timestamp) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.timestamp = timestamp;
  }
}
