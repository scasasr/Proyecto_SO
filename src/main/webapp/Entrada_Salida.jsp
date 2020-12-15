<%-- 
    Document   : Emtrada_Salida
    Created on : 13/12/2020, 03:15:41 AM
    Author     : mafec
--%>

<%@page import="Control.validar_inventario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Entrada/salida</title>

    </head>
    <body>
        <%
            //HttpSession sesion = request.getSession();
            //(if (sesion.getAttribute("logueado") == null || sesion.getAttribute("logueado").equals("0")) {
               // response.sendRedirect("Login.jsp");
            //}
        %>
        <div class="container mt-5" style="margin-bottom:35px"><h2 align="center">Entradas/Salidas</h2></div>
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-sm-4">
                    <form action="Registro.jsp" method="post">
                        <label style="margin-right: 41px">Producto:</label>
                        <select class="form-select" aria-label="Default select example" name="producto" style="margin-bottom: 15px">
                            <option selected>Seleccione</option>
                            <%
                                validar_inventario validar = new validar_inventario();
                                String value = null;
                                String[] recursos = validar.get_nameRecursos();
                                String[] unidad = validar.get_nameUnidad();
                                String[] categorias = validar.get_nameCategorias();
                                for (int i = 0; i < recursos.length; i++) {
                                    value = "" + i + "";
                            %>
                            <option value=<%=value%>><%=recursos[i]%></option>  
                            <%
                                }
                            %>
                        </select>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">agregar/desc</span>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="cantidad">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="inputGroup-sizing-default">Fecha vencimiento</span>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="vencimiento">
                            <div id="datehelp" class="form-text">Formato de la fecha es YYYY-MM-DD.</div>
                        </div>
                        <label>Unidad medida:</label>
                        <select class="form-select" aria-label="Default select example" name="unidad" style="margin-bottom: 15px">
                            <option selected>seleccione</option>
                            <%
                                String value_u = null;
                                for (int i = 0; i < unidad.length; i++) {
                                    value_u = "" + i + "";

                            %>
                            <option value=<%= value_u%>><%= unidad[i]%></option>
                            <%
                                }
                            %>                            
                        </select>
                        </br>
                        <label style="margin-right: 39px">Categoria:</label>
                        <select class="form-select" aria-label="Default select example" name="categoria" style="margin-bottom: 15px">
                            <option selected>Seleccione</option>
                            <%
                                String value_c = null;
                                for(int i = 0; i < categorias.length; i++) {
                                    value_c = "" + i + "";

                            %>
                            <option value=<%= value_c%>><%= categorias[i]%></option>
                            <%
                                }
                            %>   
                        </select>

                        <button type="submit" class="btn btn-primary" name="confirmar" style="margin-left: 110px">Confirmar E/S <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"/>
                            </svg></button>
                    </form> 
                </div>
            </div>
        </div>
        <div class="row align-items-center justify-content-center">
            <div class="col-sm-4">
                <div class="alert alert-info" role="alert">
                    <%
                        if (request.getParameter("confirmar") != null) {
                            int[] almacenado = new int[2];
                            String nombre = request.getParameter("producto");
                            String id_unidad = request.getParameter("unidad");
                            almacenado = validar.get_disponible(nombre, id_unidad);
                            if (almacenado[1] == 0) {
                                out.print("El nombre y la unidad de medida no coinciden");
                            } else if (almacenado[1] == -1) {
                                out.print("Hubo un error en la conexion al servidor");
                            } else if (almacenado[1] == 1) {
                                int nueva_cantidad = almacenado[0] + Integer.parseInt(request.getParameter("cantidad"));
                                String categoria = request.getParameter("categoria");
                                String vencimiento = request.getParameter("vencimiento");
                                boolean update = validar.update(nombre, nueva_cantidad, categoria, vencimiento);
                                if (update == false) {
                                    out.print("No se ha podido realizar los cambios, intente ,mas tarde");
                                }
                            }
                        }

                    %>



                </div>  
            </div>    
        </div>

    </body>
</html>
