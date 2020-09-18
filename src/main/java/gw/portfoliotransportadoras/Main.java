/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras;

import gw.portfoliotransportadoras.dao.TransportadoraDAO;
import gw.portfoliotransportadoras.modelo.Transportadora;
import gw.portfoliotransportadoras.service.ViaCEP;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author vicente
 */
public class Main {
    public static void main(String[] args){
        try{
            ViaCEP viacep=new ViaCEP();
            viacep.buscar("50630710");
            System.err.println(viacep.getUf());
            System.err.println(viacep.getLocalidade());
            System.err.println(viacep.getBairro());
            System.err.println(viacep.getLogradouro());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
