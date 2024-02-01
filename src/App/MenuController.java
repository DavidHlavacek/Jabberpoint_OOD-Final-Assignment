package App;

import Backend.Accessor;
import Backend.XMLAccessor;
import Presentation.Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuController extends MenuBar implements Controller {
    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGE_NR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";
    protected static final String TESTFILE = "testPresentation.xml";
    protected static final String SAVEFILE = "savedPresentation.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    private void initialize(AppContainer appContainer) {
        Presentation presentation = appContainer.getPresentationComponent().getPresentation();
        MenuItem menuItem;

        // File Menu ========================================================
        Menu fileMenu = new Menu(FILE);
        // Open (will open a static presentation)
        fileMenu.add(menuItem = this.createMenuItem(OPEN));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                try {
                    xmlAccessor.loadFile(presentation, TESTFILE);
                    presentation.setSlideNumber(0);
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(appContainer, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
                }
                appContainer.repaint();
            }
        });

        // New (will clear the current presentation)
        fileMenu.add(menuItem = this.createMenuItem(NEW));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                presentation.clear();
                appContainer.repaint();
            }
        });

        // Save
        fileMenu.add(menuItem = this.createMenuItem(SAVE));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Accessor xmlAccessor = new XMLAccessor();
                try {
                    xmlAccessor.saveFile(presentation, SAVEFILE);
                } catch (IOException exc) {
                    JOptionPane.showMessageDialog(appContainer, IOEX + exc,
                            SAVEERR, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fileMenu.addSeparator();

        // Exit
        fileMenu.add(menuItem = this.createMenuItem(EXIT));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
//                presentation.exit(0);
                System.exit(0);
            }
        });
        add(fileMenu);

        // ========= View Menu ================================================
        Menu viewMenu = new Menu(VIEW);
        // Next
        viewMenu.add(menuItem = this.createMenuItem(NEXT));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                presentation.nextSlide();
            }
        });

        // Previous presentation.Slide
        viewMenu.add(menuItem = this.createMenuItem(PREV));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                presentation.prevSlide();
            }
        });

        // Go to slide
        viewMenu.add(menuItem = this.createMenuItem(GOTO));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String pageNumberStr = JOptionPane.showInputDialog((Object) PAGE_NR);
                int pageNumber = Integer.parseInt(pageNumberStr);
                presentation.setSlideNumber(pageNumber - 1);
            }
        });
        add(viewMenu);

        // === Help Menu =============================================================
        Menu helpMenu = new Menu(HELP);
        // About
        helpMenu.add(menuItem = this.createMenuItem(ABOUT));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                AboutBox.show(appContainer);
            }
        });

        //Needed for portability (Motif, etc.).
        setHelpMenu(helpMenu);
    }

    // Creating a menu-item
    public MenuItem createMenuItem(String menuName) {
        return new MenuItem(menuName, new MenuShortcut(menuName.charAt(0)));
    }

    @Override
    public void connect(AppContainer appContainer) {
        this.initialize(appContainer);
        appContainer.setMenuBar(this);
    }
}
