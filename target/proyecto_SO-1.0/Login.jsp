<%@page import="Control.validar_login"%>
<%@page import="Entidad.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Login</title>
    </head>
    <body>
        <div class="container mt-5 ">
            <div class="row align-items-center justify-content-center">
                <div class="col-sm-4"style="height: 270px;">
                    <form action="Login.jsp" method="post">
                        <div class="form-group" >
                            <label>Usuario:</label>
                            <input type="text" class="form-control" name="nombre_us" placeholder="Usuario">
                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                        </div>
                        <div class="form-group">
                            <label >Contraseña:</label>
                            <input type="password"  class="form-control" name="password" placeholder="**********">
                        </div>
                        <div align-items-center justify-content-center >
                            <button type="submit" class="btn btn-primary" name="Login" style=margin-left:110px>Login</button>
                            <a href="Registro.jsp"><label>Registrarse</label></a>
                        </div>

                    </form> 
                </div>
            </div>
        </div>
        <div class="row align-items-center justify-content-center">
            <div class="col-sm-4">
                <div class="alert alert-info" role="alert">
                    <%
                        if (request.getParameter("Login") != null) {
                            Usuario user = new Usuario();
                            user.setName(request.getParameter("nombre_us"));
                            System.out.println(request.getParameter("nombre_us"));
                            user.setPassword(request.getParameter("password"));
                            validar_login validar = new validar_login();
                            int action = validar.verificarLogin(user);
                            HttpSession sesion = request.getSession();
                            if (action == 1) {
                                sesion.setAttribute("logueado", 1);
                                sesion.setAttribute("user", request.getParameter("nombre_us"));
                                response.sendRedirect("index.jsp");
                            } else if (action == -1) {
                                out.println("Longitud de usuario incorrecta");
                            } else if (action == -2) {
                                out.println("Longitud de contraseña incorrecta");
                            } else if (action == -3) {
                                out.println("Ha ocurrido un error con la conexion a bd");
                            } else if (action == -9) {
                                out.println("Las contraseña es incorrecta");
                            }else if (action == 0) {
                                out.println("Usuario o contraseña incorrecto");
                            }

                        }
                    %>
                </div>
            </div>
        </div>
    </body>

</html>
