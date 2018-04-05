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
            EigenVectoring.ProcessRequest(request);
        %>
        <h1> P Matrix </h1>
        <%=EigenVectoring.getGroupNodeLocal().getMValue()%>
        <table>
        <%
            for(int i=0; i<EigenVectoring.getGroupNodeLocal().getP_Matrix().getColumnDimension();i++){
        %>
                <tr>
        <%
                for(int j=0; j<EigenVectoring.getGroupNodeLocal().getP_Matrix().getRowDimension();j++){
        %>
                    <td><%=EigenVectoring.getGroupNodeLocal().getP_Matrix().get(i, j)%></td>
        <%
                }
        %>
                </tr>
        <%  }%>
        </table>

        <h1> B Matrix </h1>
        <table>
        <%
            for(int i=0; i<EigenVectoring.getGroupNodeLocal().getB_Matrix().getColumnDimension();i++){
        %>
                <tr>
        <%
                for(int j=0; j<EigenVectoring.getGroupNodeLocal().getB_Matrix().getRowDimension();j++){
        %>
                    <td><%=EigenVectoring.getGroupNodeLocal().getB_Matrix().get(i, j)%></td>
        <%
                }
        %>
                </tr>
        <%  }%>
        </table>
        
        <h1>Eigen Values</h1>
        <table><tr>
        <%
            double x[] = EigenVectoring.getGroupNodeLocal().getEigenValues();
            for(int i=0; i<x.length;i++){
                %>      <td><%=x[i]%></td>
        <%
            }
         %>
            </tr></table>
    </body>
</html>
