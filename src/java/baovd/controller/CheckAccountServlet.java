/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import baovd.registration.RegistrationDAO;
import baovd.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
        

/**
 *
 * @author Asus
 */
@WebServlet(name="CheckAccountServlet", urlPatterns={"/CheckAccountServlet"})
public class CheckAccountServlet extends HttpServlet {
    private final String LOGIN_PAGE="Login.html";
    private final String SEARCH_PAGE = "search.jsp";
   
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
        
        String url = LOGIN_PAGE;
        //1. getcookies ( read cookie)
        Cookie[] cookies = request.getCookies();
        try  {
        //2. check cookies co ton tai hay khong 
       if ( cookies != null){
           Cookie recentCookie = cookies[cookies.length - 1];
           String username = recentCookie.getName();
           String password = recentCookie.getValue();
           
        //2.1 neu co call method of DAO ( neu cookies co ton tai (username password da dc nho) thi phai login)
           RegistrationDAO dao = new RegistrationDAO();
           RegistrationDTO result = dao.checkLogin(username, password);
           //3 process
           if( result != null){
               HttpSession session = request.getSession();
               session.setAttribute("USER_INFO",result);
               url = SEARCH_PAGE;
               // store cookie
               Cookie user = new Cookie(username, url);
               user.setMaxAge(60*3);
               response.addCookie(user);
               
               
           
           }
       }//has already remember
        
       
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckAccountServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckAccountServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        
        }catch(SQLException ex){
            log("SQL" + ex.getMessage());
    
        }catch(ClassNotFoundException ex){
            log("ClassNotFoundException" + ex.getMessage());
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
