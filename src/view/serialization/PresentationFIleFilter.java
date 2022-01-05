package view.serialization;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PresentationFIleFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".rdf"));
    }

    @Override
    public String getDescription() {
        return "RuDok Presentation Files (*.rdf)";
    }
}
