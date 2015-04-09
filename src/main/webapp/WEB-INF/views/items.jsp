<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>TODO MVC</title>

    <link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/dist/css/bootstrap.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/webjars/jquery/1.11.0/dist/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.4/dist/js/bootstrap.js"></script>

  </head>

  <body>

    <div class="container">

      <h1>TODO List</h1>

      <!-- In real world application this should go into a tag file -->
      <c:if test="${messages.info != null}">
        <div class="alert alert-success" role="alert">
          ${messages.info}
        </div>
      </c:if>
      <c:if test="${not empty messages.errors}">
        <div class="alert alert-danger" role="alert">
          <ul class="list-unstyled">
            <c:forEach var="error" items="${messages.errors}">
              <li>${error}</li>
            </c:forEach>
          </ul>
        </div>
      </c:if>

      <table class="table table-striped">
        <colgroup>
          <col style="width: 40%;" />
          <col style="width: 30%;" />
          <col style="width: 30%;" />
        </colgroup>
        <thead>
          <tr>
            <th class="text-left">Title</th>
            <th class="text-center">Priority</th>
            <th class="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="item" items="${items}">
            <tr>
              <td class="text-left">
                ${item.title}
              </td>
              <td class="text-center">
                  ${item.priority}
              </td>
              <td class="text-center">
                <form action="${pageContext.request.contextPath}/r/items/delete" method="POST">
                  <input type="hidden" name="id" value="${item.id}"/>
                  <button type="submit" class="btn btn-danger">
                    Delete
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <form action="${pageContext.request.contextPath}/r/items/create" method="POST" class="form-inline">
        <div class="form-group">
          <input type="text" class="form-control" id="title" name="title" placeholder="Title"
              value="${createItemForm.title}" autofocus>
        </div>
        <div class="form-group">
          <select name="priority" class="form-control">
            <option value="">- Priority -</option>
            <option value="LOW">LOW</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="HIGH">HIGH</option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
      </form>

    </div>

  </body>
</html>