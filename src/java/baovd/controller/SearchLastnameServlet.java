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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
@WebServlet(name="SearchLastnameServlet", urlPatterns={"/SearchLastnameServlet"})
public class SearchLastnameServlet extends HttpServlet {
    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_RESULT = "search.jsp";
    
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
        
        //1. Get all user'information
        String url = SEARCH_PAGE;
        String searchValue = request.getParameter("txtSearchValue");
        try {
//            if (searchValue.trim().isEmpty()) {
        if (searchValue != null && !searchValue.trim().isEmpty()) {

            //2. controller call method DAO
            //2.1 new DAO Object
                RegistrationDAO dao = new RegistrationDAO();
            //2.2 call method of DAO Object
                dao.searchLastname(searchValue);
            //3. process
                List<RegistrationDTO> result = dao.getAccounts(); //lay list 
                url = SEARCH_RESULT;// 
                request.setAttribute("SEARCH_RESULT", result);//dang chua 2 parameter ttxSearchValue va btAction ; 1 atribute la SEARCH_RESULT
                
                
            } //user must input valid value
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url); //su dung request vi response tra ve se xoa sach
            rd.forward(request, response);
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
