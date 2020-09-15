/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.manager;

import gw.portfoliotransportadoras.dao.TransportadoraDAO;
import gw.portfoliotransportadoras.modelo.Transportadora;

/**
 *
 * @author vicente
 */
public class TransportadoraManager {
    private TransportadoraDAO transportadoraDAO;

    public TransportadoraManager() {
        this.transportadoraDAO = new TransportadoraDAO();
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
}
