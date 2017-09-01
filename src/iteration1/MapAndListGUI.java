package iteration1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MapAndListGUI extends JFrame {
    // default window size
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    
    // menu bar and elements
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem generateMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem loadMenuItem;
    JMenuItem exitMenuItem;
    
    // output displays
    JPanel mapPanel;
    JPanel listPanel;
    
    // TODO Ryan
    // Constructor
    public MapAndListGUI() {
        // construct window
        setTitle("Tournament Meeting Generator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
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
        add(mapPanel, BorderLayout.WEST);
        add(listPanel, BorderLayout.EAST);
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
