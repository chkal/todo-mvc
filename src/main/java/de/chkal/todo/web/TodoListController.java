package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.validation.ValidationResult;
import javax.validation.Valid;
import javax.ws.rs.*;

/**
 * A simple MVC controller.
 */
@Path("/items")
public class TodoListController {

  @Inject
  private Models models;

  @Inject
  private ValidationResult validationResult;

  @Inject
  private Messages messages;

  @Inject
  private TodoService todoService;

  /**
   * Executed when the user navigates to the page. The method uses MVC's Models
   * class to populates the model with all data required by the view.
   */
  @GET
  @Controller
  public String listItems() {
    models.put("items", todoService.getItems());
    return "items.jsp";
  }

  /**
   * Handles data submitted by the "create item" form. The form data is validated
   * using Bean Validation annotations. The method uses MVC's ValidationResult class
   * to access the detected violations. If violations were found, they are added
   * to the custom Messages class which is used by the view for rendering.
   */
  @POST
  @Path("/create")
  @Controller
  public String createItem(@BeanParam @Valid CreateItemForm form) {

    if (validationResult.isFailed()) {

      validationResult.getAllViolations().stream()
          .map(violation -> violation.getMessage())
          .forEach(message -> messages.addError(message));

      // reuse the listItems() controller method to prepare the model for rendering
      return listItems();

    }

    TodoItem newItem = todoService.createItem(form.getTitle());

    /*
     * Actually this method should use the POST-Redirect-GET pattern here.
     * The API for this has still to be defined. And there should also be
     * some kind of flash scope to be able to persist messages accross
     * redirects.
     *
     * https://java.net/jira/browse/MVC_SPEC-31
     */
    messages.setInfo("Item created: " + newItem.getTitle());
    return listItems();

  }

  /**
   * Handles deletion of items. Works basically the same as the createItem()
   * method. The method doesn't use a separate from class, because there
   * is only a single hidden field representing the id of the item.
   */
  @POST
  @Path("/delete")
  @Controller
  public String deleteItem(@FormParam("id") long id) {

    todoService.deleteItem(id);

    /*
     * An Ozark specific way of doing redirects. Should this go into the spec?
     * Redirecting here is fine as the method doesn't need to render any
     * messages for the user which would get lost during the redirect.
     */
    return "redirect:/items";

  }

}
