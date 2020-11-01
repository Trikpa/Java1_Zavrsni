package hr.algebra.forms.panels;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.forms.dialogs.EditMoviePersonDialog;
import hr.algebra.models.MoviePerson;
import hr.algebra.models.enums.MoviePersonType;
import hr.algebra.utils.MessageUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import hr.algebra.interfaces.PersonObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class ActorsAndDirectorsPanel extends javax.swing.JPanel {

    private List<PersonObserver> listeners = new ArrayList<>();
    
    private Repository repository;
    
    private List<JTextField> validationFields;
    private List<JLabel> errorLabels;
    
    private DefaultListModel<MoviePerson> actorsListModel = new DefaultListModel<>();
    private DefaultListModel<MoviePerson> directorsListModel = new DefaultListModel<>();
    
    private final JMenuItem jmiDelete = new JMenuItem("Delete");
    private final JMenuItem jmiEdit = new JMenuItem("Edit");
    
    private MoviePerson selectedPerson;
    
    private JList<MoviePerson> selectedList;
    
    public ActorsAndDirectorsPanel() {
        initComponents();
        init();
    }
    
    public ActorsAndDirectorsPanel(PersonObserver personUpdatedListeners) {
        initComponents();
        init();
        listeners.add(personUpdatedListeners);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupEditDelete = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        cbMoviePersonType = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        lblErrorFirstName = new javax.swing.JLabel();
        lblErrorLastName = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(610, 490));

        lsActors.setName("actors"); // NOI18N
        lsActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lsActors);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Actors");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Directors");

        lsDirectors.setName("directors"); // NOI18N
        lsDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lsDirectors);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Add a new actor or director");

        jLabel4.setText("First name");

        jLabel5.setText("Last name");

        jLabel6.setText("Type");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblErrorFirstName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorFirstName.setForeground(new java.awt.Color(204, 0, 0));

        lblErrorLastName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblErrorLastName.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(btnAdd))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(tfFirstName)
                                    .addComponent(jLabel5)
                                    .addComponent(tfLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbMoviePersonType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblErrorLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblErrorFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErrorFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErrorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMoviePersonType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAdd))
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (formValid()) {
            if ( ((MoviePersonType)cbMoviePersonType.getSelectedItem()).equals(MoviePersonType.ACTOR) ) {
                MoviePerson person = new MoviePerson(tfFirstName.getText().trim(), tfLastName.getText().trim(), MoviePersonType.ACTOR);
                try {
                    repository.InsertMoviePerson(person);
                    actorsListModel.addElement(person);
                    sortModel(actorsListModel);
                    MessageUtils.showInformationMessage("Success", "Actor added successfully!");
                    listeners.forEach(PersonObserver::personUpdated);
                } catch (Exception ex) {
                    MessageUtils.showErrorMessage("Error", "Unable to add actor");
                }
            } else {
                MoviePerson person = new MoviePerson(tfFirstName.getText().trim(), tfLastName.getText().trim(), MoviePersonType.DIRECTOR);
                try {
                    repository.InsertMoviePerson(person);
                    directorsListModel.addElement(person);
                    sortModel(directorsListModel);
                    MessageUtils.showInformationMessage("Success", "Director added successfully!");
                } catch (Exception ex) {
                    MessageUtils.showErrorMessage("Error", "Unable to add director");
                }
            }
            clearForm();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void listMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseReleased
        check(evt);
    }//GEN-LAST:event_listMouseReleased

    public void check(java.awt.event.MouseEvent e) {
        if (null == e.getComponent().getName())
            return;
        else switch (e.getComponent().getName()) {
            case "actors":
                selectedList = lsActors;
                lsDirectors.clearSelection();
                break;
            case "directors":
                selectedList = lsDirectors;
                lsActors.clearSelection();
                break;
            default:
                return;
        }

        if (e.isPopupTrigger()) {
            selectedList.setSelectedIndex(selectedList.locationToIndex(e.getPoint()));
            selectedPerson = selectedList.getSelectedValue();
            popupEditDelete.show(selectedList, e.getX(), e.getY());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<MoviePersonType> cbMoviePersonType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblErrorFirstName;
    private javax.swing.JLabel lblErrorLastName;
    private javax.swing.JList<MoviePerson> lsActors;
    private javax.swing.JList<MoviePerson> lsDirectors;
    private javax.swing.JPopupMenu popupEditDelete;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    // End of variables declaration//GEN-END:variables

    private boolean formValid() {
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
        initDropdown();
        loadLists();
        initPopup();
    }

    private void initValidation() {
        validationFields = Arrays.asList(tfFirstName, tfLastName);
        errorLabels = Arrays.asList(lblErrorFirstName, lblErrorLastName);
    }

    private void loadLists() {
        try {
            List<MoviePerson> actors = repository.SelectAllActors();
            actors.forEach(act -> actorsListModel.addElement(act));
            lsActors.setModel(actorsListModel);
            
            List<MoviePerson> directors = repository.SelectAllDirectors();
            directors.forEach(dir -> directorsListModel.addElement(dir));
            lsDirectors.setModel(directorsListModel);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to load actors and directors from database");
        }
    }

    private void initDropdown() {
        cbMoviePersonType.addItem(MoviePersonType.ACTOR);
        cbMoviePersonType.addItem(MoviePersonType.DIRECTOR);
        cbMoviePersonType.setSelectedIndex(0);
    }
    
    private void sortModel(DefaultListModel model) {
        List<MoviePerson> list = new ArrayList<>();
        for (int i = 0; i < model.size(); i++)
            list.add((MoviePerson)model.get(i));

        Collections.sort(list);
        model.removeAllElements();
        list.forEach((person) -> {
            model.addElement(person);
        });
    }
    
    private void clearForm() {
        tfFirstName.setText("");
        tfLastName.setText("");
    }

    private void initPopup() {
        popupEditDelete.add(jmiEdit);
        popupEditDelete.add(jmiDelete);
        
        jmiEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openEditDialog(selectedPerson);
                } catch (Exception exception) {
                    MessageUtils.showErrorMessage("Error", "Unable to open edit dialog");
                }
            }
        });
        
        jmiDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                DefaultListModel<MoviePerson> selectedListModel = new DefaultListModel<>();
                try {
                    switch (selectedList.getName()) {
                        case "actors":
                            actorsListModel.removeElement(selectedPerson);
                            repository.DeleteMoviePerson(selectedPerson);
                            selectedListModel = actorsListModel;
                            break;
                        case "directors":
                            directorsListModel.removeElement(selectedPerson);
                            repository.DeleteMoviePerson(selectedPerson);
                            selectedListModel = directorsListModel;
                            break;
                        default:
                            return;
                    }
                if (index > selectedListModel.indexOf(selectedListModel.firstElement())) {
                    selectedList.setSelectedIndex(--index);
                } else if (index == selectedListModel.indexOf(selectedListModel.firstElement())) {
                    selectedList.setSelectedIndex(++index);
                }
                MessageUtils.showInformationMessage("Success", "Person deleted successfully.");
                listeners.forEach(PersonObserver::personUpdated);
                } catch (Exception exception) {
                    MessageUtils.showErrorMessage("Error", "Unable to delete person");
                }
            }
        });
    }
    
    private void openEditDialog(MoviePerson person) {
        EditMoviePersonDialog dialog = new EditMoviePersonDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, person);
        dialog.setVisible(true);
        
        if (dialog.personUpdated) {
            listeners.forEach(PersonObserver::personUpdated);
            updateLists(dialog.person);
        }
            
        dialog.dispose();
    }

    private void updateLists(MoviePerson updatedPerson) {
        if (selectedList == lsActors) {
            for (int i = 0; i < actorsListModel.getSize(); i++) {
                if(actorsListModel.get(i).getId() == updatedPerson.getId())
                    actorsListModel.setElementAt(updatedPerson, i);
            }
        } else {
            for (int i = 0; i < directorsListModel.getSize(); i++) {
                if(directorsListModel.get(i).getId() == updatedPerson.getId())
                    directorsListModel.setElementAt(updatedPerson, i);
            }
        }
    }
}
