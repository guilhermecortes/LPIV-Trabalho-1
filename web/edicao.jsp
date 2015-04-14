<%-- 
    Document   : login
    Created on : Mar 13, 2015, 7:26:30 PM
    Author     : guilhermecortes
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.cesjf.lpaw.classes.Estadia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Estadia estadia = (Estadia)request.getAttribute("estadia");
    String str_entrada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(estadia.getEntrada());
    
    String str_saida = "";
    if(!(estadia.getSaida()== null)){
        str_saida = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(estadia.getSaida());
    } else {
        str_saida = "";
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edição</title>
        <%@include file="WEB-INF/jspf/head.jspf" %>
    </head>
    <body>
        <div class="container">
            <%@include file="WEB-INF/jspf/menu.jspf" %>
            <h1>Edição</h1>

            <form action="SalvaEdicao" method="post" class="form-horizontal">
                
                <div class="form-group">
                    <label class="col-sm-2 control-label">Placa</label>
                    <div class="col-sm-2">
                        <input type="text" name="placa" value="<%= estadia.getPlaca() %>" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Entrada</label>
                    <div class="col-sm-2">
                        <input type="text" name="entrada" value="<%= str_entrada %>" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Saída</label>
                    <div class="col-sm-2">
                        <input type="text" name="saida" value="<%= str_saida %>" class="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Valor</label>
                    <div class="col-sm-2">
                        <input type="text" name="valor" value="<%= estadia.getValor_pago()%>" class="form-control" />
                    </div>
                </div>
                
                <input type="hidden" value="<%= estadia.getId() %>" name="id" />
                
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                      <input type="submit" name="salvar" value="Salvar" class="btn btn-default"/>
                  </div>
                </div>
            </form>

        </div>
    </body>
</html>
