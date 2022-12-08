/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.error;

public class AgenteException extends Exception {

    public AgenteException() {
    }

    public AgenteException(String message) {
        super(message);
    }

    public AgenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgenteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
