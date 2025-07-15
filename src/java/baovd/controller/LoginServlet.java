/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import baovd.registration.RegistrationDAO;
import baovd.registration.RegistrationDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class LoginServlet extends HttpServlet {
//    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String INVALID_PAGE = "invalid.html";
   
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
        PrintWriter out = response.getWriter();
        //1. get all user's information
        //String button = request.getParameter("btAction");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
            //2. controller call method of DAO   
        String url = INVALID_PAGE;
        try {
           // if (button.equals("Login")){
              //if ("Login".equals(button)) {  
             //2.Controller calls methos of DAO  
            //2.1 new DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2,2 call method of DAO Object
            RegistrationDTO result = dao.checkLogin(username, password);
            //3. process
//                 if(result){
                 if(result!=null){ 
                     HttpSession session = request.getSession();
                     session.setAttribute("USER_INFO", result);
                //neu session hien thanh da bi huy se tra null 
                //neu co se tra lai session hien thanh neu khong co tao lai session moi
                             
                url = SEARCH_PAGE;
                    
                    }// user is authentication     
            // user clicked Login button
            
            
            
            /*out.println("Username" + username + " - " + password + " - " + button);
            //debug
            System.out.println("Username" + username + " - " + password + " - " + button);
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");




        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();           
        }finally{
//            response.sendRedirect(url);
             RequestDispatcher rd = request.getRequestDispatcher(url);
             rd.forward(request, response);
            out.close();
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
