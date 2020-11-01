package hr.algebra.forms;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Movie;
import hr.algebra.parsers.rss.MovieParser;
import hr.algebra.utils.MessageUtils;
import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLStreamException;

public class AdminForm extends javax.swing.JFrame {

    private Repository repository;
    
    public AdminForm() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLoading = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnFetch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administration");
        setPreferredSize(new java.awt.Dimension(330, 250));
        setResizable(false);
        getContentPane().setLayout(null);

        lblLoading.setBackground(new java.awt.Color(153, 153, 153));
        lblLoading.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLoading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/ajax-loader.gif"))); // NOI18N
        lblLoading.setText("Loading...");
        getContentPane().add(lblLoading);
        lblLoading.setBounds(0, 0, 330, 250);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(6, 40, 103, 30);

        jLabel2.setText("Fetch movie data");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(6, 126, 93, 16);

        jLabel1.setText("Delete all non-user-related data");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(6, 6, 182, 16);

        btnFetch.setText("Fetch");
        btnFetch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFetchActionPerformed(evt);
            }
        });
        getContentPane().add(btnFetch);
        btnFetch.setBounds(6, 160, 103, 30);

        jLabel3.setText("Open user application");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(159, 126, 141, 16);

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        getContentPane().add(btnOpen);
        btnOpen.setBounds(159, 160, 103, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        setButtonsEnabled(false);
        new Thread( () -> {
            try {
                repository.DeleteAllNonUserRelatedData();
                MessageUtils.showInformationMessage("Success", "Data has been deleted successfully.");
                btnDelete.setEnabled(true);
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "An unknown error occured.");
            }
        }).start();
        setButtonsEnabled(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

    public void setButtonsEnabled(boolean enabled) {
        btnDelete.setEnabled(enabled);
        btnFetch.setEnabled(enabled);
        btnOpen.setEnabled(enabled);
    }

    private void btnFetchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFetchActionPerformed
        setButtonsEnabled(false);
        lblLoading.setVisible(true);
        new Thread(() -> {
            try {
                repository.DeleteAllNonUserRelatedData();
                List<Movie> movies = MovieParser.parse();
                if (movies == null) {
                    MessageUtils.showErrorMessage("Error", "Unable to retreive data from the server. " );
                    setButtonsEnabled(true);
                    return;
                }
                repository.SaveMovies(movies);
                setButtonsEnabled(true);
            } catch (IOException | XMLStreamException ex) {
                MessageUtils.showErrorMessage("Error", "An unknown error occured. " + ex.getMessage());
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "An unknown error occured. " + ex.getMessage());
            }
            setButtonsEnabled(true);
            lblLoading.setVisible(false);
        }).start();
    }//GEN-LAST:event_btnFetchActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        new UserForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOpenActionPerformed

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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFetch;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblLoading;
    // End of variables declaration//GEN-END:variables

    private void init() {
        repository = RepositoryFactory.getRepository();
        lblLoading.setVisible(false);
        lblLoading.setBounds(0, -20, this.getBounds().width, this.getBounds().height);
    }
}
