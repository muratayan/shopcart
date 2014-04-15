/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mypack.servlets;

import java.io.*;
import java.net.*;
import javax.annotation.security.DeclareRoles;
import static javax.enterprise.deploy.shared.ModuleType.EJB;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServ extends HttpServlet {

    /** 
     * Processes requests for both HTTP GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, 
                 HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String userName = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            
            out.println("Username: " + userName +"<br>");
            out.println("Pass: " + password +"<br>");
            
            try {
                request.login(userName, password); 
            } catch(ServletException ex) {
                request.setAttribute("loginfail", "olmaz");
                this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
                return;
            }
            
            request.getSession().setAttribute("username", userName);
            this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
            
            //request.logout();
        } finally {
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
