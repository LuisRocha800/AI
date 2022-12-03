/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.AgentAction;


public class BorrarDebito implements AgentAction {
    private CuentaDebito cuenta_bancaria;

    public CuentaDebito getCuenta_bancaria() {
        return cuenta_bancaria;
    }

    public void setCuenta_bancaria(CuentaDebito cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
    }
    
}