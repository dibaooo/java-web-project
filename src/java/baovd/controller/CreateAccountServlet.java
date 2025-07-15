/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import baovd.registration.RegistrationCreateError;
import baovd.registration.RegistrationDAO;
import baovd.registration.RegistrationDTO;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name="CreateAccountServlet", urlPatterns={"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
   private final String ERROR_PAGE="createAccount.jsp";
   private final String LOGIN_PAGE = "Login.html";
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
        String url = ERROR_PAGE;
        
        // 1.get all user information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        
        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundError = false;
        try  {
          //1 Validate User's Error
          if(username.trim().length() < 6 || 
                  username.trim().length() > 12){
              foundError = true;
              errors.setUsernameLengthErr("Username is required from 6 to 12");
          }
          
          if(password.trim().length() < 8 || 
                  password.trim().length() > 20){
              foundError = true;
              errors.setPasswordLengthErr("Password is required from 8 to 20");
          } else if (!confirm.trim().equals(password.trim())){ // typing more not range 8;12
              foundError = true;
              errors.setConfirmNotMatches("Confirm is required to match password");
          }
          if(fullName.trim().length() < 2 || 
                  fullName.trim().length() > 40){
              foundError = true;
              errors.setFullNameLengthErr("Username is required from 2 to 40");
          }
          if (foundError){
              request.setAttribute("CREATE_ACCOUNT", errors);
          }else{ //no errors
          
          
          //2. Controller calls method of DAO ; 
          //2.1 New DAO Object
              RegistrationDAO dao = new RegistrationDAO();    
          //2.2Call method of new DAO OBject
              RegistrationDTO account = new RegistrationDTO(
                  username, password, fullName, false); 
                  boolean result = dao.createAccount(account);
          //3 process
          if (result){
              url = LOGIN_PAGE;
              } //no errors
        }
        }catch(SQLException ex){
        String msg = ex.getMessage();
        
            log("SQL" + msg);
            if(msg.contains("duplicate")){
                errors.setUserNameisExisted(username + "is existed");
                request.setAttribute("CREATE_ACCOUNT", errors);
                        }
        }catch(ClassNotFoundException ex){
            log("Class Not Found" + ex.getMessage());
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response); 
        }
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CreateAccountServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CreateAccountServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
