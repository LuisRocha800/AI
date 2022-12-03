/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;

public class CuentaDebito extends CuentaBancaria{
    private float saldo_minimo;
    private float comision;

    
    public float getSaldo_minimo() {
        return saldo_minimo;
    }

    public void setSaldo_minimo(float saldo_minimo) {
        this.saldo_minimo = saldo_minimo;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
    

    
}
