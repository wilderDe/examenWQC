<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Productos"%>
<%
    ArrayList<Productos> lista = (ArrayList<Productos>) session.getAttribute("listapro");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>SEGUNDO PARCIAL TEM-742</h2>
        <h2>NOMBRE: WILDER QUISPE CONDOR</h2>
        <h2>CARNET: 7091360</h2>
        <h1>Productos</h1>
        <a href="MainController?op=nuevo">Nuevo producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <%
            if(lista != null){
                for(Productos item : lista){
            %>
            <tr>
                <td><%= item.getId()  %></td>
                <td><%= item.getCantidad()  %></td>
                <td><%= item.getPrecio() %></td>
                <td><%= item.getCategoria()  %></td>
                <td><a href="MainController?op=editar&id=<%= item.getId() %>">Modificar</a></td>
                <td><a href="MainController?op=eliminar&id=<%= item.getId() %>"
                       onclick='return confirm("Esta seguro de eliminar el registro")'>Eliminar</a></td>
            </tr>
            <%
                
                }
            }
            %>     
        </table>

    </body>
</html>
