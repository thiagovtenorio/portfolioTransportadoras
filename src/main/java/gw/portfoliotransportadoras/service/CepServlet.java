/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author vicente
 */
@WebServlet("/CepServlet")
public class CepServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cep = request.getParameter("inputCep");
        try{
            ViaCEP viacep=new ViaCEP();
            viacep.buscar(cep);
            System.err.println("cidade "+viacep.getLocalidade());
            
            response.setContentType("text/plain");
            response.getWriter().write(viacep.getLocalidade());
        }catch(Exception e){
            
        }
    }
}
