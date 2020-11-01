package hr.algebra.models.transferables;

import hr.algebra.models.MoviePerson;
import hr.algebra.models.enums.MoviePersonType;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ActorTransferable implements Transferable {

    public static final DataFlavor ACTOR_FLAVOR = new DataFlavor(MoviePerson.class, "Actor");
    public static final DataFlavor[] SUPPORTED_FLAVORS = {ACTOR_FLAVOR};
    
    private final MoviePerson actor;
    public ActorTransferable(MoviePerson actor) {
        this.actor = actor;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(ACTOR_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(ACTOR_FLAVOR)) {
            if (actor.getMoviePersonType() == MoviePersonType.ACTOR) {
                return actor;
            }
        }
        throw new UnsupportedFlavorException(flavor);
    }
    
}