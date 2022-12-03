/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.control;

import mx.uaemex.fi.ia.control.data.Data;
import mx.uaemex.fi.ia.error.AgenteException;
import mx.uaemex.fi.ia.vistas.diseño.CuentaPrincipal;

/**
 *
 * @author angel
 */
public class ControlVentanaPrincipal implements Control {
    private Control padre;
    
    
    @Override
    public void ejecutaComando(String comando, Data d) throws AgenteException {
        switch(comando){
            case CuentaPrincipal.RETIRAR:
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_RETIRAR, null);
                break;
            case CuentaPrincipal.TRANSFERIR:
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_TRANSFERIR, null);
                break;
            case CuentaPrincipal.CANCELAR:
                this.padre.ejecutaComando(ControlFlujos.MOSTRAR_CANCELAR, null);
                break;
            case CuentaPrincipal.CERRAR_SESION:
                this.padre.ejecutaComando(ControlFlujos.REGRESAR_VENTANA_PRINCIPAL_LOGIN, null);
                break;
            default:
                this.padre.ejecutaComando(ControlFlujos.CASO_VENTANA_PRINCIPAL_DESCONOCIDO, null);
        }
    }

    @Override
    public void setPadre(Control control) {
        this.padre = control;
    }
}
