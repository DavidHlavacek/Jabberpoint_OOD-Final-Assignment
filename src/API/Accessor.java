package API;

import Presentation.Presentation;

import java.io.IOException;

public abstract class Accessor {

    public static Accessor getDemoAccessor() {
        return new DemoPresentation();
    }

    abstract public void loadFile(Presentation p, String fn) throws IOException;

    abstract public void saveFile(Presentation p, String fn) throws IOException;
}
