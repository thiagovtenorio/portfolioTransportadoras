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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    public static void main(String[] args) throws SQLException{
      
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.71:5432/portfolioTransportadoras", "postgres", "postgres@2018@");

            File file = new File("/home/vicente/Documentos/desenvolvimento/apache-tomcat-8.0.27/webapps/data/firebird-logo-200.png");
            
            FileInputStream fis = new FileInputStream(file);
            PreparedStatement ps = conn.prepareStatement("update portfolio.transportadora set logo=? where id=10");
           
            ps.setBinaryStream(1, fis, file.length());
            ps.executeUpdate();
            ps.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }
}
