<%-- 
    Document   : invalidLogin
    Created on : Jun 30, 2025, 7:58:30 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
     </head>
     <body>
        <h1>Create Account Page</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ACCOUNT}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 12 chars)<br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font>
            </c:if>
            <c:if test="${not empty errors.userNameisExisted}">
                <font color="red">
                ${errors.userNameisExisted}
                </font>
            </c:if>
            <br>
            Password* <input type="password" name="txtPassword" value="" />(8 - 20 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font>
            </c:if>
            <br>
            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatches}">
                <font color="red">
                ${errors.confirmNotMatches}
                </font>
            </c:if>
            <br>
            Full name* <input type="text" name="txtFullName" value="${param.txtFullName}" />(2 - 40 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthErr}">
                <font color="red">
                ${errors.fullNameLengthErr}
                </font>
            </c:if>
            <br>
            <input type="submit" name="btAction" value="create" />
            <input type="reset" name="Reset"/>
        </form>
        <a href="Login.html">Click to back</a>
    </body>
</html>