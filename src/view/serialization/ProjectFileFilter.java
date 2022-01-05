package view.serialization;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ProjectFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".rpf"));
    }

    @Override
    public String getDescription() {
        return "RuDok Project Files (*.rpf)";
    }
}
