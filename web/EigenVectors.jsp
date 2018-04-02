<%-- 
    Document   : EigenVectors
    Created on : Apr 2, 2018, 1:51:40 PM
    Author     : David Klecker
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import = "Project2.EigenVectoring" %>
<jsp:useBean id = "EigenVectoring" class = "Project2.EigenVectoring" scope = "session" ></jsp:useBean>
<jsp:setProperty name = "EigenVectoring" property = "*" /> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            EigenVectoring.ProcessRequest();
        %>
        <h1>Eigen Values</h1>
        <table><tr>
        <%
            double x[] = EigenVectoring.getEigenValues();
            for(int i=0; i<x.length;i++){
                %>      <td><%=x[i]%></td>
        <%
            }
         %>
            </tr></table>
    </body>
</html>
