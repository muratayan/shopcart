/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.servlets;

import com.mypack.beans.OrderBean;
import com.mypack.beans.PizzaBean;
import com.mypack.beans.PizzaListBean;
import com.mypack.beans.ProfileBean;
import com.mypack.beans.ShoppingBean;
import com.mypack.beans.StuffBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author murti_000
 */
public class welcomeServ extends HttpServlet {

    private PizzaListBean pizzaList = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {
            pizzaList = new PizzaListBean();
        } catch (Exception e) {
            throw new ServletException(e);
        }

        // DEBUG Collection osman = pizzaList.getProduktLista();
        // DEBUG Iterator iter = osman.iterator();
        // DEBUG PizzaBean pp = (PizzaBean)iter.next();
        // servletContext is the same as scope Application
        // store the pizza list in application scope
        // DEBUG System.out.print(pp.getName());
        ServletContext sc = getServletContext();
        sc.setAttribute("pizzaList", pizzaList);
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        //this.getServletContext().getRequestDispatcher( "/welcome.jsp" ).forward( request, response );
        HttpSession sess = request.getSession();
        RequestDispatcher rd = null;
        ShoppingBean shoppingCart = getCart(request);

        // find out what to do based on the attribute "action"
        // no action or show
        if (request.getParameter("action") == null
                || request.getParameter("action").equals("show")) {

            // A request dispatcher that's connected to the page.
            rd = request.getRequestDispatcher("/show.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("action").equals("add")) {

            // verify bookid and quantity
            if (request.getParameter("pizzaId") != null
                    && request.getParameter("quantity") != null) {

                PizzaBean bb = null;

                // search the book in our shop
                bb = pizzaList.getById(Integer.parseInt(
                        request.getParameter("pizzaId")));
                if (bb == null) {
                    throw new ServletException("The pizza is not in stock.");

                } else {

                    // found, add it to the cart
                    try {
                        int q = Integer.parseInt(
                                request.getParameter("quantity"));
                        if (q <= 0) {
                            throw new NumberFormatException(
                                    "Invalid quantity");
                        }
                        shoppingCart.addPizza(bb, q);
                    } catch (NumberFormatException e) {
                        throw new ServletException("Invalid quantity specified");
                    }
                }
            }

            // back to the showpage
            rd = request.getRequestDispatcher("/show.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("action").equals("remove")) {
            if (request.getParameter("pizzaId") != null
                    && request.getParameter("quantity") != null) {
                try {
                    int q = Integer.parseInt(request.getParameter("quantity"));
                    if (q <= 0) {
                        throw new NumberFormatException(
                                "Illegal quantity");
                    }
                    shoppingCart.removePizza(
                            Integer.parseInt(request.getParameter("pizzaId")), q);
                } catch (NumberFormatException e) {
                    throw new ServletException("Illegal quantity specified");
                }
            } else {
                throw new ServletException(
                        "No bookid or quantity when removing book from cart");
            }
            rd = request.getRequestDispatcher("/show.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("action").equals("checkout")) {
            if (sess.getAttribute("username") != null) {

		   // create an profile and populate it from the
                // database
                ProfileBean p = new ProfileBean();
                try {
                    p.populate((String) sess.getAttribute("username"));
                } catch (Exception e) {
                    throw new ServletException("Error loading profile", e);
                }
                sess.setAttribute("profile", p);
                System.out.println(p.getAddress());
                OrderBean ob = new OrderBean(p, shoppingCart);
                if (ob.saveOrder()) {
                    shoppingCart.clear();
                    // redirect (not forward)
                    rd = request.getRequestDispatcher("/thanks.jsp"); // Burası hata veya teşekkür için
                    rd.forward(request, response);

                } else {
                    shoppingCart.clear();
                    // redirect (not forward)
                    rd = request.getRequestDispatcher("/sorry.jsp"); // Burası hata veya teşekkür için
                    rd.forward(request, response);
                }

            }

            response.sendRedirect("/index.jsp");

        } else if (request.getParameter("action").equals("itemMenu")) {

            StuffBean stf = new StuffBean();
            stf.init();
            sess.setAttribute("stuffs", stf);
            
            
            rd = request.getRequestDispatcher("/admin/items.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("action").equals("addItem")) {

            StuffBean stf1 = (StuffBean)sess.getAttribute("stuffs");
      
            stf1.update(Integer.parseInt(request.getParameter("q1")), 
                        Integer.parseInt(request.getParameter("q2")), 
                        Integer.parseInt(request.getParameter("q3")), 
                        Integer.parseInt(request.getParameter("q4")));
            
            
            sess.setAttribute("stuffs", stf1);
            
            rd = request.getRequestDispatcher("/admin/items.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("action").equals("profile")) {
            if(sess.getAttribute("profile") == null) {
                ProfileBean p = new ProfileBean();
                try {
                    p.populate((String) sess.getAttribute("username"));
                } catch (Exception e) {
                    throw new ServletException("Error loading profile", e);
                }
                sess.setAttribute("profile", p);
            } else {
                ProfileBean p = (ProfileBean)sess.getAttribute("profile");
            }
            
             rd = request.getRequestDispatcher("editProfile.jsp");
             rd.forward(request, response);
        } else if (request.getParameter("action").equals("editProfile")) {
             ProfileBean p = new ProfileBean();
            if(sess.getAttribute("profile") == null) {
                try {
                    p.populate((String) sess.getAttribute("username"));
                } catch (Exception e) {
                    throw new ServletException("Error loading profile", e);
                }
                sess.setAttribute("profile", p);
            } else {
                p = (ProfileBean)sess.getAttribute("profile");
            }
            
             p.update(request.getParameter("name_"), request.getParameter("address_"));
             rd = request.getRequestDispatcher("editProfile.jsp");
             rd.forward(request, response);
        }

    }

    private ShoppingBean getCart(HttpServletRequest request) {
        HttpSession se = null;
        se = request.getSession();
        ShoppingBean sb = null;
        sb = (ShoppingBean) se.getAttribute("shoppingCart");
        if (sb == null) {
            sb = new ShoppingBean();
            se.setAttribute("shoppingCart", sb);
        }

        return sb;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(welcomeServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(welcomeServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(welcomeServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(welcomeServ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
