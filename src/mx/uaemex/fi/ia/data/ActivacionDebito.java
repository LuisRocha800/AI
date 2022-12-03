/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.Predicate;


public class ActivacionDebito implements Predicate{
    private CuentaDebito cuenta_bancaria_debito;

    public CuentaDebito getCuenta_bancaria_debito() {
        return cuenta_bancaria_debito;
    }

    public void setCuenta_bancaria_debito(CuentaDebito cuenta_bancaria) {
        this.cuenta_bancaria_debito = cuenta_bancaria;
    }
    
}

