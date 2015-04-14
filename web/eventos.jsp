<%-- 
    Document   : eventos
    Created on : Mar 13, 2015, 7:38:34 PM
    Author     : guilhermecortes
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.cesjf.lpaw.classes.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.cesjf.lpaw.classes.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Evento> eventos = (List<Evento>)request.getAttribute("eventos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <title>Eventos</title>
    </head>
    <body>
        <div class="container">
            <%@include file="WEB-INF/jspf/menu.jspf" %>
            <h3>Olá <%=((Usuario)session.getAttribute("usuario")).getNome()%>,</h3>
            <h1>Eventos</h1>
            <% String str_entrada;%>
            <table class="table table-bordered table-striped">
                <tr>
                    <th>Evento</th>
                    <th>Usuário</th>
                    <th>Estadia</th>
                    <th>Data</th>
                </tr>

                <%
                    for (Evento evento : eventos)
                    {
                        str_entrada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(evento.getHora());
                %>
                <tr>
                    <td><%= evento.getAcao()%></td>
                    <td><%= evento.getUsuario()%></td>
                    <td><%= evento.getEstadia()%></td>
                    <td><%= str_entrada %></td>
                </tr>
                <%
                    }
                %>


            </table>
        </div>
    </body>
</html>
