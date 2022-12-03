/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.data;


public class CuentaAhorro extends CuentaBancaria{
    private float interes;
    private float impuesto_anual;
    private float descuento_transaccion;

    
    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public float getImpuesto_anual() {
        return impuesto_anual;
    }

    public void setImpuesto_anual(float impuesto_anual) {
        this.impuesto_anual = impuesto_anual;
    }

    public float getDescuento_transaccion() {
        return descuento_transaccion;
    }

    public void setDescuento_transaccion(float descuento_transaccion) {
        this.descuento_transaccion = descuento_transaccion;
    }
   

    
}
