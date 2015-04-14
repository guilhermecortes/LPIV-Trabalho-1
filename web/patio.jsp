<%-- 
    Document   : patio
    Created on : Mar 13, 2015, 7:30:25 PM
    Author     : guilhermecortes
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.cesjf.lpaw.classes.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="br.cesjf.lpaw.classes.Estadia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Estadia> estadias = (List<Estadia>)request.getAttribute("estadias");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pátio</title>
        <%@include file="WEB-INF/jspf/head.jspf" %>
    </head>
    <body>
        <% 
            String str_entrada;
            String str_saida;
        %>
        <div class="container">
            <%@include file="WEB-INF/jspf/menu.jspf" %>
            
            <h3>Olá <%=((Usuario)session.getAttribute("usuario")).getNome()%>,</h3>
            <h1>Pátio</h1>
            <table class="table table-bordered table-striped">
                <tr>
                    <th>#</th>
                    <th>Placa</th>
                    <th>Entrada</th>
                    <th>Saída</th>
                    <th>Preço</th>
                    <th></th>
                    <th></th>
                </tr>

                <%
                    for (Estadia estadia : estadias)
                    {
                        str_entrada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(estadia.getEntrada());
                        if(estadia.getSaida()== null){
                            str_saida = "";
                        } else {
                            str_saida = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(estadia.getSaida());
                        }
                %>
                <tr>
                    <td><%= estadia.getId()%></td>
                    <td><%= estadia.getPlaca()%></td>
                    <td><%= str_entrada %></td>
                    <td><%= str_saida %></td>
                    <td><%= estadia.getValor_pago()%></td>
                    <td><a href='Edicao?id=<%= estadia.getId()%>'>Editar</a></td>
                    <td><a href='Saida?id=<%= estadia.getId()%>'>Saída</a></td>
                </tr>
                <%
                    }
                %>

            </table>

        </div>
    </body>
</html>
