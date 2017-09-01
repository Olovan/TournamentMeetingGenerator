package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MapAndListGUI extends JFrame {
    // default file output path
    private final File OUT_PATH = new File("output/");

    // default window size
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    
    // menu bar and elements
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem generateMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem exitMenuItem;
    
    // output displays
    private JPanel mapPanel;
    private JPanel listPanel;
    
    // TODO Ryan
    // Constructor
    public MapAndListGUI(File selectedFile) {
    	super();
        setVisible(true);
    	
        // construct window
        setTitle("Tournament Meeting Generator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,2));
        
        // create menus
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        generateMenuItem = new JMenuItem("Generate New");
        generateMenuItem.setToolTipText("Generate new tournament");
        generateMenuItem.addActionListener(new GenerateListener());
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setToolTipText("Save current tournament");
        saveMenuItem.addActionListener(new SaveListener());
        loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setToolTipText("Load existing tournament");
        loadMenuItem.addActionListener(new LoadListener());
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setToolTipText("Close application");
        exitMenuItem.addActionListener(new ExitListener());
        
        // add menus to frame
        fileMenu.add(generateMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        
        // create output displays then add to frame
        mapPanel = new MapPanel();
        listPanel = new ListPanel();
        add(mapPanel);
        add(listPanel);
    }
    
    // TODO Ryan 
    public class GenerateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // [do the thing]
        }
    }
    
    // TODO Ryan 
    public class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Make an output file if needed
            if (!OUT_PATH.exists()) {
                OUT_PATH.mkdir();
            }
            
            // [do the thing]
        }
    }
    
    // TODO Ryan 
    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // [do the thing]
        }
    }
    
    // TODO Ryan 
    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
