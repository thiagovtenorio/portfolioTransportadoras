/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.dao;

import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.modelo.LocalizacaoMunicipio;
import gw.portfoliotransportadoras.modelo.LocalizacaoUF;
import gw.portfoliotransportadoras.modelo.ModalQtd;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
		con=DriverManager.getConnection("jdbc:postgresql://192.168.0.71:5432/db_portfolio_transportadoras", "postgres", "postgres@2018@");
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
    
    public void alterar(Transportadora transportadora) throws SQLException{
        StringBuilder sb=new StringBuilder();
        
        sb.append("update portfolio.tb_transportadora set nome=?,email=?,telefone=?,celular=?,whatsapp=?,modal=?,cep=?,estado=?,cidade=?,bairro=?,ruaavenida=?, numero=?, empresa=? ");
        if(transportadora.getArquivoLogo()!=null){
            sb.append(", logo=? ");
        }
        sb.append(" where id_transportadora=?");
       
        Connection con=null;
        try{
		con=getConnection();
		PreparedStatement ps=con.prepareStatement(sb.toString());
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
                
                if(transportadora.getArquivoLogo()!=null){
                    File arquivoLogo=transportadora.getArquivoLogo();
                    FileInputStream fis = new FileInputStream(arquivoLogo);
                    ps.setBinaryStream(14, fis, arquivoLogo.length());
                    ps.setInt(15,transportadora.getId());
                }else{
                    ps.setInt(14,transportadora.getId());
                }
                
		ps.executeUpdate();
	}catch(Exception e){
            e.printStackTrace();
        }finally{
            con.close();
        }
    }
    public void inserir(Transportadora transportadora) throws SQLException{
        StringBuilder sb=new StringBuilder();
        sb.append("insert into portfolio.tb_transportadora(nome, email, empresa, telefone, celular, whatsapp, modal, cep, estado, cidade, bairro, ruaavenida, numero ");
        if(transportadora.getArquivoLogo()!=null){
            sb.append(", logo)");
            sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        }else{
            sb.append(") values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        }
        
        Connection con=null;
        try{
		con=getConnection();
		PreparedStatement ps
                        =con.prepareStatement(sb.toString());
                
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
                
                if(transportadora.getArquivoLogo()!=null){
                    File arquivoLogo=transportadora.getArquivoLogo();
                    FileInputStream fis = new FileInputStream(arquivoLogo);
                    ps.setBinaryStream(14, fis, arquivoLogo.length());
                    
                    ps.executeUpdate();
                    ps.close();
                    fis.close();
                }else{
                    ps.executeUpdate();
                    ps.close();
                }
                
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            con.close();
        }
        
    }
    public void excluir(Integer transportadoraId) throws SQLException{
        int status=0;
        Connection con=null;
	try{
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from portfolio.tb_transportadora where id_transportadora=?");
		ps.setInt(1, transportadoraId);
		ps.executeUpdate();
	}catch(Exception e){
            e.printStackTrace();
        }finally{
            con.close();
        }
    }
    
    public Transportadora getTransportadoraPorId(Integer id) throws SQLException{
        Transportadora transportadoraBanco=null;
        Connection con=null;
        try{
            con=getConnection();
            PreparedStatement ps=con.prepareStatement("select * from portfolio.tb_transportadora where id_transportadora=?");
	    ps.setInt(1,id);
	    ResultSet rs=ps.executeQuery();
            
            FileOutputStream fos=null;
            String caminhoLogo="";
            
            while(rs.next()){
               transportadoraBanco=new Transportadora();
               transportadoraBanco.setId(rs.getInt("id_transportadora"));
               transportadoraBanco.setNome(rs.getString("nome"));
               transportadoraBanco.setEmail(rs.getString("email"));
               transportadoraBanco.setTelefone(rs.getString("telefone"));
               transportadoraBanco.setCelular(rs.getString("celular"));
               transportadoraBanco.setWhatsapp(rs.getString("whatsapp"));
               transportadoraBanco.setModal(rs.getString("modal"));
               transportadoraBanco.setCep(rs.getString("cep"));
               transportadoraBanco.setEstado(rs.getString("estado"));
               transportadoraBanco.setCidade(rs.getString("cidade"));
               transportadoraBanco.setBairro(rs.getString("bairro"));
               transportadoraBanco.setRuaAvenida(rs.getString("ruaavenida"));
               transportadoraBanco.setNumero(rs.getInt("numero"));
               transportadoraBanco.setEmpresa(rs.getString("empresa"));
               transportadoraBanco.setLogo(rs.getBytes("logo"));
               
               if(transportadoraBanco.getLogo()!=null){
                    String fileName="/home/vicente/NetBeansProjects/mavenproject1/mavenproject1/portfolioTransportadoras/src/main/webapp/img/"+transportadoraBanco.getId()+".png";
                    fos=new FileOutputStream(fileName);
                    fos.write(transportadoraBanco.getLogo());
                    transportadoraBanco.setCaminhoLogo("img/"+transportadoraBanco.getId()+".png");
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            con.close();
        }
        
        return transportadoraBanco;
    }
    
    
    public List<Transportadora> pesquisarPorFiltro(FiltroTransportadora filtro) throws SQLException, FileNotFoundException, IOException{
       List<Transportadora> transportadoraList= new ArrayList<Transportadora>();
       Connection con=null;
        try {
            StringBuilder sb=new StringBuilder();
            sb.append("SELECT * FROM portfolio.tb_transportadora where nome like '");
            sb.append(filtro.getNome());
            sb.append("%'");
            
            if(filtro.getEstado()!=null && filtro.getEstado().length() > 0){
                sb.append("and estado='");
                sb.append(filtro.getEstado());
                sb.append("'");
            }
            if(filtro.getCidade()!=null && filtro.getCidade().length() > 0){
                sb.append(" and cidade='");
                sb.append(filtro.getCidade());
                sb.append("'");
            }
            if(filtro.getModal()!=null && filtro.getModal().length() > 0){
                sb.append(" and modal='");
                sb.append(filtro.getModal());
                sb.append("'");
            }
            
            con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement(sb.toString());
            
            ResultSet rs=ps.executeQuery();
            
            Transportadora transportadora=null;
            
            FileOutputStream fos=null;
            String caminhoLogo="";
            
            while(rs.next())
            {
                transportadora=new Transportadora();
                transportadora.setId(rs.getInt("id_transportadora"));
                transportadora.setNome(rs.getString("nome"));
                transportadora.setEmail(rs.getString("email"));
                transportadora.setTelefone(rs.getString("telefone"));
                transportadora.setCelular(rs.getString("celular"));
                transportadora.setWhatsapp(rs.getString("whatsapp"));
                transportadora.setModal(rs.getString("modal"));
                transportadora.setCep(rs.getString("cep"));
                transportadora.setEstado(rs.getString("estado"));
                transportadora.setCidade(rs.getString("cidade"));
                transportadora.setBairro(rs.getString("bairro"));
                transportadora.setRuaAvenida(rs.getString("ruaavenida"));
                transportadora.setNumero(rs.getInt("numero"));
                transportadora.setEmpresa(rs.getString("empresa"));
                transportadora.setLogo(rs.getBytes("logo"));
                
                if(transportadora.getLogo()!=null){
                    String fileName="/home/vicente/NetBeansProjects/mavenproject1/mavenproject1/portfolioTransportadoras/src/main/webapp/img/"+transportadora.getId()+".png";
                    fos=new FileOutputStream(fileName);
                    fos.write(transportadora.getLogo());
                    
                    transportadora.setCaminhoLogo("img/"+transportadora.getId()+".png");
                }
                
                transportadoraList.add(transportadora);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            con.close();
        }
        return transportadoraList;
    }
    
    public List<LocalizacaoUF> listLocalizacaoUFs() throws SQLException{
        
        List<LocalizacaoUF> listLocalizacaoUFs=new ArrayList<LocalizacaoUF>();
        
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
    
        try(Connection con=getConnection();){
            
            PreparedStatement ps;
            ps = con.prepareStatement("select estado, count(*) from portfolio.tb_transportadora group by estado;");
            ResultSet rs=ps.executeQuery();
            
            StringBuilder sb=new StringBuilder();
            String sigla="";
            String nomeCompleto="";
            
            
            LocalizacaoUF localizacaoUF=new LocalizacaoUF();
            
            while(rs.next())
            {
               sigla=rs.getString("estado"); 
               nomeCompleto=siglaNomeCompleto.get(sigla);
               localizacaoUF=new LocalizacaoUF();
               localizacaoUF.setSigla(sigla);
               localizacaoUF.setNomeCompleto(nomeCompleto);
               localizacaoUF.setQtdCadastros(rs.getInt("count"));
               listLocalizacaoUFs.add(localizacaoUF);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listLocalizacaoUFs;
    }
    public List<ModalQtd> listModalQtd(){
        List<ModalQtd> listModalQtd=new ArrayList<ModalQtd>();
        
        try(Connection con=getConnection();){
            
            PreparedStatement ps;
            ps = con.prepareStatement("select modal, count(*) from portfolio.tb_transportadora group by modal;");
            ResultSet rs=ps.executeQuery();
            
            ModalQtd modalQtd=new ModalQtd();
            
            while(rs.next())
            {
                modalQtd.setDescricao(rs.getString("modal"));
                modalQtd.setQtdCadastros(rs.getInt("count"));
                listModalQtd.add(modalQtd);
                modalQtd=new ModalQtd();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listModalQtd;
    }
    
    public Integer getQtdResultados(){
        Integer qtdResultados=0;
        try(Connection con=getConnection();){
            
            PreparedStatement ps;
            ps = con.prepareStatement("select count(*) from portfolio.tb_transportadora;");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                qtdResultados=rs.getInt("count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return qtdResultados;
    }
    
    public List<LocalizacaoMunicipio> listLocalizacaoMunicipio(){
        List<LocalizacaoMunicipio> listLocalizacaoMunicipio=new ArrayList<LocalizacaoMunicipio>();
        try(Connection con=getConnection();){
            
            PreparedStatement ps;
            ps = con.prepareStatement("select cidade, count(*) from portfolio.tb_transportadora group by cidade;");
            ResultSet rs=ps.executeQuery();
            
            LocalizacaoMunicipio localizacaoMunicipio=new LocalizacaoMunicipio();
            
            while(rs.next())
            {
                localizacaoMunicipio.setNome(rs.getString("cidade"));
                localizacaoMunicipio.setQtdCadastros(rs.getInt("count"));
                listLocalizacaoMunicipio.add(localizacaoMunicipio);
                localizacaoMunicipio=new LocalizacaoMunicipio();
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listLocalizacaoMunicipio;
    }
}
