package de.chkal.todo.web;

import de.chkal.todo.service.TodoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/index")
@RequestScoped
public class TodoListController {

  @Inject
  private Models models;

  @Inject
  private TodoService todoService;

  @GET
  @Controller
  public String index() {
    models.put("items", todoService.getItems());
    return "list.jsp";
  }

}
