package hr.algebra.forms.dialogs;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Movie;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.IconUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class UpdateMovieDialog extends javax.swing.JDialog {
    
    private static final Random RANDOM = new Random();

    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    
    public Movie movie;
    private List<String> genre = new ArrayList<>();
    private String picturePath = "";
    
    private Repository repository;
    
    private UpdateMovieDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    
    public UpdateMovieDialog(java.awt.Frame parent, boolean modal, Movie movieToUpdate) {
        super(parent, modal);
        initComponents();
        movie = movieToUpdate;
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        tfDuration = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfGenre = new javax.swing.JTextField();
        lblImage = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfPicturePath = new javax.swing.JTextField();
        btnSelectPicture = new javax.swing.JButton();
        lblErrorTitle = new javax.swing.JLabel();
        lblErrorDescription = new javax.swing.JLabel();
        lblErrorDuration = new javax.swing.JLabel();
        lblErrorGenre = new javax.swing.JLabel();
        btnDiscard = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit movie info");
        setMinimumSize(new java.awt.Dimension(725, 456));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Title:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Description:");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setWrapStyleWord(true);
        taDescription.setName("description"); // NOI18N
        jScrollPane1.setViewportView(taDescription);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Duration:");

        tfDuration.setName("duration"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Genre:");

        tfGenre.setName("genre"); // NOI18N

        lblImage.setBackground(new java.awt.Color(51, 51, 51));
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setMinimumSize(new java.awt.Dimension(190, 272));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Movie poster");

        tfPicturePath.setEditable(false);

        btnSelectPicture.setText("...");
        btnSelectPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPictureActionPerformed(evt);
            }
        });

        lblErrorTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorTitle.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorDescription.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorDescription.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorDuration.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorDuration.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorGenre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorGenre.setForeground(new java.awt.Color(204, 0, 0));

        btnDiscard.setText("Discard");
        btnDiscard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiscardActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(tfTitle)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfGenre))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDuration)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblErrorGenre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblErrorDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblErrorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSelectPicture)
                .addGap(38, 38, 38))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(547, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblErrorDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelectPicture))
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(376, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDiscardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiscardActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnDiscardActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (formValid()) {
            movie.setTitle(tfTitle.getText().trim());
            movie.setDescription(taDescription.getText().trim());
            movie.setDuration(Integer.parseInt(tfDuration.getText().trim()));
            
            handleGenre();
            movie.setGenre(genre);
            
            if (movie.getPicturePath() == null ? tfPicturePath.getText() != null : !movie.getPicturePath().equals(tfPicturePath.getText())) {
                movie.setPicturePath(this.picturePath);
            }
            
            try {
                int updateStatus = repository.UpdateMovie(movie);
                if(updateStatus > 0)
                    MessageUtils.showInformationMessage("Success!", "Movie updated successfully!");
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "An error occurred while trying to update movie." + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSelectPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPictureActionPerformed
        Optional<File> file = FileUtils.uploadFile("Images", "jpg", "jpeg", "png", "bmp");
        
        if (file.isPresent()) {
            try {
                String filePath = file.get().getAbsolutePath();
                String ext = filePath.substring(filePath.lastIndexOf("."));
                if (ext.length() > 4) {
                    ext = ".jpg";
                }
        
                String pictureName = Math.abs(RANDOM.nextInt()) + ext;
                
                String pictureLocation = "assets/movie_posters" + File.separator + pictureName;
                
                FileUtils.copy(file.get().getAbsolutePath(), pictureLocation);
                this.picturePath = pictureLocation;
                tfPicturePath.setText(file.get().getAbsolutePath());
                
                try {
                    ImageIcon image = IconUtils.createIcon(pictureLocation, lblImage.getWidth(), lblImage.getHeight());
                    lblImage.setIcon(image);
                } catch (IOException iOException) {
                    lblImage.setText("no image set");
                }
            } catch (IOException ex) {
                MessageUtils.showErrorMessage("Error", "Unable to upload file." + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSelectPictureActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateMovieDialog dialog = new UpdateMovieDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnSelectPicture;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErrorDescription;
    private javax.swing.JLabel lblErrorDuration;
    private javax.swing.JLabel lblErrorGenre;
    private javax.swing.JLabel lblErrorTitle;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfGenre;
    private javax.swing.JTextField tfPicturePath;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initValidation();
        fillForm();
        repository = RepositoryFactory.getRepository();
        genre = movie.getGenre();
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfTitle, taDescription, tfDuration, tfGenre);
        errorLabels = Arrays.asList(lblErrorTitle, lblErrorDescription, lblErrorDuration, lblErrorGenre);
    }

    private void fillForm() {
        tfTitle.setText(movie.getTitle());
        taDescription.setText(movie.getDescription());
        tfDuration.setText(String.valueOf(movie.getDuration()));
        tfGenre.setText(movie.getGenre().toString().replaceAll("\\[", "").replaceAll("\\]", ""));
        
        if (!"".equals(movie.getPicturePath())) {
            tfPicturePath.setText(movie.getPicturePath());
            
            try {
                ImageIcon moviePoster = IconUtils.createIcon(movie.getPicturePath(), lblImage.getWidth(), lblImage.getHeight());
                lblImage.setIcon(moviePoster);
            } catch (IOException ex) {
                MessageUtils.showErrorMessage("Error", "Unable to load movie poster");
            }
        }
    }

    private boolean formValid() {
        boolean ok = true;
        
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
            
            if ("description".equals(validationFields.get(i).getName())) {
                if (taDescription.getText().trim().length() > 255) {
                    ok = false;
                    errorLabels.get(i).setText("X");
                }
            } else if ("duration".equals(validationFields.get(i).getName())) {
                try {
                    Integer.parseInt(tfDuration.getText().trim());
                    errorLabels.get(i).setText("");
                } catch (NumberFormatException e) {
                    ok = false;
                    errorLabels.get(i).setText("X");
                }
            } else if ("genre".equals(validationFields.get(i).getName())) {
                if (!validationFields.get(i).getText().trim().isEmpty()) {
                    if (validationFields.get(i).getText().trim().contains("^[a-zA-Z\\,\\-\\s]*$")) {
                        ok = false;
                        errorLabels.get(i).setText("X");
                    }
                }
            }
        }        
        return ok;
    }

    private void handleGenre() {
        genre.clear();
        String[] genres = tfGenre.getText().split(",");
        for(String gen : genres)
            genre.add(gen.trim());
    }

}
