/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemex.fi.ia.vistas.diseño;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mx.uaemex.fi.ia.data.BooleanData;
import mx.uaemex.fi.ia.error.AgenteException;

/**
 *
 * @author Elia
 */
public class CrearCuenta extends VentanaAbstracta {
    
    public static final String CREAR_CUENTA_DATOS = "Crear Cuenta con Datos Introducidos";
    
    public static final String REGRESAR = "Regresar de Crear Cuenta a Productos";
    
    private BooleanData es_cuenta_debito;
    
    
    /**
     * Creates new form CrearCuenta
     */
    public CrearCuenta() {
        initComponents();
         setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        edad = new javax.swing.JTextField();
        estado_laboral = new javax.swing.JComboBox<>();
        Continuar1 = new javax.swing.JButton();
        correo = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        domicilio = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon(getClass().getResource("/mx/uaemex/fi/ia/recursos/banco.png")).getImage());
        setPreferredSize(new java.awt.Dimension(447, 463));
        setResizable(false);

        jLabel2.setBackground(new java.awt.Color(220, 27, 251));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(220, 27, 251));
        jButton1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("REGRESAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 100, 30));

        edad.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        edad.setForeground(new java.awt.Color(220, 27, 251));
        edad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edadActionPerformed(evt);
            }
        });
        jPanel2.add(edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 200, -1));

        estado_laboral.setBackground(new java.awt.Color(193, 209, 248));
        estado_laboral.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        estado_laboral.setForeground(new java.awt.Color(220, 27, 251));
        estado_laboral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Trabajador(a)", "Desempleado(a)", "Estudiante", "Pensionado(a)" }));
        estado_laboral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estado_laboralActionPerformed(evt);
            }
        });
        jPanel2.add(estado_laboral, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 170, 23));

        Continuar1.setBackground(new java.awt.Color(220, 27, 251));
        Continuar1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        Continuar1.setForeground(new java.awt.Color(255, 255, 255));
        Continuar1.setText("CONTINUAR");
        Continuar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Continuar1ActionPerformed(evt);
            }
        });
        jPanel2.add(Continuar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 110, 30));

        correo.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        correo.setForeground(new java.awt.Color(220, 27, 251));
        correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoActionPerformed(evt);
            }
        });
        jPanel2.add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 170, -1));

        telefono.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        telefono.setForeground(new java.awt.Color(220, 27, 251));
        telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonoActionPerformed(evt);
            }
        });
        jPanel2.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 200, -1));

        domicilio.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        domicilio.setForeground(new java.awt.Color(220, 27, 251));
        domicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                domicilioActionPerformed(evt);
            }
        });
        jPanel2.add(domicilio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 200, -1));

        nombre.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        nombre.setForeground(new java.awt.Color(220, 27, 251));
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 200, -1));

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(220, 27, 251));
        jLabel4.setText("ESTADO LABORAL: ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 160, -1));

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(220, 27, 251));
        jLabel3.setText("Favor de llenar los siguientes campos:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(220, 27, 251));
        jLabel1.setText("CREAR CUENTA BANCARIA");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 260, 35));

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(220, 27, 251));
        jLabel10.setText("NOMBRE:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 60, -1));

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(220, 27, 251));
        jLabel11.setText("DOMICILIO:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, -1));

        jLabel12.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(220, 27, 251));
        jLabel12.setText("TELEFONO:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 90, -1));

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(220, 27, 251));
        jLabel13.setText("EDAD:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 90, -1));

        jLabel14.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(220, 27, 251));
        jLabel14.setText("CORREO ELECTRONICO:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(573, 573, 573))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(416, 416, 416))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void estado_laboralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estado_laboralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estado_laboralActionPerformed

    private void domicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_domicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_domicilioActionPerformed

    private void telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoActionPerformed

    private void correoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoActionPerformed

    private void edadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edadActionPerformed

    private void Continuar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Continuar1ActionPerformed
        try {
            /*String nombreC = nombre.getText();
            cliente.setNombre(nombreC);
            String domicilioC = domicilio.getText();
            cliente.setDomicilio(domicilioC);
            String telefonoC = telefono.getText();
            cliente.setTelefono(telefonoC);
            String correoC = correoE.getText();
            cliente.setCorreo(correoC);
            
            int edadC;
            String ed = edad.getText();
            
            try{
            edadC = Integer.parseInt(ed);
            }catch(NumberFormatException e){
            System.err.println("No se puede convertir a número");
            e.printStackTrace();
            }*/
            
            
            this.control.ejecutaComando(CREAR_CUENTA_DATOS, es_cuenta_debito);
        } catch (AgenteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
        
        
        
        
    }//GEN-LAST:event_Continuar1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            this.control.ejecutaComando(REGRESAR, null);
        } catch (AgenteException ex) {
            Logger.getLogger(CrearCuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearCuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearCuenta().setVisible(true);
            }
        });
    }

    public BooleanData getEs_cuenta_debito() {
        return es_cuenta_debito;
    }

    public void setEs_cuenta_debito(BooleanData es_cuenta_debito) {
        if(this.es_cuenta_debito == null){
            this.es_cuenta_debito = es_cuenta_debito;
        }
    }

    public JTextField getCorreo() {
        return correo;
    }

    public JTextField getDomicilio() {
        return domicilio;
    }

    public JTextField getEdad() {
        return edad;
    }

    public JComboBox<String> getEstado_laboral() {
        return estado_laboral;
    }

    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getTelefono() {
        return telefono;
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Continuar1;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField domicilio;
    private javax.swing.JTextField edad;
    private javax.swing.JComboBox<String> estado_laboral;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField telefono;
    // End of variables declaration//GEN-END:variables

}
