<%-- 
    Document   : Registro
    Created on : 10/12/2020, 08:54:27 PM
    Author     : mafec
--%>

<%@page import="Control.validar_registro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Registro</title>
    </head>
    <body>     
        <div class="container mt-5"><h2 align="center">Registro</h2></div>
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-sm-4">
                    <form action="Registro.jsp" method="post">
                        <div class="form-group" >
                            <label>Identificacion:</label>
                            <input type="text" class="form-control" name="Id" placeholder="ID">
                        </div>
                        <div class="form-group">
                            <label >Usuario(email):</label>
                            <input type="text" class="form-control" name="nombre" placeholder="ejemplo@correoejemplo.com">
                        </div>
                        <div class="form-group">
                            <label >Contraseña:</label>
                            <input type="password" class="form-control" name="contrasenia" placeholder="**********">
                        </div>
                        <div class="form-group">
                            <label >Confirmar contraseña:</label>
                            <input type="password" class="form-control" name="confirmar" placeholder="**********">
                        </div>
                        <button type="submit" class="btn btn-primary" name="Registrar" style=margin-left:110px>Registrarse</button>
                    </form> 
                </div>
            </div>
        </div></br>
        <div class="row align-items-center justify-content-center">
            <div class="col-sm-4"> 
                <div class="alert alert-info" role="alert">
                    <%
                        if (request.getParameter("Registrar")!= null){
                            String id_user=request.getParameter("Id");
                            String user_name=request.getParameter("nombre");
                            String contrasenia=request.getParameter("contrasenia");
                            String confirmar=request.getParameter("confirmar");
                            validar_registro registro= new validar_registro();
                            int flag= registro.registro(Integer.parseInt(id_user), user_name, contrasenia, confirmar);
                            if(flag==1){
                                request.getRequestDispatcher("Login.jsp").forward(request, response);
                            }else if(flag ==0){
                                out.println("Ha ocurrido un error al conectar con el servidor");
                            }else if(flag==-1){
                                out.println("Longitud de correo incorrecta");
                            }else if(flag==-2){
                                out.println("Longitud de contraseña incorrecta");
                            }else if(flag==-3){
                                out.println("Las contraseñas no coinciden");
                            } 
                        }
                    
                    %>
                </div>  
            </div>       
        </div>
    </body>>
</html>
