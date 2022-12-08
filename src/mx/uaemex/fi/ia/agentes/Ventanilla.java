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
import mx.uaemex.fi.ia.control.ControlAgentes;
import mx.uaemex.fi.ia.data.ActivacionAhorro;
import mx.uaemex.fi.ia.data.ActivacionDebito;
import mx.uaemex.fi.ia.data.Cliente;
import mx.uaemex.fi.ia.data.CrearAhorro;
import mx.uaemex.fi.ia.data.CuentaBancaria;
import mx.uaemex.fi.ia.data.OntologiaBancaria;
import mx.uaemex.fi.ia.data.RespuestaAgente;
import mx.uaemex.fi.ia.data.Retiracion;
import mx.uaemex.fi.ia.data.Retiro;
import mx.uaemex.fi.ia.data.Transaccion;
import mx.uaemex.fi.ia.data.Transferencia;
import mx.uaemex.fi.ia.vistas.diseño.Cancelar;
import mx.uaemex.fi.ia.vistas.diseño.CrearCuenta;
import mx.uaemex.fi.ia.vistas.diseño.Monto;
import mx.uaemex.fi.ia.vistas.diseño.Retirar;
import mx.uaemex.fi.ia.vistas.diseño.Transferir;


public class Ventanilla extends Agent {
    
    
    
    private Codec codec = new SLCodec();
    private Ontology ontologia = OntologiaBancaria.getInstance();
    private MessageTemplate mt;
    ACLMessage respuestaAgente;
    
    private String nombreAnterior = "";
    private String nombreActual = "";
    
    private CuentaBancaria cuentaSession;
    private CrearAhorro crea;
    
    private StringBuilder comando;
    private RespuestaAgente respuesta;
    private Transferir transferir;
    private Retirar retirar;
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
            
            
            
            if(!comando.toString().equals("")){
                if(comando.toString().equals(ControlAgentes.TRANSFERIR_CUENTAS)){
                    //EN CASO DE TRANSFERIR
                    if(validacion_campos_vacios_transferir()){
                        respuesta.setTipo(ControlAgentes.CAMPOS_VACIOS_TIPO);
                        respuesta.setMensaje(ControlAgentes.CAMPOS_VACIOS_MENSAJE);
                        comando.delete(0, comando.length());
                    }else{
                        
                        try{
                            if(cuentaSession.getMonto()>=Float.parseFloat(transferir.getMonto().getText())){
                                transferir_cuentas();
                                
                            }else{
                                respuesta.setTipo(ControlAgentes.MONTO_INSUFICIENTE_TIPO);
                                respuesta.setMensaje(ControlAgentes.MONTO_INSUFICIENTE_MENSAJE);
                                comando.delete(0, comando.length());
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
                    
                    
                }else{
                    //EN CASO DE RETIRAR
                    if(comando.toString().equals(ControlAgentes.RETIRAR_CUENTAS)){
                    
                    if(validacion_campos_vacios_retirar()){
                        respuesta.setTipo(ControlAgentes.CAMPOS_VACIOS_TIPO);
                        respuesta.setMensaje(ControlAgentes.CAMPOS_VACIOS_MENSAJE);
                        comando.delete(0, comando.length());
                    }else{
                        
                        try{
                            if(cuentaSession.getMonto()>=Float.parseFloat(retirar.getMonto().getText())){
                                retirar_cuentas();
                                
                            }else{
                                respuesta.setTipo(ControlAgentes.MONTO_INSUFICIENTE_TIPO);
                                respuesta.setMensaje(ControlAgentes.MONTO_INSUFICIENTE_MENSAJE);
                                comando.delete(0, comando.length());
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
        
        private void transferir_cuentas() throws Codec.CodecException, OntologyException{
            
            Transaccion transaccion = new Transaccion();
            transaccion.setClabe_destino(transferir.getClabe().getText());
            transaccion.setMonto(Float.parseFloat(transferir.getMonto().getText()));
            transaccion.setClabe_origen(cuentaSession.getClabe());
            
            AID idReceptor = new AID();
            idReceptor.setLocalName("usuario");
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

            msg.setSender(getAID());
            msg.addReceiver(idReceptor);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontologia.getName());
            
            
            Transferencia t = new Transferencia();
            t.setTransaccion(transaccion);
                

            getContentManager().fillContent(msg,t);

            
   
            
         
            
            send(msg);

            respuestaAgente = blockingReceive(mt);
            
            ContentElement ec = getContentManager().extractContent(respuestaAgente);
            
            if (respuestaAgente.getPerformative() == ACLMessage.REQUEST){
               
                //REGRESA UNA RESPUESTA AFIRMATIVA
                respuesta.setTipo(ControlAgentes.RESPUESTA_CORRECTA);
                respuesta.setMensaje("");
                
                
                /*for(int i=0; i<10000){
                    
                }*/
               
                Transferencia tr = (Transferencia) ec; 
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
        
         private void retirar_cuentas() throws Codec.CodecException, OntologyException{
            
            Retiracion retiracion = new Retiracion();
            Transaccion transa = new Transaccion();
            transa.setClabe_origen(cuentaSession.getClabe());
            transa.setMonto(Float.parseFloat(retirar.getMonto().getText()));

            retiracion.setTransaccion(transa);
            
            AID idReceptor = new AID();
            idReceptor.setLocalName("usuario");
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);

            msg.setSender(getAID());
            msg.addReceiver(idReceptor);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontologia.getName());
            
            
            //Retiro r = new Retiro();
            //r.setRetiracion(retiracion);
                

            getContentManager().fillContent(msg,retiracion);

            
   
            
         
            
            send(msg);

            respuestaAgente = blockingReceive(mt);
            
            ContentElement ec = getContentManager().extractContent(respuestaAgente);
            
            if (respuestaAgente.getPerformative() == ACLMessage.REQUEST){
               
                //REGRESA UNA RESPUESTA AFIRMATIVA
                respuesta.setTipo(ControlAgentes.RESPUESTA_CORRECTA);
                respuesta.setMensaje("");
                
                
                /*for(int i=0; i<10000){
                    
                }*/
               
                Retiracion re = (Retiracion) ec; 
//                System.out.println(re.getTransaccion().getMonto());
//                System.out.println("Si es este xd ");
                
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
        
        
        private boolean validacion_campos_vacios_transferir(){
            if(transferir.getMonto().getText().equals("")){
                return true;
            }
            if(transferir.getClabe().getText().equals("")){
                return true;
            }
            return false;
        }
        
        private boolean validacion_campos_vacios_retirar(){
            if(retirar.getMonto().getText().equals("")){
                return true;
            }
            return false;
        }
        
        
        
    } // Fin de la clase EnviarMensajeBehaviour
    
    @Override
    protected void setup() {
        System.out.println("INICIANDO AGENTE VENTANILLA");
        this.comando = (StringBuilder) this.getArguments()[0];
        this.respuesta = (RespuestaAgente) this.getArguments()[1];
        this.cuentaSession = (CuentaBancaria) this.getArguments()[2];
        this.transferir = (Transferir) this.getArguments()[3];
        this.retirar = (Retirar) this.getArguments()[4];
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontologia);
        
        mt = MessageTemplate.and(
        MessageTemplate.MatchLanguage(codec.getName()),
        MessageTemplate.MatchOntology(ontologia.getName()));

        
        
        
        ComportamientoEjecutivo EnviarBehaviour = new ComportamientoEjecutivo(this);
        addBehaviour(EnviarBehaviour);
    }
}
