package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class MapAndListGUI extends JFrame {
    // object the GUI is displaying
    protected Tournament currentTournament;
    
    // default file output path
    private final File OUT_PATH = new File("output/");

    // default window size and formating
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private GridLayout layout = new GridLayout(1,2);
    
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
    
    // TODO Ryan or Quentin
    // Constructor
    public MapAndListGUI(File selectedFile) {
    	super();
        setVisible(true);
    	
        // Generate Tournament
        generateTournament(selectedFile);
        
        // construct window
        setTitle("Tournament Meeting Generator");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        
        // create file menu
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
        
        // create modify menu
        modifyMenu = new JMenu("Modify");
        changeHostItem = new JMenuItem("Change Host(s)");
        changeHostItem.addActionListener(new ChangeHostListener());
        changeParticipantItem = new JMenuItem("Change Participant(s)");
        changeParticipantItem.addActionListener(new ChangeParticipantListener());
        
        // add menus to bar then bar to window
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
        //listPanel = new ListPanel(currentTournament, mapPanel); Will crash until generateMenu() has been completed
        add(mapPanel);
        //add(listPanel); Will crash until generateMenu() has been completed
    }
    
    // TODO Ryan or Quentin
    public boolean generateTournament(File selectedFile) {
        // discern file Enrollment(.csv) or Saved Tournament(.ser)
        
        //THIS IS GOING TO GET MESSY!
        // if .csv call ReadSchoolFile(selectedFile.getName()) then current Tournament = generated Tournament
        // if .ser call LoadSavedTournament(selectedFile)
        return true;
    }
    
    // TODO Ryan or Quentin
    private boolean loadSavedTournament(File saveFile) {
        Tournament savedTourney = null; // [TEMP]

        // deserialize Tourney
        
        currentTournament = savedTourney;
        
        // updateListPanel()
        
        return true;
    }
    
    // TODO Ryan or Quentin
    private boolean refreshListPanel() {
        // refresh listPanel with updated currentTournament information
        
        return true;
    }
    
    // TODO Ryan or Quentin
    public class GenerateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get enrollement file (.csv)
            // pass file to generateTournament
        }
    }
    
    // TODO Ryan 
    // [TEST]
    // Output torunament table to txt and serilize current Tournament for later loading.
    public class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Successful saving of tournament schedule or meet data.
            Boolean result;
            
            // Get tournament name from user and update
            String newName = (String)JOptionPane.showInputDialog(null,
                                                                 "What would you like to call this current tournament setup?",
                                                                 "Please Name Tournament",
                                                                 JOptionPane.QUESTION_MESSAGE);
            // rename current Tournament if valid entry
            if ((newName != null) && (newName.length() > 0)) {
                // rename Tournament
                currentTournament.tournamentName = newName;
            }
            else {
                // exit method
                JOptionPane.showMessageDialog(null,
                                              "Name is invalid, unable to save.",
                                              "Output Warning",
                                              JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            
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
    
    // TODO Ryan or Quentin
    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // get Tournament file (.ser)
            // pass file to loadSavedTournament()
        }
    }
    
    // Exit Application
    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    // TODO Ryan or Quentin
    public class ChangeHostListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // have user modify the hosts for torunaments from a new window
            // make sure to save modficications to the current tournament object!
        }
    }
    
    // TODO Ryan or Quentin
    public class ChangeParticipantListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // have user modify the competitors in each of the meets from a new window
            // make sure to save modficications to the current tournament object!
        }
    }
}
