
package com.emergentes.controlador;

import com.emergentes.modelo.Productos;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        HttpSession ses = request.getSession();
        
        if(ses.getAttribute("listapro") == null){
            ArrayList<Productos> listaux = new ArrayList<Productos>();
            ses.setAttribute("listapro", listaux);
        }
        
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        
        String op = request.getParameter(("op"));
        String opcion = (op != null) ? request.getParameter("op") : "view";
        
        Productos obj1 = new Productos();
        
        int id, pos;
        
        switch (opcion){
            case "nuevo":
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                obj1 = lista.get(pos);
                request.setAttribute("miProducto", obj1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                pos = buscarIndice(request, Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                ses.setAttribute("listapro", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
                break;
        }
    }   
   
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        
        Productos obj = new Productos();
        obj.setId(Integer.parseInt(request.getParameter("id")));
        obj.setDescripcion(request.getParameter("descripcion"));
        obj.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        obj.setPrecio(Double.parseDouble(request.getParameter("precio")));
        obj.setCategoria(request.getParameter("categoria"));
        
        int idt = obj.getId();
        
        if(idt == 0){
            int ultID;
            ultID = ultimoId(request);
            obj.setId(ultID);
            lista.add(obj);
        }else{
            lista.set(buscarIndice(request, idt), obj);
        }
        ses.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");
       
    }
    
    private int ultimoId(HttpServletRequest request ){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        
        int idaux = 0;
        for(Productos item : lista){
            idaux = item.getId();
        }
        return idaux +1;
    }
    
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos>) ses.getAttribute("listapro");
        
        int i = 0;
          
        if(lista.size() > 0 ){
            while (i<lista.size()) {                
                if(lista.get(i).getId() == id ){
                    break;
                }else{
                    i++;
                }
            }
        }
        
        return i;
    }
}

