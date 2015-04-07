package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.validation.ValidationResult;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("/index")
@View("list.jsp")
public class TodoListController {

  @Inject
  private Models models;

  @Inject
  private TodoService todoService;

  @Inject
  private ValidationResult validationResult;

  @GET
  @Controller
  public void prepareModel() {
    models.put("items", todoService.getItems());
  }

  @POST
  @Controller
  public void createItem(@BeanParam @Valid NewTodoItemForm form) {

    if (validationResult.isFailed()) {

      List<String> errors = validationResult.getAllViolations().stream()
          .map(v -> v.getPropertyPath() + ": " + v.getMessage())
          .collect(Collectors.toList());

      prepareModel();
      models.put("errors", errors);
      return;

    }

    TodoItem newItem = todoService.createItem(form.getTitle());

    prepareModel();
    models.put("message", "Item created: " + newItem.getTitle());

  }

}
