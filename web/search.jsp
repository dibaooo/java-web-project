<%-- 

    Document   : search
    Created on : Jun 12, 2025, 7:46:51 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="java.util.List" %>
<%@page import="baovd.registration.RegistrationDTO" %> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <!--        <font color="red">
                Welcome,
                </font>     -->
        <c:if test="${not empty sessionScope.USER_INFO}">
            <font color="red">Welcome, ${sessionScope.USER_INFO.fullName}</font>
        </c:if>

        <h1>Search Page</h1>

        <form action="DispatchServlet" method="GET">
            Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="btAction" value="search" />
        </form>
        <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" />
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>${counter.count}.</td>
                                <td>${dto.username}</td>
                                <td>
                                    <form action="DispatchServlet" method="POST">
                                        <input type="hidden" name="txtUsername" value="${dto.username}" />
                                        <input type="hidden" name="txtFullname" value="${dto.fullName}" />
                                        <input type="text" name="txtPassword" value="${dto.password}" />
                                        <br/>
                                        <c:set var="errorsMap" value="${requestScope.UPDATE_ACCOUNT}" />
                                        <c:set var="errors" value="${errorsMap[dto.username]}" />
                                        <c:if test="${not empty errors.passwordLengthErr}">
                                            <font color="red">${errors.passwordLengthErr}</font>
                                        </c:if>
                                </td>
                                <td>${dto.fullName}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                        ${dto.role ? 'checked="checked"' : ''} />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btAction" value="delete" />
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                    <input type="submit" name="btAction" value="update" />
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty result}">
                <h2><font color="red">No record is matched!!!</font></h2>
            </c:if>
        </c:if>


        <%--  <form action="DispatchServlet">
           Search Value <input type ="text" name="txtSearchValue" 
                               value="<%= request.getParameter("txtSearchValue") %>" /><br/>
           <input type="submit" name="btAction" value="search" />
            
       </form><br/>
      
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null){
                List<RegistrationDTO> result =
                    (List<RegistrationDTO>)request.getAttribute("SEARCH_RESULT");
                    
                if(result != null){
                     %> <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th> 
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for(RegistrationDTO dto : result){
                                %>
                                <tr>
                                    <td>
                                        <%= ++count %>
                                    .</td> 
                                    <td>
                                        <%= dto.getUsername() %>
                                    </td>
                                    <td>
                                        <%= dto.getPassword() %>
                                    </td>
                                    <td>
                                        <%= dto.getFullName() %>
                                    </td>
                                    <td>
                                         <%= dto.isRole() %>
                                    </td>
                        </tr>
                        <%
                            }//traversal each DTO in result
                        %>
                    </tbody>
                </table>

                   
                       
       <%
                } else {//search is not found
                    %>
                    <h2>
                        <font color="green">
                         No record is matched!!!
                         </font>
                    </h2>
       <%//
                }
           }//not access directly
            
%> --%>
    </body>
</html>
