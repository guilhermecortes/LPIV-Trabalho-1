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
public class Estadia {
    private Integer id;
    private String placa;
    private Date entrada;
    private Date saida;
    private double valor_pago;

    public Estadia() {
    }

    public Estadia(Integer id, String placa, Date entrada, Date saida, double valor_pago) {
        this.id = id;
        this.placa = placa;
        this.entrada = entrada;
        this.saida = saida;
        this.valor_pago = valor_pago;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }
    
    
    
}
