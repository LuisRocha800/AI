/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.agentes;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mx.uaemex.fi.ia.control.ControlAgentes;
import mx.uaemex.fi.ia.data.ActivacionAhorro;
import mx.uaemex.fi.ia.data.ActivacionDebito;
import mx.uaemex.fi.ia.data.Cancelacion;
import mx.uaemex.fi.ia.data.Cliente;
import mx.uaemex.fi.ia.data.CrearAhorro;
import mx.uaemex.fi.ia.data.CuentaAhorro;
import mx.uaemex.fi.ia.data.CuentaBancaria;
import mx.uaemex.fi.ia.data.CuentaDebito;
import mx.uaemex.fi.ia.data.OntologiaBancaria;
import mx.uaemex.fi.ia.data.RespuestaAgente;
import mx.uaemex.fi.ia.data.Transaccion;
import mx.uaemex.fi.ia.data.Transferencia;
import mx.uaemex.fi.ia.vistas.diseño.Cancelar;
import mx.uaemex.fi.ia.vistas.diseño.CrearCuenta;
import mx.uaemex.fi.ia.vistas.diseño.Monto;


public class Ejecutivo extends Agent {
    
    
    
    private Codec codec = new SLCodec();
    private Ontology ontologia = OntologiaBancaria.getInstance();
    private MessageTemplate mt;
    ACLMessage respuestaAgente;
    
    private String nombreAnterior = "";
    private String nombreActual = "";
    
    private CuentaBancaria cuentaSesion;
    private CrearAhorro crea;
    
    private StringBuilder comando;
    private RespuestaAgente respuesta;
    private CrearCuenta crearCuenta;
    private Monto monto;
    private Cancelar cancelar;
    private boolean banderaCamposVacios;
    
    public void hola(){
        
    }
    
    class ComportamientoEjecutivo extends SimpleBehaviour {
 
        private boolean finished = false;

        public ComportamientoEjecutivo(Agent a) {
            super(a);
        }

