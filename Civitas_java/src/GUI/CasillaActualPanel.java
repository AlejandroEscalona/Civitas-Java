/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.*;

/**
 *
 * @author pedrobedmar
 */
public class CasillaActualPanel extends javax.swing.JPanel {
    
    Casilla casillaActual;
    /**
     * Creates new form CasillaActualPanel
     */
    public CasillaActualPanel() {
        initComponents();
    }

    void setCasillaActual(Casilla casilla){
        casillaActual=casilla;
        textNombre.setText(casilla.getNombre());
        if(casilla.getClass().getName()=="civitas.Casilla"){
            textTipoCasilla.setText("Descanso");
        } else if(casilla.getClass().getName()=="civitas.CasillaCalle"){
            textTipoCasilla.setText("Calle");
        } else if(casilla.getClass().getName()=="civitas.CasillaImpuesto"){
            textTipoCasilla.setText("Impuesto");
        } else if(casilla.getClass().getName()=="civitas.CasillaJuez"){
            textTipoCasilla.setText("Juez");
        } else if(casilla.getClass().getName()=="civitas.CasillaSorpresa"){
            textTipoCasilla.setText("Sorpresa");
        }
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTipoCasilla = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        textTipoCasilla = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 153));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelTipoCasilla.setText("Tipo Casilla:");

        labelNombre.setText("Nombre:");

        textTipoCasilla.setText("jTextField1");
        textTipoCasilla.setEnabled(false);

        textNombre.setText("jTextField1");
        textNombre.setEnabled(false);

        jLabel1.setText("Casilla Actual");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoCasilla)
                            .addComponent(labelNombre))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(textTipoCasilla)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoCasilla)
                    .addComponent(textTipoCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelTipoCasilla;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTipoCasilla;
    // End of variables declaration//GEN-END:variables
}
