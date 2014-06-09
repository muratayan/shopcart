/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mypack.servlets;

import com.mypack.beans.Car;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import static javax.enterprise.deploy.shared.ModuleType.EJB;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServ extends HttpServlet {
    
    private Car car;
    /** 
     * Processes requests for both HTTP GET and POST methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, 
                 HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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
            
            //System.out.println("Murat KRAL");
            //car = new Car();
            request.getSession().setAttribute("username", userName);
            //request.getSession().setAttribute("car", car);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
            
            //request.logout();
        } finally {
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
