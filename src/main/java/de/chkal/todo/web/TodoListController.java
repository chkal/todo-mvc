package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;
import de.chkal.todo.service.TodoService;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * A simple MVC controller.
 */
@Path("/items")
@Controller
public class TodoListController {

  @Inject
  private Models models;

  @Inject
  private BindingResult bindingResult;

  @Inject
  private Messages messages;

  @Inject
  private TodoService todoService;

  /**
   * Executed when the user navigates to the page. The method uses MVC's Models
   * class to populates the model with all data required by the view.
   */
  @GET
  public String listItems() {
    models.put("items", todoService.getItems());
    return "items.jsp";
  }

  /**
   * Handles data submitted by the "create item" form. The form data is validated
   * using Bean Validation annotations. The method uses MVC's BindingResult class
   * to access the detected binding errors and constraint violations. If errors
   * were found, they are added to the custom Messages class which is used by
   * the view for rendering.
   */
  @POST
  @Path("/create")
  @ValidateOnExecution(type = ExecutableType.NONE)
  public String createItem(@BeanParam @Valid CreateItemForm form) {

    if (bindingResult.isFailed()) {

      // store errors to display them in the view
      messages.addErrors(bindingResult.getAllMessages());

      // The inputs should be populated with the previously submitted invalid values
      models.put("form", form);

      // reuse the listItems() controller method to prepare the model for rendering
      return listItems();

    }

    TodoItem newItem = todoService.createItem(form.getTitle(), form.getPriority(),
        form.getDueDate());

    /*
     * Redirect the user after that so that pressing F5 in the browser
     * doesn't trigger the form post again (POST-Redirect-GET pattern).
     */
    messages.setInfo("Item created: " + newItem.getTitle());
    return "redirect:/items";

  }

  /**
   * Handles deletion of items. Works basically the same as the createItem()
   * method. The method doesn't use a separate from class, because there
   * is only a single hidden field representing the id of the item.
   */
  @POST
  @Path("/delete")
  public String deleteItem(@FormParam("id") long id) {

    todoService.deleteItem(id);

    return "redirect:/items";

  }

}
