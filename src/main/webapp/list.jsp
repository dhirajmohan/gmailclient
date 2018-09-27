<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>

<body>
    <html>
    <head>
    <style>
    body {
        background-color:pink;
    }
    
    h1 {
        color:burlywood;
        text-align: center;
    }
    
    p {
        font-family: verdana;
        font-size: 20px;
    }
    </style>
    </head>
   
    <center>

    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption>
                <h2>List</h2>
            </caption>
            <tr>
               
                <th>username</th>
                <th>password</th>
            </tr>

            <c:forEach var="log" items="${login}">
                <tr>
                    <td>
                        <c:out value="${log.username}" />
                    </td>
                    <td>
                        <c:out value="${log.password}" />
                    </td>
                   
                    <td><a href="/user?action=delete&username=<c:out value="${log.username}"/>">delete book</a></td>
                    <td><a href="/user?action=edit&username=<c:out value="${log.username}"/>">update book</a></td>
                </tr>
            </c:forEach>
            
        </table>
    
        <a href="login.jsp">add new book</a>
    </div>
</body>

</html>