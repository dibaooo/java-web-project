/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import baovd.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
@WebServlet(name="DeleteAccountServlet", urlPatterns={"/DeleteAccountServlet"})
public class DeleteAccountServlet extends HttpServlet {
   private final String ERROR_PAGE = "Error.html";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //1get all user'information
        String username= request.getParameter("pk");
        String searchValue= request.getParameter("lastSearchValue");
        String url = ERROR_PAGE;
        
        try {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DeleteAccountServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DeleteAccountServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    } 
            
        //2Controller get method of DAO
        //2.1new Dao Object
            RegistrationDAO dao = new RegistrationDAO();
        //2.2Call method of Dao object
        boolean result=dao.deleteAccount(username);
        //3.process
        if(result = true){
            // refresh --> call previous function again
            // -->url rewriting --> add requset pars base on how many input control;
            url="DispatchServlet"
                    + "?btAction=search"
                    + "&txtSearchValue=" + searchValue;
            
        }//delete is succesfully
        
        }catch(SQLException ex){
            log("DeleteAccountServlet_SQL" + ex.getMessage());
    
        }catch(ClassNotFoundException ex){
            log("DeleteAccountServlet_ClassNotFoundException" + ex.getMessage());
        }finally{
            response.sendRedirect(url);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
