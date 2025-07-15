/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import baovd.registration.RegistrationDAO;
import baovd.registration.RegistrationUpdateError;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
@WebServlet(name="UpdateAccountServlet", urlPatterns={"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {
private final String ERROR_PAGE="Error.html";   
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
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastSearchValue = request.getParameter("lastSearchValue");
        boolean isAdmin = request.getParameter("chkAdmin") != null;
        
        Map<String,RegistrationUpdateError> errorsMap = new HashMap<>();
        RegistrationUpdateError errors = new RegistrationUpdateError();
        boolean foundError = false;
        String url = ERROR_PAGE;
        try  {
          if(password.trim().length() < 8 || 
                  password.trim().length() > 20){
              foundError = true;
              errors.setPasswordLengthErr("Password is required from 8 to 20");
              errorsMap.put(username, errors);
          }
          if (foundError){
                request.setAttribute("UPDATE_ACCOUNT", errorsMap);
                url = "DispatchServlet"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + lastSearchValue;
          }else{
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.updateAccount(username, password, isAdmin);
                if (result) {
                    url = "DispatchServlet"
                            + "?btAction=Search"
                            + "&txtSearchValue=" + lastSearchValue;              
            } 
          }
        }catch(SQLException ex){
            log("SQL: " +ex.getMessage());
        }catch(ClassNotFoundException ex){
            log("Class Not Found: " +ex.getMessage());            
        }finally{
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            if (foundError) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                response.sendRedirect(url);
            }

        }          
                
            
            
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdateAccountServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateAccountServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        
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
