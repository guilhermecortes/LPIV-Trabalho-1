/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.classes;

import java.util.Date;

/**
 *
 * @author guilhermecortes
 */
public class Evento {
    private Integer id;
    private String acao;
    private Date hora;
    private String usuario;
    private String estadia;

    public Evento() {
    }

    public Evento(Integer id, String acao, Date hora, String usuario, String estadia) {
        this.id = id;
        this.acao = acao;
        this.hora = hora;
        this.usuario = usuario;
        this.estadia = estadia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstadia() {
        return estadia;
    }

    public void setEstadia(String estadia) {
        this.estadia = estadia;
    }
    
}
