package de.chkal.todo.service;

public class TodoItem {

  private String title;

  public TodoItem() {
    // default constructor
  }

  public TodoItem(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
