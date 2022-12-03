/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.control;

import mx.uaemex.fi.ia.control.data.Data;
import mx.uaemex.fi.ia.error.AgenteException;

/**
 *
 * @author angel
 */
public interface Control {
    public void ejecutaComando(String comando, Data d) throws AgenteException;
    public void setPadre(Control control);
}
