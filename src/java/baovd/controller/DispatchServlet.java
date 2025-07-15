/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package baovd.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
@WebServlet(name="DispatchServlet", urlPatterns={"/DispatchServlet"}) 
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "Login.html";
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER="SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER="DeleteAccountServlet";
    private final String CHECK_ACCOUNT_CONTRONLLER="CheckAccountServlet";
    private final String CREATE_ACCOUNT_CONTRONLLER="CreateAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER="UpdateAccountServlet";

    

   
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
        //1. USER da nhan nut gi Which button did USER click
        String button = request.getParameter("btAction");// tat ca cac hanh dong di qua ham nay copy paste vao ("") chinh xac
        //de truyen URL co kem theo parameters vao btACtion co chu btAction khi goi Dispatch Controller bang bao nhieu 
        
        
        try {
            if (button == null){ //First request 
                                 // do nothing transfer to Login page
                    url = CHECK_ACCOUNT_CONTRONLLER;
            } else {
                switch(button){
                    case "Login":
                        url = LOGIN_CONTROLLER; 
                        break;
                    case "search":    
                        url = SEARCH_LASTNAME_CONTROLLER;
                        break;
                    case "delete":    
                        url = DELETE_ACCOUNT_CONTROLLER;
                        break;
                    case "create":    
                        url = CREATE_ACCOUNT_CONTRONLLER; 
                        break;
                    case "update":
                        url = UPDATE_ACCOUNT_CONTROLLER;
                        break;
                    default: //nothing    
                }
            }
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
