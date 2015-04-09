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

      <div class="starter-template">
        <h1>TODO List</h1>

        <c:if test="${message != null}">
          <div class="alert alert-success" role="alert">
            ${message}
          </div>
        </c:if>

        <c:if test="${not empty errors}">
          <div class="alert alert-danger" role="alert">
            <ul class="list-unstyled">
              <c:forEach var="error" items="${errors}">
                <li>${error}</li>
              </c:forEach>
            </ul>
          </div>
        </c:if>

        <table class="table table-striped">
          <colgroup>
            <col style="width: 70%;" />
            <col style="width: 30%;" />
          </colgroup>
          <thead>
            <tr>
              <th class="text-left">Title</th>
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
            <input type="text" class="form-control" id="title" name="title" placeholder="Title">
          </div>
          <button type="submit" class="btn btn-default">Create</button>
        </form>

      </div>

    </div>
  </body>
</html>