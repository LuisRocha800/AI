/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.AgentAction;

public class Transferir implements AgentAction{
    private CuentaBancaria cuenta_bancaria;

    public CuentaBancaria getCuenta_bancaria() {
        return cuenta_bancaria;
    }

    public void setCuenta_bancaria(CuentaBancaria cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
    }
    
}
