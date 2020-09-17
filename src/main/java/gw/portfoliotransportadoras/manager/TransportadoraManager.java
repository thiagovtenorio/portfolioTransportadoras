/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.manager;

import gw.portfoliotransportadoras.dao.TransportadoraDAO;
import gw.portfoliotransportadoras.filtro.FiltroTransportadora;
import gw.portfoliotransportadoras.modelo.LocalizacaoUF;
import gw.portfoliotransportadoras.modelo.Transportadora;
import java.util.List;

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
        return transportadoraDAO.pesquisarPorFiltro(filtro);
    }
    
    public void adicionarTransportadora(Transportadora novaTransportadora){
        transportadoraDAO.inserir(novaTransportadora);
    }
    public void alterarTransportadora(Transportadora transportadoraAtual){
        transportadoraDAO.alterar(transportadoraAtual);
    }
    public void excluirTransportadora(Transportadora transportadora){
        transportadoraDAO.excluir(transportadora);
    }
    public List<Transportadora> getList(){
        return transportadoraDAO.getList();
    }
    public Transportadora getTransportadoraPorId(Integer id){
        return transportadoraDAO.getTransportadoraPorId(id);
    }
    public List<LocalizacaoUF> listLocalizacaoUFs(){
        return transportadoraDAO.listLocalizacaoUFs();
    }
}
