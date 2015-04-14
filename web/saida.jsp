<%-- 
    Document   : saida
    Created on : Mar 13, 2015, 7:42:26 PM
    Author     : guilhermecortes
--%>

<%@page import="br.cesjf.lpaw.classes.Estadia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <title>Saída</title>
    </head>
    <body>
        <%
            Date dt_atual = new Date();
            Estadia estadia = (Estadia)request.getAttribute("estadia");
            String str_saida = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dt_atual); //str formatada para ser exibida na view
            String str_entrada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(estadia.getEntrada());
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date entrada = format.parse(str_entrada);
            Date saida = format.parse(str_saida);
            
            long dif_milisegundos = saida.getTime() - entrada.getTime(); //milisegundos
            int horas   = (int) ((dif_milisegundos / (1000*60*60)) % 24); //horas
            int minutos = (int) ((dif_milisegundos / (1000*60)) % 60); //minutos
            
            //Valor por hora = R$8,00
            int parcial_minutos = (int) (minutos / 15 + 1) * 2; //+1 por causa da fração de 15 min
            int valor_total = horas * 8 + parcial_minutos;  
        %>
        
        <div class="container">
            <%@include file="WEB-INF/jspf/menu.jspf" %>
            <h1>Saída</h1>

            <form action="FecharSaida" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Estadia</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= estadia.getId()%>" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Placa</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= estadia.getPlaca()%>" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Entrada</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= str_entrada %>" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Saída</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= str_saida %>" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Valor</label>
                    <div class="col-sm-2">
                        <input type="text" disabled="disabled" class="form-control" value="<%= valor_total %>" />
                    </div>
                </div>
                    
                <input type="hidden" value="<%= valor_total %>" name="valor" />
                <input type="hidden" value="<%= estadia.getId() %>" name="id" />

                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" name="fechar" value="Fechar" class="btn btn-default"/>
                  </div>
                </div>
            </form>                
                
        </div>
    </body>
</html>
