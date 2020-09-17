/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gw.portfoliotransportadoras.modelo;

/**
 *
 * @author vicente
 */
public class LocalizacaoUF {
    private String sigla;
    private String nomeCompleto;
    private Integer qtdCadastros;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getQtdCadastros() {
        return qtdCadastros;
    }

    public void setQtdCadastros(Integer qtdCadastros) {
        this.qtdCadastros = qtdCadastros;
    }
}
