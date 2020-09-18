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
import org.json.JSONObject;


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
            
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("uf", viacep.getUf());
            jsonObj.put("localidade", viacep.getLocalidade());
            jsonObj.put("bairro", viacep.getBairro());
            jsonObj.put("logradouro", viacep.getLogradouro());
            
            response.setContentType("text/plain");
            System.err.println("jsonObj "+jsonObj.toString());
            response.getWriter().write(jsonObj.toString());
            
        }catch(Exception e){
            
        }
    }
}
