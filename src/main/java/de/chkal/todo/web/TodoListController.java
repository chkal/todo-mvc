package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.mvc.validation.ValidationResult;
import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple MVC controller.
 */
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
  public void createItem(@BeanParam @Valid CreateItemForm form) {

    if (validationResult.isFailed()) {

      List<String> errors = validationResult.getAllViolations().stream()
          .map(v -> v.getMessage())
          .collect(Collectors.toList());

      prepareModel();
      models.put("errors", errors);
      return;

    }

    TodoItem newItem = todoService.createItem(form.getTitle());

    prepareModel();
    models.put("message", "Item created: " + newItem.getTitle());

    /*
     * Actually this method should use the POST-Redirect-GET pattern here.
     * The API for this is yet to be defined.
     *
     * https://java.net/jira/browse/MVC_SPEC-31
     */

  }

  @POST
  @Path("/delete")
  @Controller
  public String deleteItem(@FormParam("id") long id) {

    todoService.deleteItem(id);

    /*
     * An Ozark specific way of doing redirects.
     */
    return "redirect:/index";

  }

}