        @Override
        public void action() {
            
            
            //VALIDACIÓN DE LOS JTEXTFIELD DE LAS VENTANAS
            if(!comando.toString().equals("")){
                if(comando.toString().equals(ControlAgentes.CANCELAR_CUENTA)){
                    //EN CASO DE CANCELAR LA CUENTA
                    if (cancelar.getConfirmacion().isSelected()) {
                        try {
                            cancelar_cuenta();
                            System.out.println("LAMENTAMOS MUCHO QUE HAYA CANCELADO SU CUENTA, SEGUIREMOS TRABAJANDO PARA MEJORAR");
       
                            //JOptionPane.showMessageDialog(null,"LAMENTAMOS MUCHO QUE HAYA CANCELADO SU CUENTA, SEGUIREMOS TRABAJANDO PARA MEJORAR");
                        } catch (Codec.CodecException ex) {
                            Logger.getLogger(Ejecutivo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (OntologyException ex) {
                            Logger.getLogger(Ejecutivo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                    }else{
                        respuesta.setTipo(ControlAgentes.NO_SELECCION);
                        respuesta.setMensaje(ControlAgentes.NO_SELECCION_MENSAJE);
                        comando.delete(0, comando.length());
                    }
                    
                    
                    
                }else{
                    //EN CASO DE ACTIVAR LA CUENTA. VALIDACIONES
                    
                    if(validacion_campos_vacios_crear_cuenta()){
                        respuesta.setTipo(ControlAgentes.CAMPOS_VACIOS_TIPO);
                        respuesta.setMensaje(ControlAgentes.CAMPOS_VACIOS_MENSAJE);
                        comando.delete(0, comando.length());
                    }else{
                        if(crearCuenta.getTelefono().getText().length() != 10){
                            respuesta.setTipo(ControlAgentes.TELEFONO_INVALIDO_TIPO);
                            respuesta.setMensaje(ControlAgentes.TELEFONO_INVALIDO_MENSAJE);
                            comando.delete(0, comando.length());
                            
                        }else{
                            try{
                                if(Integer.parseInt(crearCuenta.getEdad().getText())<10){
                                    respuesta.setTipo(ControlAgentes.MINIMO_EDAD_TIPO);
                                    respuesta.setMensaje(ControlAgentes.MINIMO_EDAD_MENSAJE);
                                    comando.delete(0, comando.length());
                             
                                }else{
                                    //PARA COMPROBAR SI TELEFONO ES UN NÚMERO, SINO LO ES SALTA LA EXCEPCION
                                    Long.parseLong(crearCuenta.getTelefono().getText());
                                    
                                    /*AQUI SIGNIFICA QUE LAS VALIDACIONES ESTÁN CORRECTAS
                                      Y LLAMA AL AGENTE USUARIO PARA SABER SI VA A PODER CREAR LA CUENTA
                                      ES LA COMUNICACIÓN DE LOS AGENTES
                                    */
                                    
                                    activacion_cuenta();

                                }
                            }catch(NumberFormatException e){
                                respuesta.setTipo(ControlAgentes.NO_NUMERO_TIPO);
                                respuesta.setMensaje(ControlAgentes.NO_NUMERO_MENSAJE);
                                comando.delete(0, comando.length());
                            } catch (Codec.CodecException ex) {
                                Logger.getLogger(Ejecutivo.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (OntologyException ex) {
                                Logger.getLogger(Ejecutivo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
            
        }
        
        public boolean done() {

            return finished;

        }
        
        private void activacion_cuenta() throws Codec.CodecException, OntologyException{
            
            Cliente c = new Cliente();
            c.setNombre(crearCuenta.getNombre().getText());
            c.setCorreo(crearCuenta.getCorreo().getText());
            c.setDomicilio(crearCuenta.getDomicilio().getText());
            c.setEdad(Integer.parseInt(crearCuenta.getEdad().getText()));
            c.setEstado_laboral(crearCuenta.getEstado_laboral().getItemAt(crearCuenta.getEstado_laboral().getSelectedIndex()));
            
            AID idReceptor = new AID();
            idReceptor.setLocalName("usuario");
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

            msg.setSender(getAID());
            msg.addReceiver(idReceptor);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontologia.getName());
            
            
            if(comando.toString().equals(ControlAgentes.CREAR_CUENTA_DEBITO)){
                CuentaDebito cuenta = new CuentaDebito();
                cuenta.setCliente(c);
                
                cuenta.setClabe("");
                
                cuenta.setNumero_tarjeta("");
                cuenta.setMonto(Float.parseFloat(monto.getMonto().getText()));

                ActivacionDebito a = new ActivacionDebito();
               
                a.setCuenta_bancaria_debito(cuenta);
                
                getContentManager().fillContent(msg,a);
            }else{
                CuentaAhorro cuenta = new CuentaAhorro();
                cuenta.setCliente(c);
                cuenta.setClabe("");
                cuenta.setNumero_tarjeta("");
                cuenta.setMonto((float) 0.0);

                ActivacionAhorro a = new ActivacionAhorro();
                a.setCuenta_bancaria_ahorro(cuenta);
                getContentManager().fillContent(msg,a);
                
            }
            
   
            
         
            
            send(msg);

            respuestaAgente = blockingReceive(mt);
           
            


            ContentElement ec = getContentManager().extractContent(respuestaAgente);
            
            if (respuestaAgente.getPerformative() == ACLMessage.REQUEST){
               
                //REGRESA UNA RESPUESTA AFIRMATIVA
                respuesta.setTipo(ControlAgentes.RESPUESTA_CORRECTA);
                respuesta.setMensaje("");
                respuesta.setMensaje(ControlAgentes.CUENTA_CREADA);
                
                /*for(int i=0; i<10000){
                    
                }*/
                if(comando.toString().equals(ControlAgentes.CREAR_CUENTA_AHORRO)){
                    ActivacionAhorro ac = (ActivacionAhorro) ec; 
                    System.out.println("CUENTA DE AHORRO CREADA CON EXITO");
                    System.out.println("SU CLABE INTERBANCARIA ES: "+ ac.getCuenta_bancaria_ahorro().getClabe());
                    System.out.println("SU NUMERO DE TARJETA ES: "+ac.getCuenta_bancaria_ahorro().getNumero_tarjeta());
                    System.out.println("SU MONTO INICIAL ES DE: "+ac.getCuenta_bancaria_ahorro().getMonto());
                }else{
                    ActivacionDebito ac = (ActivacionDebito) ec;
                    System.out.println("CUENTA DE DEBITO CREADA CON EXITO");
                    System.out.println("SU CLABE INTERBANCARIA ES: "+ac.getCuenta_bancaria_debito().getClabe());
                    System.out.println("SU NUMERO DE TARJETA ES: "+ac.getCuenta_bancaria_debito().getNumero_tarjeta());
                    System.out.println("SU MONTO INICIAL ES DE: "+ac.getCuenta_bancaria_debito().getMonto());
                    
                }
                
                comando.delete(0, comando.length());
                
            }else{
                if(respuestaAgente.getPerformative() == ACLMessage.REFUSE){
                    respuesta.setTipo(ControlAgentes.MONTO_MINIMO_TIPO);
                    respuesta.setMensaje(ControlAgentes.MONTO_MINIMO_MENSAJE);
                    comando.delete(0, comando.length());
                }else{
                    if(respuestaAgente.getPerformative() == ACLMessage.CANCEL){
                        // Recibido un con contenido incorrecto
                        respuesta.setTipo(ControlAgentes.CUENTA_REPETIDA_TIPO);
                        respuesta.setMensaje(ControlAgentes.CUENTA_REPETIDA_MENSAJE);
                        comando.delete(0, comando.length());
                    }else{
                        // Recibido un con contenido incorrecto
                        respuesta.setTipo(ControlAgentes.RESPUESTA_DESCONOCIDA_TIPO);
                        respuesta.setMensaje(ControlAgentes.RESPUESTA_DESCONOCIDA_MENSAJE);
                        comando.delete(0, comando.length());
                    }
                    
                }
            
            }
            
        }
        
        private void cancelar_cuenta() throws Codec.CodecException, OntologyException{
            
            Transaccion transaccion = new Transaccion();
            
            transaccion.setClabe_origen(cuentaSesion.getClabe());
            
            AID idReceptor = new AID();
            idReceptor.setLocalName("usuario");
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

            msg.setSender(getAID());
            msg.addReceiver(idReceptor);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontologia.getName());
            
            
            Cancelacion t = new Cancelacion();
            t.setTransaccion(transaccion);
                

            getContentManager().fillContent(msg,t);

            //System.out.println("Esta es cuenta cancelada = "+transaccion.getClabe_origen());
   
            
         
            
            send(msg);

            respuestaAgente = blockingReceive(mt);
            
            ContentElement ec = getContentManager().extractContent(respuestaAgente);
            
            if (respuestaAgente.getPerformative() == ACLMessage.REQUEST){
               
                //REGRESA UNA RESPUESTA AFIRMATIVA
                respuesta.setTipo(ControlAgentes.RESPUESTA_CORRECTA);
                respuesta.setMensaje("");
                respuesta.setMensaje(ControlAgentes.CUENTA_CREADA);
                
                /*for(int i=0; i<10000){
                    
                }*/
               
                Cancelacion tr = (Cancelacion) ec; 
//                System.out.println(tr.getTransaccion().getMonto());
//                System.out.println(tr.getTransaccion().getClabe_origen());
//                System.out.println(tr.getTransaccion().getClabe_destino());
                
                
                comando.delete(0, comando.length());
                
            }else{
                if(respuestaAgente.getPerformative() == ACLMessage.REFUSE){
                    respuesta.setTipo(ControlAgentes.CUENTA_NO_EXISTENTE_TIPO);
                    respuesta.setMensaje(ControlAgentes.CUENTA_NO_EXISTENTE_MENSAJE);
                    comando.delete(0, comando.length());
                }else{
                    
                    // Recibido un con contenido incorrecto
                    respuesta.setTipo(ControlAgentes.RESPUESTA_DESCONOCIDA_TIPO);
                    respuesta.setMensaje(ControlAgentes.RESPUESTA_DESCONOCIDA_MENSAJE);
                    comando.delete(0, comando.length());
                    
                    
                }
            
            }
            
        }
        
        //-----------------------------------------
        
        
        
        private boolean validacion_campos_vacios_crear_cuenta(){
            if(crearCuenta.getNombre().getText().isEmpty()){
                return true;
            }
            if(crearCuenta.getDomicilio().getText().isEmpty()){
                return true;
            }
            if(crearCuenta.getTelefono().getText().isEmpty()){
                return true;
            }
            if(crearCuenta.getCorreo().getText().isEmpty()){
                return true;
            }
            if(crearCuenta.getEdad().getText().isEmpty()){
                return true;
            }
            if(crearCuenta.getEstado_laboral().getSelectedIndex() == -1){
                return true;
            }
            return false;
        }
        
    } // Fin de la clase EnviarMensajeBehaviour
    
    @Override
    protected void setup() {
        System.out.println("INCICIANDO AGENTE EJECUTIVO");
        this.comando = (StringBuilder) this.getArguments()[0];
        this.respuesta = (RespuestaAgente) this.getArguments()[1];
        this.crearCuenta = (CrearCuenta) this.getArguments()[2];
        this.monto = (Monto) this.getArguments()[3];
        this.cancelar = (Cancelar) this.getArguments()[4];
        this.cuentaSesion = (CuentaBancaria) this.getArguments()[5];
        
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);
        
        mt = MessageTemplate.and(
        MessageTemplate.MatchLanguage(codec.getName()),
        MessageTemplate.MatchOntology(ontologia.getName()));

        
        
        
        
        ComportamientoEjecutivo EnviarBehaviour = new ComportamientoEjecutivo(this);
        addBehaviour(EnviarBehaviour);
    }
}
