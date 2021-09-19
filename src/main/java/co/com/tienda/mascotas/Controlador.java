package co.com.tienda.mascotas;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Controlador() {
       super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
String menu = request.getParameter("menu");
String accion = request.getParameter("accion");
        
 switch (menu) {
     case "Principal":
	  request.getRequestDispatcher("/Principal.jsp").forward(request, response);
	  break;
     case "Usuarios":
	  if (accion.equals("Listar")) {
	     try {
	        ArrayList<Usuarios> lista = TestJSON.getJSON();
		 request.setAttribute("lista", lista);
	     } catch (Exception e) {
		 e.printStackTrace();
	     }
	  }else if(accion.equals("Agregar")) {
	     Usuarios usuario = new Usuarios();
	     usuario.setCedula_usuario(Long.parseLong(request.getParameter("txt_cedula")));
	     usuario.setNombre_usuario(request.getParameter("txt_nombre_completo"));
	     usuario.setEmail_usuario(request.getParameter("txt_correo"));
	     usuario.setUsuario(request.getParameter("txt_usuario"));
	     usuario.setPassword(request.getParameter("txt_password"));
					
	     int respuesta=0;
	      try {
		   respuesta = TestJSON.postJSON(usuario);
		   if (respuesta==200) {
			   request.setAttribute("usuario", usuario);
	request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
                                                                               response);
	        } else {
	        	System.out.println("Error: " +  respuesta);
			
		 }
	     } catch (Exception e) {
		 e.printStackTrace();
	      }
					
	  }
	  request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
	  break;
	case "Clientes":
		request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		break;
	case "Proveedores":	
		request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
	      break;
	case "Productos":
		request.getRequestDispatcher("/Productos.jsp").forward(request, response);
		break;
	case "Ventas":	
		request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
		break;
	case "Reportes":	
		request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
		break;
	}
      
   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
