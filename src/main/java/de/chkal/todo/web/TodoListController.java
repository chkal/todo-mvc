package de.chkal.todo.web;

import com.oracle.ozark.validation.ValidationResult;
import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;
import java.util.stream.Collectors;

@Path("/index")
@RequestScoped
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
  public void populateModel() {
    models.put("items", todoService.getItems());
  }

  @POST
  @Controller
  public void createItem(@BeanParam NewTodoItemForm form) {

    if (validationResult.isFailed()) {

      List<String> errors = validationResult.getAllViolations().stream()
          .map(v -> v.getPropertyPath() + ": " + v.getMessage())
          .collect(Collectors.toList());

      populateModel();
      models.put("errors", errors);
      return;

    }

    TodoItem newItem = todoService.createItem(form.getTitle());

    populateModel();
    models.put("message", "Item created: " + newItem.getTitle());

  }

}
