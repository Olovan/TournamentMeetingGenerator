package iteration1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MapAndListGUI extends JFrame {   
    // defaults settings
    private final String CONFIG_FILE = "etc/config.txt";
    private final String TOURNAMENT_NAME = "IHSAA Cross Country Running Tournament v.0";
    private final File OUT_PATH = new File("output/");
    
    // default window size and formating
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private GridLayout layout = new GridLayout(1,2);
    
    // object the GUI is displaying
    protected Tournament currentTournament;
    
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
    private MapPanel mapPanel;
    private JPanel listPanel;
    
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
    }
    
    // Creates map and button display.
    private void createPanels() {
        // remove outdated components if needed
        if (mapPanel != null) {
            remove(mapPanel);
        }
        if (listPanel != null) {
            remove(listPanel);
        }
        
        mapPanel = new MapPanel();
        listPanel = new ListPanel(currentTournament, mapPanel);
        
        add(mapPanel);
        add(listPanel);
        
        // refresh GUI
        this.repaint();
        this.revalidate();
    }

    // Generate+Display a new Tournament based on a given enrollment file and configuration settings.
    public boolean generateTournament(File selectedFile) {
        // discern file Enrollment(.csv) or Saved Tournament(.ser)
        String fileName = selectedFile.getName();
        if (fileName.contains(".csv")) {
            // read schools from enrollement file and set config
            List<School> allSchools = ReadSchoolFile.GetSchoolsFromFile(selectedFile.getAbsolutePath());
            Configuration config = new Configuration(CONFIG_FILE);
            
            // Create list of hosts and participants from allSchools
            List<School> hosts = new ArrayList<School>();
            List<School> participants = new ArrayList<School>();
            for(School school : allSchools) {
                if(school.participation != 0)
                    participants.add(school);

                if(school.hostSectionals != 0 || school.hostRegionals != 0 || school.hostSemiState != 0)
                    hosts.add(school);

                if(school.schoolName.contentEquals("Terre Haute LaVern Gibson"))
                    hosts.add(school);
            }
            
            // convert lists into arrays
            School[] hostsArray = new School[hosts.size()];
            School[] participantsArray = new School[participants.size()];
            hosts.toArray(hostsArray);
            participants.toArray(participantsArray);
            
            // create Tournament
            currentTournament = new Tournament(TOURNAMENT_NAME,participantsArray, hostsArray, config);
            
            // display
            createPanels();
        }
        else if (fileName.contains(".ser")) {
            loadSavedTournament(selectedFile);
        }
        else {
            System.exit(0);
        }
        
        // send confirmation
        JOptionPane.showMessageDialog(null,
                                      "Successfully generated tournament.",
                                      "Output Message",
                                      JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    // Load previously generated/modifies Tournament and display to GUI.
    // @return: True if Tournament was successfully loaded to GUI,
    //          False otherwise.
    private boolean loadSavedTournament(File saveFile) {
        Tournament savedTourney;

        // deserialize Tourney
        try {
            // read object from file
            FileInputStream file = new FileInputStream(saveFile.getAbsoluteFile());
            ObjectInputStream in = new ObjectInputStream(file);
            
            // deserialize Tournament
            savedTourney = (Tournament)in.readObject();
            
            // close file
            in.close();
            file.close();
            
            // update GUI
            currentTournament = savedTourney;
            createPanels();
        
            //send confirmation
            JOptionPane.showMessageDialog(null,
                                          "Successfully loaded tournament.",
                                          "Output Message",
                                          JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        catch (IOException | ClassNotFoundException ex){
            // warning message
            JOptionPane.showMessageDialog(null, 
                                              "Failed to load tournament data.",
                                              "Output Warning",
                                              JOptionPane.WARNING_MESSAGE);
            System.out.println(ex.toString());
            return false;
        }
    }
    
    // Select a file to input from.
    // @return: Chosen file for reading.
    private File chooseFile(String type, String ext) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(type, ext);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(filter);
        
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        else {
            JOptionPane.showMessageDialog(null,
                                          "You did not select a file.",
                                          "Input Message",
                                          JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }
    
    // Create a new Tournament and display to GUI.
    public class GenerateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            File enrollmentFile = chooseFile("Comma Separated Values", "csv");
            if (enrollmentFile != null) {
                generateTournament(enrollmentFile);
            }
        }
    }
    
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
                                              "Successfully saved current tournament schedule as a table.",
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
    
    // Load previously saved Tournament and display to GUI. 
    public class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            File tourneyFile = chooseFile("Serialized File", "ser");
            if (tourneyFile != null) {
                loadSavedTournament(tourneyFile);
            }
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
