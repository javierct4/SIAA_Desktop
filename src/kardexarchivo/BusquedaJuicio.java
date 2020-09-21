/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GuardarKardex.java
 *
 * Created on 05-ago-2013, 12:01:31
 */

package kardexarchivo;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author edwin.cevallost
 */
public class BusquedaJuicio extends javax.swing.JDialog {
    private String _idjuicio;
    public Usuario user;
    /** Creates new form GuardarKardex */
    public BusquedaJuicio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);      
    }

    public String getIdjuicio() {
        return _idjuicio;
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCadena = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstBusqueda = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        txtCadena.setName("txtCadena"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kardexarchivo.KardexArchivoApp.class).getContext().getResourceMap(BusquedaJuicio.class);
        btnBuscar.setText(resourceMap.getString("btnBuscar.text")); // NOI18N
        btnBuscar.setActionCommand(resourceMap.getString("btnBuscar.actionCommand")); // NOI18N
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        lstBusqueda.setName("lstBusqueda"); // NOI18N
        lstBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstBusquedaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstBusqueda);

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents
 public DefaultListModel BuscarProcesos(String cadena){
        try{
            DefaultListModel lstJuicios=new DefaultListModel();
            List<Litigantes> lt= new ArrayList<Litigantes>();
           
            ResultSet rs = null;
            BaseDatos conn = new BaseDatos("INVENTARIO");
            conn.Conectar();
            conn.CrearStoreProcedure("exec proc_buscar_procesos_litigante ?");
            conn.AgregarParametroString("litigante", cadena);
            rs=conn.EjecutarComando();
            while(rs.next()){
                Litigantes litigante= new Litigantes();
                litigante.jui_id=rs.getString("jui_id");
                litigante.lit_nombre=rs.getString("lit_nombre");
                lstJuicios.addElement(litigante);
                
            }      
        
             conn.Desconectar();
             return lstJuicios;
         
               }catch(Exception ex){
                  System.out.println("Error al guardar el registro sp_buscar_procesos_litigante: "+ex);
                  return null;
                }
            }
 
 
    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked
        // TODO add your handling code here:
       if(!txtCadena.getText().isEmpty()){
            DefaultListModel mdlList = new DefaultListModel(); 
            mdlList=this.BuscarProcesos(txtCadena.getText());
            this.lstBusqueda.setModel(mdlList);
       }
        
        //System.out.println(lst.get(0).toString());
        
      
    }//GEN-LAST:event_btnBuscarMouseClicked

private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
// TODO add your handling code here:
}//GEN-LAST:event_btnBuscarMouseEntered

private void lstBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstBusquedaMouseClicked
// TODO add your handling code here:
    
    if(evt.getClickCount()==2){
        if(lstBusqueda.getSelectedValue()!=null){
            Litigantes lit=new Litigantes();
            lit=(Litigantes)lstBusqueda.getSelectedValue();
            this._idjuicio=lit.jui_id;
            //System.out.println(_idjuicio);
            this.dispose();
        }
    }
    
}//GEN-LAST:event_lstBusquedaMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BusquedaJuicio dialog = new BusquedaJuicio(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstBusqueda;
    private javax.swing.JTextField txtCadena;
    // End of variables declaration//GEN-END:variables

}