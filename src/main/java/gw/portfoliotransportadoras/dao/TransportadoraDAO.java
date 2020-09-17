/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.dao;

import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    /**
     Integer id, String nome, String email, String telefone, String celular, String whatsapp, 
            String modal, String cep, String estado, String cidade, String bairro, String ruaAvenida, 
            Integer numero, String empres
     * @param transportadora
     * @return a*/
    
    public int alterar(Transportadora transportadora){
        int status=0;
        System.err.println("atualizar transp id: "+transportadora.getId());
        try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("update portfolio.transportadora set nome=?,email=?,telefone=?,celular=?,whatsapp=?,modal=?,cep=?,estado=?,cidade=?,bairro=?,ruaavenida=?, numero=?, empresa=? where id=?");
		ps.setString(1,transportadora.getNome());
                ps.setString(2,transportadora.getEmail());
                ps.setString(3,transportadora.getTelefone());
                ps.setString(4,transportadora.getCelular());
                ps.setString(5,transportadora.getWhatsapp());
                ps.setString(6,transportadora.getModal());
                ps.setString(7,transportadora.getCep());
                ps.setString(8,transportadora.getEstado());
                ps.setString(9,transportadora.getCidade());
                ps.setString(10,transportadora.getBairro());
                ps.setString(11,transportadora.getRuaAvenida());
                ps.setInt(12,transportadora.getNumero());
                ps.setString(13,transportadora.getEmpresa());
                ps.setInt(14,transportadora.getId());
                
		status=ps.executeUpdate();
	}catch(Exception e){
            System.out.println(e);
        }
        return status;
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
    public int excluir(Transportadora transportadora){
        int status=0;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from portfolio.transportadora where id=?");
		ps.setInt(1,transportadora.getId());
		status=ps.executeUpdate();
	}catch(Exception e){
            System.out.println(e);
        }
        return status;
    }
    
    public Transportadora getTransportadoraPorId(Integer id){
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
    
    
    public List<Transportadora> pesquisarPorFiltro(FiltroTransportadora filtro){
       List<Transportadora> transportadoraList= new ArrayList<Transportadora>();
       
        try {
            StringBuilder sb=new StringBuilder();
            sb.append("SELECT * FROM portfolio.transportadora where nome like '");
            sb.append(filtro.getNome());
            sb.append("%'");
            
            if(filtro.getEstado().length() > 0){
                sb.append("and estado='");
                sb.append(filtro.getEstado());
                sb.append("'");
            }
            if(filtro.getCidade().length() > 0){
                sb.append(" and cidade='");
                sb.append(filtro.getCidade());
                sb.append("'");
            }
            if(filtro.getModal().length() > 0){
                sb.append(" and modal='");
                sb.append(filtro.getModal());
                sb.append("'");
            }
            
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement(sb.toString());
            
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
    
    public List<String> listLocalizacaoUFs(){
        List<String> listLocalizacaoUFs=new ArrayList<String>();
        HashMap<String, String> siglaNomeCompleto=new HashMap<String,String>();
        
        siglaNomeCompleto.put("AC", "Acre");
        siglaNomeCompleto.put("AL", "Alagoas");
        siglaNomeCompleto.put("AP", "Amapá");
        siglaNomeCompleto.put("AM", "Amazonas");
        siglaNomeCompleto.put("BA", "Bahia");
        siglaNomeCompleto.put("CE", "Ceará");
        siglaNomeCompleto.put("DF", "Distrito Federal");
        siglaNomeCompleto.put("ES", "Espírito Santo");
        siglaNomeCompleto.put("GO", "Goiás");
        siglaNomeCompleto.put("MA", "Maranhão");
        siglaNomeCompleto.put("MT", "Mato Grosso");
        siglaNomeCompleto.put("MS", "Mato Grosso do Sul");
        siglaNomeCompleto.put("MG", "Minas Gerais");
        siglaNomeCompleto.put("PA", "Pará");
        siglaNomeCompleto.put("PB", "Paraíba");
        siglaNomeCompleto.put("PR", "Paraná");
        siglaNomeCompleto.put("PE", "Pernambuco");
        siglaNomeCompleto.put("PI", "Piauí");
        siglaNomeCompleto.put("RJ", "Rio de Janeiro");
        siglaNomeCompleto.put("RN", "Rio Grande do Norte");
        siglaNomeCompleto.put("RS", "Rio Grande do Sul");
        siglaNomeCompleto.put("RO", "Rondônia");
        siglaNomeCompleto.put("RR", "Roraima");
        siglaNomeCompleto.put("SC", "Santa Catarina");
        siglaNomeCompleto.put("SP", "São Paulo");
        siglaNomeCompleto.put("SE", "Sergipe");
        siglaNomeCompleto.put("TO", "Tocantins");
        
        try{
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select estado, count(*) from portfolio.transportadora group by estado;");
            ResultSet rs=ps.executeQuery();
            
            StringBuilder sb=new StringBuilder();
            String nomeCompleto="";
            
            while(rs.next())
            {
               nomeCompleto=siglaNomeCompleto.get(rs.getString("estado"));
               
               sb.append(nomeCompleto);
               sb.append("(");
               sb.append(rs.getString("count"));
               sb.append(")");
               listLocalizacaoUFs.add(sb.toString());
               sb=new StringBuilder();
            }
        }catch(Exception e){
            
        }
        return listLocalizacaoUFs;
    }
}
