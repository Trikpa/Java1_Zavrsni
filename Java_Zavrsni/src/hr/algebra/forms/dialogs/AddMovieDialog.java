package hr.algebra.forms.dialogs;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.models.Movie;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.text.JTextComponent;

public class AddMovieDialog extends javax.swing.JDialog {
    
    private static final Random RANDOM = new Random();
    
    private List<JTextComponent> validationFields;
    private List<JLabel> errorLabels;
    
    private Repository repository;
    
    private Integer descriptionCharCount;
    private final Integer MAX_DESCRIPTION_CHARS = 255;
    
    private String picturePath = "";
    
    public Optional<Movie> insertedMovie = Optional.empty();

    public AddMovieDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblCharCountDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tfDuration = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfPicturePath = new javax.swing.JTextField();
        btnSelectImage = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblErrorTitle = new javax.swing.JLabel();
        lblErrorDescription = new javax.swing.JLabel();
        lblErrorGenre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfGenre = new javax.swing.JTextField();
        lblErrorDuration = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add a movie");
        setMinimumSize(new java.awt.Dimension(400, 415));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("newMovieDialog"); // NOI18N
        setSize(new java.awt.Dimension(400, 390));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add a new movie");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Title");

        tfTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfTitle.setName("title"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Description");

        lblCharCountDescription.setText("250/250");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        taDescription.setTabSize(4);
        taDescription.setWrapStyleWord(true);
        taDescription.setName("description"); // NOI18N
        taDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taDescriptionKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(taDescription);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Duration");

        tfDuration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDuration.setName("duration"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Picture");

        tfPicturePath.setEditable(false);
        tfPicturePath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnSelectImage.setText("...");
        btnSelectImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectImageActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblErrorTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorTitle.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorDescription.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorDescription.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorGenre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorGenre.setForeground(new java.awt.Color(204, 0, 0));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Genre");

        tfGenre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfGenre.setText("comedy, drama, SCI-FI...");
        tfGenre.setName("genre"); // NOI18N

        lblErrorDuration.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorDuration.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(lblErrorDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46))
                            .addComponent(lblCharCountDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btnSelectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErrorDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblErrorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(lblCharCountDescription)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(tfDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel6))
                                    .addComponent(tfGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSelectImage, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdd)
                                    .addComponent(btnCancel)))
                            .addComponent(lblErrorGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblErrorTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (formValid()) {
            String[] genresRaw = tfGenre.getText().trim().split(",");
            List<String> genres = new ArrayList<>();
            
            for(String genre : genresRaw)
                genres.add(genre.trim());
            
            try {
                Movie movie = new Movie(tfTitle.getText().trim(), LocalDateTime.now(), tfTitle.getText().trim(), new ArrayList<>(), new ArrayList<>(), taDescription.getText().trim(), genres, Integer.parseInt(tfDuration.getText().trim()), picturePath);
                
                int movieID = repository.InsertMovie(movie);
                movie.setId(movieID);
                insertedMovie = Optional.of(movie);
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + ex.getCause().getMessage());
                MessageUtils.showErrorMessage("Error", "Unable to insert movie into database." + ex.getMessage());
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void taDescriptionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taDescriptionKeyTyped
        updateDescriptionCharCountLabel();
    }//GEN-LAST:event_taDescriptionKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        try {
            org.apache.commons.io.FileUtils.deleteQuietly(new File(picturePath));
        } catch (Exception e) {
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSelectImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectImageActionPerformed
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
            } catch (IOException ex) {
                MessageUtils.showErrorMessage("Error", "Unable to upload file." + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSelectImageActionPerformed


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
            java.util.logging.Logger.getLogger(AddMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMovieDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMovieDialog dialog = new AddMovieDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSelectImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCharCountDescription;
    private javax.swing.JLabel lblErrorDescription;
    private javax.swing.JLabel lblErrorDuration;
    private javax.swing.JLabel lblErrorGenre;
    private javax.swing.JLabel lblErrorTitle;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField tfDuration;
    private javax.swing.JTextField tfGenre;
    private javax.swing.JTextField tfPicturePath;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables

    private void init() {
        repository = RepositoryFactory.getRepository();
        initValidation();
        try {
            descriptionCharCount = taDescription.getText().length();
        } catch (Exception e) {
            descriptionCharCount = 0;
        }
        updateDescriptionCharCountLabel();
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfTitle, taDescription, tfDuration, tfGenre);
        errorLabels = Arrays.asList(lblErrorTitle, lblErrorDescription, lblErrorDuration, lblErrorGenre);
    }

    private boolean formValid() {
        boolean ok = true;
        
        for (int i = 0; i < validationFields.size(); i++) {
            ok &= !validationFields.get(i).getText().trim().isEmpty();
            errorLabels.get(i).setText(validationFields.get(i).getText().trim().isEmpty() ? "X" : "");
            
            if ("description".equals(validationFields.get(i).getName())) {
                if (descriptionCharCount > MAX_DESCRIPTION_CHARS) {
                    ok = false;
                    errorLabels.get(i).setText("X");
                }
            } else if ("duration".equals(validationFields.get(i).getName())) {
                try {
                    Integer.parseInt(tfDuration.getText().trim());
                    errorLabels.get(i).setText("");
                } catch (Exception e) {
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

    private void updateDescriptionCharCountLabel() {
        try {
            if (taDescription.getText().length() == 0) {
                descriptionCharCount = 0;
            } else {
                descriptionCharCount = taDescription.getText().length() + 1;
            }
        } catch (Exception e) {
        }
        lblCharCountDescription.setText(descriptionCharCount.toString() + "/" + MAX_DESCRIPTION_CHARS.toString());
    }
}
