/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.manager;

import gw.portfoliotransportadoras.dao.TransportadoraDAO;
import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.modelo.LocalizacaoMunicipio;
import gw.portfoliotransportadoras.modelo.LocalizacaoUF;
import gw.portfoliotransportadoras.modelo.ModalQtd;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vicente
 */
public class TransportadoraManager {
    private TransportadoraDAO transportadoraDAO;

    public TransportadoraManager() {
        this.transportadoraDAO = new TransportadoraDAO();
    }
    
    public List<Transportadora> pesquisarPorFiltro(FiltroTransportadora filtro){
        List<Transportadora> listTransportadora=new ArrayList<Transportadora>();
        try{
            listTransportadora=transportadoraDAO.pesquisarPorFiltro(filtro);
        }catch(Exception e){
            e.printStackTrace();
        }
        return listTransportadora;
    }
    
    public void adicionarTransportadora(Transportadora novaTransportadora){
        try{
            transportadoraDAO.inserir(novaTransportadora);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void alterarTransportadora(Transportadora transportadoraAtual){
        try{
            transportadoraDAO.alterar(transportadoraAtual);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void excluirTransportadora(Integer transportadoraId){
        try{
            transportadoraDAO.excluir(transportadoraId);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Transportadora getTransportadoraPorId(Integer id){
        Transportadora transportadora=null;
        try{
            transportadora=transportadoraDAO.getTransportadoraPorId(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return transportadora;
    }
    public List<LocalizacaoUF> listLocalizacaoUFs(){
        try {
            return transportadoraDAO.listLocalizacaoUFs();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    public List<LocalizacaoMunicipio> listLocalizacaoMunicipio(){
        return transportadoraDAO.listLocalizacaoMunicipio();
    }
    public Integer getQtdResultados(){
        return transportadoraDAO.getQtdResultados();
    }
    
    public List<ModalQtd> listModalQtd(){
        return transportadoraDAO.listModalQtd();
    }
}
