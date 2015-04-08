package de.chkal.todo.service;

/**
 * The model object representing a single item.
 * Could be an JPA entity in real world applications.
 */
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
