/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.dao;

import gw.portfoliotransportadoras.modelo.Transportadora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicente
 */
public class TransportadoraDAO {
    public static Connection getConnection(){
	Connection con=null;
	try{
		Class.forName("org.postgresql.Driver");
		con=DriverManager.getConnection("jdbc:postgresql://192.168.0.71:5432/portfolioTransportadoras", "postgres", "postgres@2018@");
	}catch(Exception e){
            System.out.println(e);
        }
	return con;
    }
    
    public static int inserir(Transportadora transportadora){
        int status=0;
        
        try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into portfolio.transportadora(nome) values(?)");
		ps.setString(1, transportadora.getNome());
                status=ps.executeUpdate();
                
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    
    public static List<Transportadora> getList(){
        List<Transportadora> transportadoraList= new ArrayList<Transportadora>();
        
        try {
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM portfolio.transportadora");
            ResultSet rs=ps.executeQuery();
            
            Transportadora transportadora=null;
            while(rs.next())
            {
                transportadora=new Transportadora();
                transportadora.setNome(rs.getString("nome"));
                transportadoraList.add(transportadora);
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TransportadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        return transportadoraList;
    }
}
