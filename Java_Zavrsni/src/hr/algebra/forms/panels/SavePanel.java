package hr.algebra.forms.panels;

import hr.algebra.models.Movie;
import hr.algebra.models.MovieArchive;
import hr.algebra.utils.FileUtils;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import java.io.File;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class SavePanel extends javax.swing.JPanel {

    private String fileSaveLocation;
    
    private final MoviesPanel moviesPanel;
    
    public SavePanel(MoviesPanel moviesPanel) {
        initComponents();
        init();
        this.moviesPanel = moviesPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfFilePath = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(610, 490));
        setPreferredSize(new java.awt.Dimension(610, 490));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Save data from the database to an XML file");

        jLabel2.setText("Location");

        tfFilePath.setEditable(false);

        btnBrowse.setText("...");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBrowse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addGap(14, 14, 14))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowse)
                    .addComponent(btnSave))
                .addContainerGap(397, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String filename = fileSaveLocation + File.separator + "archive" + ".xml";
            JAXBUtils.save(new MovieArchive(moviesPanel.movies), filename);
            MessageUtils.showInformationMessage("Success", "File save successfully");
        } catch (JAXBException e) {
            MessageUtils.showErrorMessage("Error", "Failed to save the file." + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        Optional<File> saveLocation = FileUtils.chooseFileSaveLocation();
        if (!saveLocation.isPresent())
            return;
        
        fileSaveLocation = saveLocation.get().getAbsolutePath();
        tfFilePath.setText(fileSaveLocation);
    }//GEN-LAST:event_btnBrowseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfFilePath;
    // End of variables declaration//GEN-END:variables

    private void init() {
        fileSaveLocation = new File(".").getAbsolutePath();
        fileSaveLocation = fileSaveLocation.substring(0, fileSaveLocation.length()-1);
        
        tfFilePath.setText(fileSaveLocation);
    }
}
