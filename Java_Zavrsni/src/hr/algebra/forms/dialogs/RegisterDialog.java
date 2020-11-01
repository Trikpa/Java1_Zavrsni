package hr.algebra.forms.dialogs;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.utils.MessageUtils;
import hr.algebra.utils.PasswordUtils;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterDialog extends javax.swing.JDialog {

    private List<JTextField> validationFields;
    private List<JLabel> errorLabels;
    
    private Repository repository;
    
    public RegisterDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        tfConfirmPassword = new javax.swing.JPasswordField();
        btnRegister = new javax.swing.JButton();
        lblErrorConfirmPassword = new javax.swing.JLabel();
        lblErrorUsername = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register");
        setMaximumSize(new java.awt.Dimension(410, 325));
        setMinimumSize(new java.awt.Dimension(410, 325));
        setModal(true);
        setName("registerDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(410, 325));
        setResizable(false);
        getContentPane().setLayout(null);

        tfUsername.setBackground(new java.awt.Color(255, 255, 255));
        tfUsername.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tfUsername.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tfUsername);
        tfUsername.setBounds(150, 150, 140, 27);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(150, 179, 140, 16);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Username");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(150, 135, 60, 16);

        tfPassword.setBackground(new java.awt.Color(255, 255, 255));
        tfPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfPassword.setName("password"); // NOI18N
        getContentPane().add(tfPassword);
        tfPassword.setBounds(150, 194, 140, 22);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Confirm password");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(150, 218, 140, 16);

        tfConfirmPassword.setBackground(new java.awt.Color(255, 255, 255));
        tfConfirmPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfConfirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfConfirmPasswordKeyReleased(evt);
            }
        });
        getContentPane().add(tfConfirmPassword);
        tfConfirmPassword.setBounds(150, 233, 140, 22);

        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister);
        btnRegister.setBounds(176, 262, 76, 22);

        lblErrorConfirmPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorConfirmPassword.setForeground(new java.awt.Color(153, 0, 0));
        getContentPane().add(lblErrorConfirmPassword);
        lblErrorConfirmPassword.setBounds(290, 230, 10, 0);

        lblErrorUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorUsername.setForeground(new java.awt.Color(153, 0, 0));
        getContentPane().add(lblErrorUsername);
        lblErrorUsername.setBounds(290, 150, 10, 0);

        lblErrorPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorPassword.setForeground(new java.awt.Color(153, 0, 0));
        getContentPane().add(lblErrorPassword);
        lblErrorPassword.setBounds(290, 190, 10, 0);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Register_background.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -10, 410, 325);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        btnRegister.setEnabled(false);
        new Thread(() -> {
            if (formIsValid()) {
                if (tfPassword.getPassword().length < 8 || tfPassword.getPassword().length > 64) {
                    MessageUtils.showInformationMessage("", "Password must be between 8 and 64 characters long.");
                    return;
                }
                if (!Arrays.equals(tfPassword.getPassword(), tfConfirmPassword.getPassword())) {
                    MessageUtils.showInformationMessage("", "Passwords must match");
                    tfPassword.setText("");
                    tfConfirmPassword.setText("");
                    return;
                }
            
                String passwordHash = PasswordUtils.getSHA512SecurePassword(tfPassword.getPassword());
            
                try {
                    int response = repository.RegisterUser(tfUsername.getText(), passwordHash);
                    if (response == 1) {
                        MessageUtils.showInformationMessage("Success", "Registration successfull");
                        this.dispose();
                    } else {
                        MessageUtils.showInformationMessage("Unsuccessful", "User already exists");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(RegisterDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                btnRegister.setEnabled(true);
            }
        }).start();
        btnRegister.setEnabled(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void tfConfirmPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfConfirmPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnRegister.doClick();
        }
    }//GEN-LAST:event_tfConfirmPasswordKeyReleased


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
            java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegisterDialog dialog = new RegisterDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblErrorConfirmPassword;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JLabel lblErrorUsername;
    private javax.swing.JPasswordField tfConfirmPassword;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    private void initValidation() {
        errorLabels = Arrays.asList(lblErrorUsername, lblErrorPassword, lblErrorConfirmPassword);
        validationFields = Arrays.asList(tfUsername, tfPassword, tfConfirmPassword);
    }
    
    private boolean formIsValid() {
        boolean ok = true;
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
        }        
        return ok;
    }

    private void init() {
        repository = RepositoryFactory.getRepository();
        initValidation();
    }
}
