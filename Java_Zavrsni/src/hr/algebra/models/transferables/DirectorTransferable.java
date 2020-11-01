package hr.algebra.models.transferables;

import hr.algebra.models.MoviePerson;
import hr.algebra.models.enums.MoviePersonType;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DirectorTransferable implements Transferable {

    public static final DataFlavor DIRECTOR_FLAVOR = new DataFlavor(MoviePerson.class, "Director");
    public static final DataFlavor[] SUPPORTED_FLAVORS = {DIRECTOR_FLAVOR};
    
    private final MoviePerson director;
    public DirectorTransferable(MoviePerson director) {
        this.director = director;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DIRECTOR_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(DIRECTOR_FLAVOR)) {
            if (director.getMoviePersonType() == MoviePersonType.DIRECTOR) {
                return director;
            }
        }
        throw new UnsupportedFlavorException(flavor);
    }
    
}