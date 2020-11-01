package hr.algebra.forms.panels;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.forms.dialogs.AddMovieDialog;
import hr.algebra.forms.dialogs.UpdateMovieDialog;
import hr.algebra.models.Movie;
import hr.algebra.models.MoviePerson;
import hr.algebra.models.enums.MoviePersonType;
import hr.algebra.models.transferables.*;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import hr.algebra.interfaces.PersonObserver;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoviesPanel extends javax.swing.JPanel implements PersonObserver{
    
    public List<Movie> movies;

    private DefaultListModel<Movie>       moviesListModel                 = new DefaultListModel<>();
    private DefaultListModel<MoviePerson> selectedMovieActorsListModel    = new DefaultListModel<>();
    private DefaultListModel<MoviePerson> selectedMovieDirectorsListModel = new DefaultListModel<>();
    private DefaultListModel<MoviePerson> allActorsListModel              = new DefaultListModel<>();
    private DefaultListModel<MoviePerson> allDirectorsListModel           = new DefaultListModel<>();
    
    private List<DefaultListModel<MoviePerson>> listModels = new ArrayList<>();
    
    private List<MoviePerson> allActors = new ArrayList<>();
    private List<MoviePerson> allDirectors = new ArrayList<>();
    
    private Repository repository;
    private Movie selectedMovie;
    private MoviePerson selectedMoviePerson;
    private JList selectedList;
    
    private final JMenuItem jmiDelete = new JMenuItem("Delete");
    
    public MoviesPanel() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenuDeleteItem = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsMovies = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsAllActors = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsAllDirectors = new javax.swing.JList<>();
        btnEditMovie = new javax.swing.JButton();
        btnAddNewMovie = new javax.swing.JButton();

        popupMenuDeleteItem.setLabel("Test");

        setMinimumSize(new java.awt.Dimension(610, 490));
        setPreferredSize(new java.awt.Dimension(600, 469));

        lsMovies.setName("movies"); // NOI18N
        lsMovies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ListMouseReleased(evt);
            }
        });
        lsMovies.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                MovieSelectionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lsMovies);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Pick a movie");

        lsActors.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lsActors.setDragEnabled(true);
        lsActors.setName("actors"); // NOI18N
        lsActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ListMouseReleased(evt);
            }
        });
        lsActors.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                SelectedMoviePersonSelectionChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lsActors);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Actors");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Directors");

        lsDirectors.setDragEnabled(true);
        lsDirectors.setName("directors"); // NOI18N
        lsDirectors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ListMouseReleased(evt);
            }
        });
        lsDirectors.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                SelectedMoviePersonSelectionChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lsDirectors);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("All actors");

        lsAllActors.setDragEnabled(true);
        lsAllActors.setName("allActors"); // NOI18N
        jScrollPane4.setViewportView(lsAllActors);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("All directors");

        lsAllDirectors.setDragEnabled(true);
        lsAllDirectors.setName("allDirectors"); // NOI18N
        jScrollPane5.setViewportView(lsAllDirectors);

        btnEditMovie.setText("Edit selected movie");
        btnEditMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMovieActionPerformed(evt);
            }
        });

        btnAddNewMovie.setText("Add new movie");
        btnAddNewMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewMovieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(btnAddNewMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditMovie, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAddNewMovie)
                                    .addComponent(btnEditMovie)))
                            .addComponent(jScrollPane1))
                        .addGap(41, 41, 41))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MovieSelectionChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_MovieSelectionChanged
        try {
            selectedMovie = lsMovies.getSelectedValue();
        } catch (Exception e) {
            selectedMovie = null;
        }
        setActorsAndDirectors();
    }//GEN-LAST:event_MovieSelectionChanged

    private void ListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListMouseReleased
        check(evt);
    }//GEN-LAST:event_ListMouseReleased

    private void SelectedMoviePersonSelectionChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_SelectedMoviePersonSelectionChanged
        try {
            selectedList = (JList) evt.getSource();
            selectedMoviePerson = (MoviePerson) selectedList.getSelectedValue();
        } catch (Exception e) {
            System.out.println("Error" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_SelectedMoviePersonSelectionChanged

    private void btnAddNewMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewMovieActionPerformed
        AddMovieDialog dialog = new AddMovieDialog((JFrame) SwingUtilities.getWindowAncestor(this), true);
        dialog.setVisible(true);
        if (dialog.insertedMovie.isPresent()) {
                try {
                    updateMovieList(dialog.insertedMovie.get());
                } catch (Exception ex) {
                    MessageUtils.showErrorMessage("Error", "Unable to load movies from database." + ex.getMessage());
                }
        }
        dialog.dispose();
    }//GEN-LAST:event_btnAddNewMovieActionPerformed

    private void btnEditMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMovieActionPerformed
        if (lsMovies.getSelectedValue() == null)
            return;
        
        UpdateMovieDialog dialog = new UpdateMovieDialog((JFrame) SwingUtilities.getWindowAncestor(this), true, selectedMovie);
        dialog.setVisible(true);
        
        dialog.dispose();
    }//GEN-LAST:event_btnEditMovieActionPerformed

    public void check(java.awt.event.MouseEvent e) {
        if (null == e.getComponent().getName())
            return;
        else switch (e.getComponent().getName()) {
            case "actors":
                selectedList = lsActors;
                break;
            case "directors":
                selectedList = lsDirectors;
                break;
            case "movies":
                selectedList = lsMovies;
                break;
            default:
                return;
        }

        if (e.isPopupTrigger()) {
            selectedList.setSelectedIndex(selectedList.locationToIndex(e.getPoint()));
            popupMenuDeleteItem.show(selectedList, e.getX(), e.getY());
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewMovie;
    private javax.swing.JButton btnEditMovie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<MoviePerson> lsActors;
    private javax.swing.JList<MoviePerson> lsAllActors;
    private javax.swing.JList<MoviePerson> lsAllDirectors;
    private javax.swing.JList<MoviePerson> lsDirectors;
    private javax.swing.JList<Movie> lsMovies;
    private javax.swing.JPopupMenu popupMenuDeleteItem;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initPopup();
        repository = RepositoryFactory.getRepository();
        
        new Thread(() -> {
            try {
                fillMovieList();
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "Unknown error occured while trying to fetch movie data from database.");
            }
            listModels.add(allActorsListModel);
            listModels.add(allDirectorsListModel);
            listModels.add(selectedMovieActorsListModel);
            listModels.add(selectedMovieDirectorsListModel);
            
            initDragAndDrop();
        }).start();
    }

    private void fillMovieList() throws Exception {
        lsMovies.clearSelection();

        movies = repository.LoadMovies();
        
        moviesListModel.clear();
        movies.forEach((movie) -> {
            moviesListModel.addElement(movie);
        });
        
        lsMovies.setModel(moviesListModel);
        lsMovies.setSelectedIndex(0);
    }

    private void initPopup() {
        popupMenuDeleteItem.add(jmiDelete);
        jmiDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                try {
                    switch (selectedList.getName()) {
                        case "actors":
                            selectedMovie.removeActor(selectedMoviePerson);
                            repository.RemovePersonFromAMovie(selectedMoviePerson, selectedMovie);
                            break;
                        case "directors":
                            selectedMovie.removeDirector(selectedMoviePerson);
                            repository.RemovePersonFromAMovie(selectedMoviePerson, selectedMovie);
                            break;
                        case "movies":
                            repository.DeleteMovie(selectedMovie);
                            
                            index = moviesListModel.indexOf(selectedMovie);
                            
                            moviesListModel.removeElement(selectedMovie);
                            break;
                        default:
                            return;
                    }
                } catch (Exception exception) {
                }
                if (index > moviesListModel.indexOf(moviesListModel.firstElement())) {
                    lsMovies.setSelectedIndex(--index);
                } else if (index == moviesListModel.indexOf(moviesListModel.firstElement())) {
                    selectedList.setSelectedIndex(++index);
                }
                setActorsAndDirectors();
            }
        });
    }

    private void setActorsAndDirectors() {
        clearActorDirectorLists();
        if (selectedMovie != null) {
            selectedMovie.getActors().forEach((actor) -> {
                selectedMovieActorsListModel.addElement(actor);
            });
            selectedMovie.getDirectors().forEach((director) -> {
                selectedMovieDirectorsListModel.addElement(director);
            });
        
            lsActors.setModel(selectedMovieActorsListModel);
            lsDirectors.setModel(selectedMovieDirectorsListModel);

            try {
                allActors = repository.SelectAllOtherActors(selectedMovie);

                allActors.forEach(actor -> allActorsListModel.addElement(actor));
                lsAllActors.setModel(allActorsListModel);
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "Unable to fetch other actors");
            }

            try {
                allDirectors = repository.SelectAllOtherDirectors(selectedMovie);

                allDirectors.forEach(director -> allDirectorsListModel.addElement(director));
                lsAllDirectors.setModel(allDirectorsListModel);
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "Unable to fetch other directors");
            }
        }
    }

    private void initDragAndDrop() {
        lsAllActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllActors.setDragEnabled(true);
        lsAllActors.setTransferHandler(new ActorExportTransferHandler());
        
        lsActors.setDropMode(DropMode.ON);
        lsActors.setTransferHandler(new ActorImportTransferHandler());
        
        lsAllDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsAllDirectors.setDragEnabled(true);
        lsAllDirectors.setTransferHandler(new DirectorExportTransferHandler());
        
        lsDirectors.setDropMode(DropMode.ON);
        lsDirectors.setTransferHandler(new DirectorImportTransferHandler());
    }

    private void clearActorDirectorLists() {
        listModels.forEach(lm -> { lm.clear(); });
    }

    private void updateMovieList(Movie movie) {
        moviesListModel.addElement(movie);
    }

    @Override
    public void personUpdated() {
        try {
            fillMovieList();
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to fill movies list");
        }
    }
    
    private class ActorImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            try {
                if ( ((MoviePerson)support.getTransferable().getTransferData(ActorTransferable.ACTOR_FLAVOR)).getMoviePersonType() != MoviePersonType.ACTOR ) {
                    return false;
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("Error", "An unknown error occure while trying to drop object.");
            }
            
            return support.isDataFlavorSupported(ActorTransferable.ACTOR_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                MoviePerson actorInTransfer = (MoviePerson) transferable.getTransferData(ActorTransferable.ACTOR_FLAVOR);
                if (selectedMovie.addActor(actorInTransfer)) {
                    if (MessageUtils.showConfirmDialog("Add actor", "Are you sure?") == JOptionPane.YES_OPTION) {
                        repository.AssignPersonToAMovie(actorInTransfer, selectedMovie);
                        setActorsAndDirectors();
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    MessageUtils.showInformationMessage("", "Actor already in the cast.");
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("Error", "Unable to add actor " + ex.getMessage());
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "An error occured " + ex.getMessage());
            }
            return false;
        }
    }

    private class ActorExportTransferHandler extends TransferHandler {
        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new ActorTransferable(lsAllActors.getSelectedValue());
        }
    }
    
    private class DirectorImportTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            try {
                if ( ((MoviePerson)support.getTransferable().getTransferData(ActorTransferable.ACTOR_FLAVOR)).getMoviePersonType() != MoviePersonType.DIRECTOR ) {
                    return false;
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("Error", "An unknown error occure while trying to drop object.");
            }
            
            return support.isDataFlavorSupported(ActorTransferable.ACTOR_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                MoviePerson directorInTransfer = (MoviePerson) transferable.getTransferData(ActorTransferable.ACTOR_FLAVOR);
                if (selectedMovie.addDirector(directorInTransfer)) {
                    if (MessageUtils.showConfirmDialog("Add director", "Are you sure?") == JOptionPane.YES_OPTION) {
                        repository.AssignPersonToAMovie(directorInTransfer, selectedMovie);
                        setActorsAndDirectors();
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    MessageUtils.showInformationMessage("", "Director already in the cast.");
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                MessageUtils.showErrorMessage("Error", "Unable to add actor " + ex.getMessage());
            } catch (Exception ex) {
                MessageUtils.showErrorMessage("Error", "An error occured " + ex.getMessage());
            }
            return false;
        }
    }

    private class DirectorExportTransferHandler extends TransferHandler {
        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new DirectorTransferable(lsAllDirectors.getSelectedValue());
        }
    }
}
