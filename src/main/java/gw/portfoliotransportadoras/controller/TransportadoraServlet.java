/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.controller;

import gw.portfoliotransportadoras.manager.TransportadoraManager;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vicente
 */
@WebServlet("/")
public class TransportadoraServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private TransportadoraManager transportadoraManager;
    
    public void init() {
        this.transportadoraManager=new TransportadoraManager();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/novo":
                    mostrarNovoForm(request, response);
                    break;
                 case "/editar":
                    mostrarFormEdicao(request, response);
                    break;
                default:
                    listTransportadora(request, response);
                    break;
            }
        }catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void mostrarNovoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("transportadora-form.jsp");
        dispatcher.forward(request, response);
    }
    private void mostrarFormEdicao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
         int id = Integer.parseInt(request.getParameter("id"));
         
         Transportadora transportadoraExistente=transportadoraManager.getTransportadoraPorId(id);
         RequestDispatcher dispatcher = request.getRequestDispatcher("transportadora-form.jsp");
         
         System.err.println("transportadoraExistente "+transportadoraExistente.getNome());
         request.setAttribute("transportadora", transportadoraExistente);
         dispatcher.forward(request, response);
    }
    
    private void listTransportadora(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<Transportadora> listTransportadora = transportadoraManager.getList();
        
        request.setAttribute("listTransportadora", listTransportadora);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-transportadora.jsp");
        
        dispatcher.forward(request, response);
        
    }
}
