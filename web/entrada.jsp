<%-- 
    Document   : entrada
    Created on : Mar 13, 2015, 7:45:57 PM
    Author     : guilhermecortes
--%>


<%@page import="br.cesjf.lpaw.classes.Usuario"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <title>Entrada</title>
    </head>
    <body>
        <div class="container">
            <%@include file="WEB-INF/jspf/menu.jspf" %>
            <h3>Olá <%=((Usuario)session.getAttribute("usuario")).getNome()%>, </h3>
            <h1>Entrada</h1>
            <%
                Date myDate = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(myDate);
            %>

            <form action="CriaEntrada" method="post" class="form-horizontal">
                <div class="form-group">
                  <label class="col-sm-2 control-label">Placa</label>
                  <div class="col-sm-2">
                    <input type="text" name="placa" autofocus class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Entrada</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= data %>" />
                    </div>
                </div>

                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" name="entrada_placa" value="Ok" class="btn btn-default"/>
                  </div>
                </div>
            </form>                
        </div>
    </body>
</html>
