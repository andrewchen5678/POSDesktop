/*
 * NewJDialog.java
 *
 * Created on July 20, 2008, 2:44 PM
 */

package posdesktop;
import ordering.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
/**
 *
 * @author  angelchen
 */
public class RemoveCondDialog extends javax.swing.JDialog {
    //private static Connection con;
    private ArrayList<CondEn> condimEdit;
    /** Creates new form NewJDialog */
    public RemoveCondDialog(JDialog parent, boolean modal, ArrayList<CondEn> theCondim) throws IllegalArgumentException {
        super(parent, modal);
        if(theCondim==null){
            throw new IllegalArgumentException("condim can't be null");
        }
        removeListModel=new DefaultListModel();
        initComponents();
        condimEdit=theCondim;
        for(int i=0;i<theCondim.size();i++){
            removeListModel.addElement(theCondim.get(i));
        }
        jList1.setSelectedIndex(removeListModel.size()-1);
        jList1.ensureIndexIsVisible(removeListModel.size()-1);
        //JButton[] subButtons=new JButton[subCond.length];
        //this.pack();
        //jPanel1.removeAll();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bottomPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList(removeListModel);
        removeWholeBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        reduceQuantityBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.PAGE_AXIS));

        jLabel1.setText("Current condiments:");

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        removeWholeBtn.setText("Remove Whole");
        removeWholeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWholeBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Finish Editing");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        reduceQuantityBtn.setText("Quantity-1");
        reduceQuantityBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reduceQuantityBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(reduceQuantityBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeWholeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(removeWholeBtn)
                    .addComponent(reduceQuantityBtn))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        getContentPane().add(bottomPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void removeWholeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWholeBtnActionPerformed
// TODO add your handling code here:
    helperRemove();//GEN-LAST:event_removeWholeBtnActionPerformed
}

private void helperRemove(){
    if(removeListModel.isEmpty()){
        JOptionPane.showMessageDialog(this, "nothing to remove");
        return;
    }else if(jList1.isSelectionEmpty()){
        JOptionPane.showMessageDialog(this, "nothing selected");
        return;
    }
    int i=jList1.getSelectedIndex();
    removeListModel.remove(i);
    condimEdit.remove(i);
    jList1.setSelectedIndex(removeListModel.getSize()-1);
    jList1.ensureIndexIsVisible(removeListModel.getSize()-1);
}

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    this.dispose();//GEN-LAST:event_jButton3ActionPerformed
}                                        

private void reduceQuantityBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reduceQuantityBtnActionPerformed
// TODO add your handling code here:
    helperReduce();//GEN-LAST:event_reduceQuantityBtnActionPerformed
}

private void helperReduce(){
    if(removeListModel.isEmpty()){
        JOptionPane.showMessageDialog(this, "nothing to remove");
        return;
    }else if(jList1.isSelectionEmpty()){
        JOptionPane.showMessageDialog(this, "nothing selected");
        return;
    }
    int i=jList1.getSelectedIndex();
    CondEn selected=(CondEn)removeListModel.get(i);
    if(selected.getQuantity()<=1){
        removeListModel.remove(i);
        condimEdit.remove(i);
        jList1.setSelectedIndex(removeListModel.getSize()-1);
        jList1.ensureIndexIsVisible(removeListModel.getSize()-1);        
    }else{
        //condimEdit.get(i).setQuantity(condimEdit.get(i).getQuantity()-1);
        selected.setQuantity(selected.getQuantity()-1);
        removeListModel.setElementAt(selected, i);
    }
}
    /**
    * @param args the command line arguments
    */
/*
    public static void main(String args[]) throws Exception {
                NewJDialog dialog = new NewJDialog(null, true);
                dialog.setVisible(true);
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton reduceQuantityBtn;
    private javax.swing.JButton removeWholeBtn;
    // End of variables declaration//GEN-END:variables
    private javax.swing.DefaultListModel removeListModel;
}