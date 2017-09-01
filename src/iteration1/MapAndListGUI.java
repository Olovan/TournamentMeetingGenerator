package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MapAndListGUI extends JFrame {
    // object the GUI is displaying
    private Tournament currentTournament;

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
    private JMenu modifyMenu;
    private JMenuItem changeHostItem;
    private JMenuItem changeParticipantItem;
    
    // output displays
    private JPanel mapPanel;
    private JPanel listPanel;
    
    // TODO Ryan
    // Constructor
    public MapAndListGUI(File selectedFile) {
    	super();
        setVisible(true);
    	
        //Generate Tournament
        generateTournament(selectedFile);
        
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
        modifyMenu = new JMenu("Modify");
        // [ADD ACTION LISTENER]
        changeHostItem = new JMenuItem("Change Host(s)");
        changeParticipantItem = new JMenuItem("Change Participant(s)");
        // [ADD ACTION LISTENER]
        
        // add menus to frame
        fileMenu.add(generateMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.add(exitMenuItem);
        modifyMenu.add(changeHostItem);
        modifyMenu.add(changeParticipantItem);
        menuBar.add(fileMenu);
        menuBar.add(modifyMenu);
        setJMenuBar(menuBar);
        
        // create output displays then add to frame
        mapPanel = new MapPanel();
        listPanel = new ListPanel();
        add(mapPanel);
        add(listPanel);
    }
    
    // TODO Ryan
    public boolean generateTournament(File selectedFile) {
        // discern file Enrollment(.csv) or Saved Tournament(.ser)
        // if .csv call ReadSchoolFile()
        // if .ser call LoadSavedTournament()
        
        return true;
    }
    
    // TODO Ryan
    private boolean loadSavedTournament(File saveFile) {
        Tournament savedTourney = null; // [TEMP]

        // deserialize Tourney
        
        currentTournament = savedTourney;
        return true;
    }
    
    // TODO Ryan 
    public class GenerateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // new GenerateMenu()
        }
    }
    
    // TODO Ryan 
    // [TEST!!!]
    public class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Successful saving of tournament schedule or meet data.
            Boolean result;

            // Make an output file if needed
            if (!OUT_PATH.exists()) {
                OUT_PATH.mkdir();
            }
            
            // format tournament schedule and save as a text file within the output folder.
            result = currentTournament.outputToFile(OUT_PATH);
            if (result == false) {
                JOptionPane.showMessageDialog(null, 
                                              "Failed to save current tournament schedule as a table.",
                                              "Output Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,
                                              "Successfully saved current tournament schedule as a table",
                                              "Output Message",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
            
            
            // save current tournament data to output file
            result = currentTournament.save(OUT_PATH);
            if (result == false) {
                JOptionPane.showMessageDialog(null, 
                                              "Failed to save current tournament data.",
                                              "Output Warning",
                                              JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null,
                                              "Current tournament data has been saved.",
                                              "Output Message",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // TODO Ryan 
    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // new TournamentMenu()
        }
    }
    
    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
