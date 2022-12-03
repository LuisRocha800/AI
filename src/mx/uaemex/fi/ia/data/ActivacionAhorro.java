/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

import jade.content.Predicate;


public class ActivacionAhorro implements Predicate{
    private CuentaAhorro cuenta_bancaria_ahorro;

    public CuentaAhorro getCuenta_bancaria_ahorro() {
        return cuenta_bancaria_ahorro;
    }

    public void setCuenta_bancaria_ahorro(CuentaAhorro cuenta_bancaria_ahorro) {
        this.cuenta_bancaria_ahorro = cuenta_bancaria_ahorro;
    }
    
}
