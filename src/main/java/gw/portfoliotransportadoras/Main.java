/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras;

import gw.portfoliotransportadoras.dao.TransportadoraDAO;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicente
 */
public class Main {
    public static void main(String[] args){
   
       List<Transportadora> transportadoraList=TransportadoraDAO.getList();
       
       for(Transportadora t: transportadoraList){
           System.err.println(t.getNome());
       }
    }
}
