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
            <ul>
              <c:forEach var="error" items="${errors}">
                <ul>${error}</ul>
              </c:forEach>
            </ul>
          </div>
        </c:if>

        <table class="table table-striped">
          <thead>
            <tr>
              <th>Title</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${items}">
              <tr>
                <td>${item.title}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <form action="index" method="POST" class="form-inline">
          <div class="form-group">
            <input type="text" class="form-control" id="title" name="title" placeholder="Title">
          </div>
          <button type="submit" class="btn btn-default">Create</button>
        </form>

      </div>

    </div>
  </body>
</html>