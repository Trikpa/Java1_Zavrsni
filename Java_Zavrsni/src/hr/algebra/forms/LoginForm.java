package hr.algebra.forms;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.forms.dialogs.RegisterDialog;
import hr.algebra.models.User;
import hr.algebra.models.enums.UserRole;
import hr.algebra.utils.MessageUtils;
import hr.algebra.utils.PasswordUtils;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginForm extends javax.swing.JFrame {
    private List<JLabel> errorLabels;
    private List<JTextField> validationFields;

    private Repository repository;
    
    public LoginForm() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();
        lblErrorUsername = new javax.swing.JLabel();
        lblErrorPassword = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setName("loginFrame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 120, 70, 20);

        tfUsername.setBackground(new java.awt.Color(255, 255, 255));
        tfUsername.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tfUsername.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(tfUsername);
        tfUsername.setBounds(130, 140, 170, 25);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(130, 170, 70, 20);

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin);
        btnLogin.setBounds(130, 240, 72, 21);

        tfPassword.setBackground(new java.awt.Color(255, 255, 255));
        tfPassword.setForeground(new java.awt.Color(0, 0, 0));
        tfPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPasswordKeyReleased(evt);
            }
        });
        getContentPane().add(tfPassword);
        tfPassword.setBounds(130, 190, 170, 25);

        lblErrorUsername.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorUsername.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorUsername.setFocusable(false);
        getContentPane().add(lblErrorUsername);
        lblErrorUsername.setBounds(300, 140, 10, 25);

        lblErrorPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorPassword.setFocusable(false);
        getContentPane().add(lblErrorPassword);
        lblErrorPassword.setBounds(300, 190, 10, 25);

        btnRegister.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnRegister.setText("Register");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegister);
        btnRegister.setBounds(220, 240, 80, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/Cinemoon_background.png"))); // NOI18N
        jLabel1.setText("lblBackgroundImage");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 300);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        btnLogin.setEnabled(false);
        btnRegister.setEnabled(false);
        
        new Thread(() -> {
            if (formIsValid()) {
                String passwordHash = PasswordUtils.getSHA512SecurePassword(tfPassword.getPassword());
                try {
                    Optional<User> user = repository.LoginUser(tfUsername.getText(), passwordHash);
                    if (user.isPresent()) {
                        if (user.get().getUserLevel() == UserRole.ADMIN)
                            new AdminForm().setVisible(true);
                        else
                            new UserForm().setVisible(true);
                            
                        this.dispose();
                    } else {
                        MessageUtils.showInformationMessage("Incorrect credentials", "Incorrect username or password.");
                        tfPassword.setText("");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            btnLogin.setEnabled(true);
            btnRegister.setEnabled(true);
        }).start();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void tfPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnLogin.doClick();
        }
    }//GEN-LAST:event_tfPasswordKeyReleased

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        RegisterDialog reg = new RegisterDialog(this, true);
        reg.setVisible(true);
    }//GEN-LAST:event_btnRegisterActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblErrorPassword;
    private javax.swing.JLabel lblErrorUsername;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables

    private void init() {
        repository = RepositoryFactory.getRepository();
        
        initValidation();
    }

    private void initValidation() {
        errorLabels = Arrays.asList(lblErrorUsername, lblErrorPassword);
        validationFields = Arrays.asList(tfUsername, tfPassword);
    }
    
    private boolean formIsValid() {
        boolean ok = true;
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
        }        
        return ok;
    }
}
