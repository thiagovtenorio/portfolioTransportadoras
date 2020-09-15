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
    
    public int inserir(Transportadora transportadora){
        int status=0;
        
        try{
		Connection con=getConnection();
		PreparedStatement ps
                        =con.prepareStatement("insert into portfolio.transportadora(nome, email, empresa, telefone, celular, whatsapp, modal, cep, estado, cidade, bairro, ruaavenida, numero) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
		ps.setString(1, transportadora.getNome());
                ps.setString(2, transportadora.getEmail());
                ps.setString(3, transportadora.getEmpresa());
                ps.setString(4, transportadora.getTelefone());
                ps.setString(5, transportadora.getCelular());
                ps.setString(6, transportadora.getWhatsapp());
                ps.setString(7, transportadora.getModal());
                ps.setString(8, transportadora.getCep());
                ps.setString(9, transportadora.getEstado());
                ps.setString(10, transportadora.getCidade());
                ps.setString(11, transportadora.getBairro());
                ps.setString(12, transportadora.getRuaAvenida());
                ps.setInt(13, transportadora.getNumero());
                
                
                status=ps.executeUpdate();
                
                con.close();
                
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
    public static Transportadora getTransportadoraPorId(Integer id){
        Transportadora transportadoraBanco=null;
        try{
            Connection con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from portfolio.transportadora where id=?");
	    ps.setInt(1,id);
	    ResultSet rs=ps.executeQuery();
            
            Integer transportadoraId=null;
            String nome=null;
            String email=null;
            String telefone=null;
            String celular=null; 
            String whatsapp=null; 
            String modal=null;
            String cep=null;
            String estado=null;
            String cidade=null;
            String bairro=null;
            String ruaAvenida=null;
            Integer numero=null;
            String empresa=null;
            
            while(rs.next()){
               transportadoraId=rs.getInt("id");
               nome=rs.getString("nome");
               email=rs.getString("email");
               telefone=rs.getString("telefone");
               celular=rs.getString("celular");
               whatsapp=rs.getString("whatsapp");
               modal=rs.getString("modal");
               cep=rs.getString("cep");
               estado=rs.getString("estado");
               cidade=rs.getString("cidade");
               bairro=rs.getString("bairro");
               ruaAvenida=rs.getString("ruaavenida");
               numero=rs.getInt("numero");
               empresa=rs.getString("empresa");
               
               transportadoraBanco=new Transportadora(transportadoraId, nome, email, telefone, celular, whatsapp, modal, cep, estado, cidade, bairro, ruaAvenida, numero, empresa);
               
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return transportadoraBanco;
    }
    
    public static List<Transportadora> getList(){
        List<Transportadora> transportadoraList= new ArrayList<Transportadora>();
        
        try {
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT * FROM portfolio.transportadora");
            ResultSet rs=ps.executeQuery();
            
            Transportadora transportadora=null;
            Integer id=null;
            String nome=null;
            String email=null;
            String telefone=null;
            String celular=null;
            String whatsapp=null;
            String modal=null;
            String cep=null;
            String estado=null;
            String cidade=null;
            String bairro=null;
            String ruaavenida=null;
            Integer numero=null;
            String empresa=null;
            
            while(rs.next())
            {
                id=rs.getInt("id");
                nome=(String)rs.getString("nome");
                email=(String)rs.getString("email");
                telefone=(String)rs.getString("telefone");
                celular=(String)rs.getString("celular");
                whatsapp=(String)rs.getString("whatsapp");
                modal=(String)rs.getString("modal");
                cep=(String)rs.getString("cep");
                estado=(String)rs.getString("estado");
                cidade=(String)rs.getString("cidade");
                bairro=(String)rs.getString("bairro");
                ruaavenida=(String)rs.getString("ruaavenida");
                numero=rs.getInt("numero");
                empresa=(String)rs.getString("empresa");
                
                transportadora=new Transportadora(id, nome, email, telefone, celular, whatsapp, modal, 
                        cep, estado, cidade, bairro, ruaavenida, numero, empresa);
                
                transportadoraList.add(transportadora);
            }
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TransportadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
	
        return transportadoraList;
    }
}
