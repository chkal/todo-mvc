package de.chkal.todo.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@ApplicationScoped
public class TodoService {

  private final List<TodoItem> items = new ArrayList<>();

  private long sequence = 1;

  @PostConstruct
  public void init() {
    createItem("Create MVC sample app", TodoItem.Priority.HIGH);
    createItem("Write a blog post about it", TodoItem.Priority.MEDIUM);
  }

  public List<TodoItem> getItems() {
    return Collections.unmodifiableList(items);
  }

  public TodoItem createItem(String title, TodoItem.Priority priority) {
    TodoItem item = new TodoItem(sequence++, title, priority);
    items.add(item);
    return item;
  }

  public void deleteItem(long id) {
    Iterator<TodoItem> it = items.iterator();
    while (it.hasNext()) {
      TodoItem item = it.next();
      if (item.getId() == id) {
        it.remove();
      }
    }
  }

}
