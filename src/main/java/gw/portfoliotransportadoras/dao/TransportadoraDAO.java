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
    
    public void alterar(Transportadora transportadora) throws SQLException{
        
        Connection con=null;
        try{
		con=getConnection();
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
                
		ps.executeUpdate();
	}catch(Exception e){
            e.printStackTrace();
        }finally{
            con.close();
        }
    }
    public void inserir(Transportadora transportadora) throws SQLException{
        Connection con=null;
        try{
		con=getConnection();
		PreparedStatement ps
                        =con.prepareStatement("insert into portfolio.transportadora(nome, email, empresa, telefone, celular, whatsapp, modal, cep, estado, cidade, bairro, ruaavenida, numero, logo) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
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
                
                File arquivoLogo=transportadora.getArquivoLogo();
                FileInputStream fis = new FileInputStream(arquivoLogo);
                
                ps.setBinaryStream(14, fis, arquivoLogo.length());
                
                ps.executeUpdate();
                ps.close();
                fis.close();
                
        }catch(Exception e)
        {
            e.printStackTrace();
        }finally{
            con.close();
        }
        
    }
    public void excluir(Transportadora transportadora) throws SQLException{
        int status=0;
        Connection con=null;
	try{
		con=getConnection();
		PreparedStatement ps=con.prepareStatement("delete from portfolio.transportadora where id=?");
		ps.setInt(1,transportadora.getId());
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
            sb.append("SELECT * FROM portfolio.transportadora where nome like '");
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
            byte[] logo=null;
            
            FileOutputStream fos=null;
            String caminhoLogo="";
            
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
                logo=rs.getBytes("logo");
                
                transportadora=new Transportadora(id, nome, email, telefone, celular, whatsapp, modal, 
                        cep, estado, cidade, bairro, ruaavenida, numero, empresa);
                
                transportadora.setLogo(logo);
                
                
                
                if(transportadora.getLogo()!=null){
                    String fileName="/home/vicente/NetBeansProjects/mavenproject1/mavenproject1/portfolioTransportadoras/src/main/webapp/img/"+transportadora.getId()+".png";
                    fos=new FileOutputStream(fileName);
                    fos.write(transportadora.getLogo());
                    transportadora.setCaminhoLogo("img/"+transportadora.getId()+".png");
                }
                
                transportadoraList.add(transportadora);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TransportadoraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            con.close();
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
    
    public List<LocalizacaoUF> listLocalizacaoUFs(){
        
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
        
        try{
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select estado, count(*) from portfolio.transportadora group by estado;");
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
            
        }
        return listLocalizacaoUFs;
    }
    public List<ModalQtd> listModalQtd(){
        List<ModalQtd> listModalQtd=new ArrayList<ModalQtd>();
        
        try{
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select modal, count(*) from portfolio.transportadora group by modal;");
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
            
        }
        return listModalQtd;
    }
    
    public Integer getQtdResultados(){
        Integer qtdResultados=0;
        try{
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select count(*) from portfolio.transportadora;");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                qtdResultados=rs.getInt("count");
            }
        }catch(Exception e){
        }
        return qtdResultados;
    }
    
    public List<LocalizacaoMunicipio> listLocalizacaoMunicipio(){
        List<LocalizacaoMunicipio> listLocalizacaoMunicipio=new ArrayList<LocalizacaoMunicipio>();
        try{
            Connection con=getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select cidade, count(*) from portfolio.transportadora group by cidade;");
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
