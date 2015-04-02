package de.chkal.todo.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TodoService {

  private final List<TodoItem> items = new ArrayList<>();

  @PostConstruct
  public void init() {
    items.add(new TodoItem("Create MVC sample app"));
    items.add(new TodoItem("Create blog post"));
  }

  public List<TodoItem> getItems() {
    return Collections.unmodifiableList(items);
  }

}
