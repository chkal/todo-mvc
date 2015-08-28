<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>TODO MVC</title>

    <script src="${mvc.contextPath}/webjars/jquery/1.11.0/dist/jquery.js"></script>

    <link href="${mvc.contextPath}/webjars/bootstrap/3.3.4/dist/css/bootstrap.css" rel="stylesheet">
    <script src="${mvc.contextPath}/webjars/bootstrap/3.3.4/dist/js/bootstrap.js"></script>

    <link href="${mvc.contextPath}/webjars/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.css" rel="stylesheet">
    <script src="${mvc.contextPath}/webjars/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.js"></script>

  </head>

  <body>

    <div class="container">

      <h1>TODO List</h1>

      <!-- In real world application this should go into a tag file -->
      <c:if test="${messages.info != null}">
        <div class="alert alert-success" role="alert">
          ${mvc.encoders.html(messages.info)}
        </div>
      </c:if>
      <c:if test="${not empty messages.errors}">
        <div class="alert alert-danger" role="alert">
          <ul class="list-unstyled">
            <c:forEach var="error" items="${messages.errors}">
              <li>${mvc.encoders.html(error)}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <table class="table table-striped">
        <colgroup>
          <col style="width: 40%;" />
          <col style="width: 25%;" />
          <col style="width: 20%;" />
          <col style="width: 15%;" />
        </colgroup>
        <thead>
          <tr>
            <th class="text-left">Title</th>
            <th class="text-center">Priority</th>
            <th class="text-center">Due Date</th>
            <th class="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${items}">
            <tr>
              <td class="text-left">
                <!-- For security reasons text should be escaped -->
                ${mvc.encoders.html(item.title)}
              </td>
              <td class="text-center">
                  ${item.priority}
              </td>
              <td class="text-center">
                  ${item.dueDate}
              </td>
              <td class="text-center">
                <form action="${mvc.contextPath}${mvc.applicationPath}/items/delete" method="POST">
                  <input type="hidden" name="id" value="${item.id}"/>
                  <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>
                  <button type="submit" class="btn btn-danger">
                    Delete
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <form action="${mvc.contextPath}${mvc.applicationPath}/items/create"
            method="POST" class="form-inline">

        <div class="form-group">
          <input type="text" class="form-control" id="title" name="title" placeholder="Title"
              value="${createItemForm.title}" autofocus>
        </div>

        <div class="form-group">
          <select name="priority" class="form-control" title="Priority">
            <option value="">- Priority -</option>
            <option value="LOW">LOW</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="HIGH">HIGH</option>
          </select>
        </div>

        <div class="form-group">
          <input type="text" id="duedate" name="duedate" class="form-control" placeholder="Due date">
          <script type="application/javascript">
            $(function () {
              $('#duedate').datepicker({
                orientation: "top auto",
                format: "yyyy-mm-dd",
                clearBtn: true,
                autoclose: true
              });
            });
          </script>
        </div>

        <!-- CSRF protection is enabled in "TodoApplication", so we need to submit the token like this -->
        <input type="hidden" name="${mvc.csrf.name}" value="${mvc.csrf.token}"/>

        <button type="submit" class="btn btn-primary">Create</button>

      </form>

    </div>

  </body>
</html>