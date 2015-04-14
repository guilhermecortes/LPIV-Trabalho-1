<%-- 
    Document   : login
    Created on : Mar 13, 2015, 7:26:30 PM
    Author     : guilhermecortes
--%>

<%@page import="br.cesjf.lpaw.classes.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="WEB-INF/jspf/head.jspf" %>
        <title>Login</title>
    </head>
    <body>
        <div class="container">
            <h1>Login</h1>

            <form class="form-horizontal" action="Login" method="post">
                <div class="form-group">
                  <label class="col-sm-2 control-label">E-mail</label>
                  <div class="col-sm-6">
                    <input type="email" name="email" class="form-control"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">Senha</label>
                  <div class="col-sm-6">
                    <input type="password" name="senha" class="form-control" />
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-6">
                    <input type="submit" name="login" value="Entrar" class="btn btn-default"/>
                  </div>
                </div>
            </form>
        </div>
        
    </body>
</html>
