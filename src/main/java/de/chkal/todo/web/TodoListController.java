package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.validation.ValidationResult;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple MVC controller.
 */
@Path("/items")
public class TodoListController {

  @Inject
  private Models models;

  @Inject
  private TodoService todoService;

  @Inject
  private ValidationResult validationResult;

  @GET
  @Controller
  public String listItems() {
    models.put("items", todoService.getItems());
    return "items.jsp";
  }

  @POST
  @Path("/create")
  @Controller
  public String createItem(@BeanParam @Valid CreateItemForm form) {

    if (validationResult.isFailed()) {

      List<String> errors = validationResult.getAllViolations().stream()
          .map(v -> v.getMessage())
          .collect(Collectors.toList());

      models.put("errors", errors);
      return listItems();

    }

    TodoItem newItem = todoService.createItem(form.getTitle());

    /*
     * Actually this method should use the POST-Redirect-GET pattern here.
     * The API for this is yet to be defined. And there should also be
     * some kind of flash scope, to be able to persist messages.
     *
     * https://java.net/jira/browse/MVC_SPEC-31
     */
    models.put("message", "Item created: " + newItem.getTitle());
    return listItems();

  }

  @POST
  @Path("/delete")
  @Controller
  public String deleteItem(@FormParam("id") long id) {

    todoService.deleteItem(id);

    // An Ozark specific way of doing redirects. Should this go into the spec?
    return "redirect:/items";

  }

}
