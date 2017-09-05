package iteration1;

import java.awt.EventQueue;

import java.io.File;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainGui {
	static final String CONFIG_FILE = "etc/config.txt";
	static final String ENROLLMENT_FILE = "etc/Boys_Enrollment.csv";

	private JFrame frame;
	public Tournament tournament;
	private TournamentComboBox matchComboBox;
	private ConfigWindow configWindow;
	private CreateSchoolFrame createSchoolFrame;
	private MoveSchoolFrame moveSchoolFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui window = new MainGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		tournament = new Tournament();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);

		JPanel backgroundPanel = new JPanel();
		frame.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
		backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.X_AXIS));

		MapPanel mapPanel = new MapPanel();
		mapPanel.setMaximumSize(new Dimension(640,640));
		backgroundPanel.add(mapPanel);

		JPanel matchPanel = new JPanel();
		matchPanel.setPreferredSize(new Dimension(400, 10));
		matchPanel.setMinimumSize(new Dimension(50, 10));
		backgroundPanel.add(matchPanel);
		matchPanel.setLayout(new BoxLayout(matchPanel, BoxLayout.Y_AXIS));

		matchComboBox = new TournamentComboBox("Sectional", null, mapPanel);
		matchPanel.add(matchComboBox);
		JPanel matchContentPanel = new JPanel();
		matchContentPanel.setLayout(new BoxLayout(matchContentPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollBarPanel = new JScrollPane(matchContentPanel);
		scrollBarPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		matchPanel.add(scrollBarPanel);
		matchComboBox.targetPanel = matchContentPanel;
		matchComboBox.loadMatches(tournament);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
	
		JMenu editMenu = new JMenu("Modify");
		JMenuItem createSchoolMenu = new JMenuItem("Create School");
		JMenuItem moveSchoolMenuItem = new JMenuItem("Move School");
		JMenuItem updateTournament = new JMenuItem("Update Tournament");
		editMenu.add(createSchoolMenu);
		editMenu.add(moveSchoolMenuItem);
		editMenu.add(updateTournament);
		menuBar.add(editMenu);
		createSchoolMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createSchoolFrame.tournament = tournament;
				createSchoolFrame.display();
			}
		});
		moveSchoolMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveSchoolFrame.tournament = tournament;
				moveSchoolFrame.display();
			}
		});
		updateTournament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				School[] participantsArray = new School[tournament.participants.size()];
				School[] hostsArray = new School[tournament.hosts.size()];
				tournament.participants.toArray(participantsArray);
				tournament.hosts.toArray(hostsArray);
				generateFromData(participantsArray, hostsArray, tournament.config);
				matchComboBox.loadMatches(tournament);
			}
		});

		JMenuItem saveTournamentMenuItem = new JMenuItem("Save Tournament");
		JMenuItem saveTournamentTextMenuItem = new JMenuItem("Save Tournament As Text");
		JMenuItem genTournamentMenuItem = new JMenuItem("Generate Tournament");
		JMenuItem loadTournamentMenuItem = new JMenuItem("Load Tournament");
		JMenuItem configMenuItem = new JMenuItem("Config Settings");
		mnNewMenu.add(saveTournamentMenuItem);
		mnNewMenu.add(saveTournamentTextMenuItem);
		mnNewMenu.add(loadTournamentMenuItem);
		mnNewMenu.add(genTournamentMenuItem);
		mnNewMenu.add(configMenuItem);

		saveTournamentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile();
			}
		});
		saveTournamentTextMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToTextFile();
			}
		});
		loadTournamentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadFromFile();
			}
		});
		genTournamentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File studentsFile = getStudentsFile();
				if(studentsFile == null) return;
				
				generateFromFiles(studentsFile.getPath(), CONFIG_FILE);
		}
		});
		configMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				configWindow.tournament = tournament;
				configWindow.display();
			}
		});


		JLabel lblNewLabel = new JLabel("Total Miles: X");
		frame.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);

		frame.pack();
		configWindow = new ConfigWindow(this);
		createSchoolFrame = new CreateSchoolFrame(tournament);
		moveSchoolFrame = new MoveSchoolFrame(tournament);
	}

	private void loadFromFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized File", "ser");
        JFileChooser fileChooser = new JFileChooser("./saved_files");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        
        if(result != JFileChooser.APPROVE_OPTION) return;
        
        File targetFile = fileChooser.getSelectedFile();
		tournament = Tournament.load(targetFile);
		
		matchComboBox.loadMatches(tournament);
	}

	private void saveToFile() {
		String fileName = JOptionPane.showInputDialog("Please Name the Tournament");
		if(fileName == null || fileName.isEmpty()) return; //Bail out if cancelled
		
		tournament.save(new File("saved_files/" + fileName + ".ser"));
	}
	
	private void saveToTextFile() {
		String fileName = JOptionPane.showInputDialog("Please Name the Tournament");
		if(fileName == null || fileName.isEmpty()) return; //Bail out if cancelled
		
		tournament.tournamentName = fileName;
		
		tournament.outputToFile(new File("./"));
	}
	
	private File getStudentsFile() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Student Enrollment File", "csv");
        JFileChooser fileChooser = new JFileChooser("./etc");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(frame);
        
        if(result != JFileChooser.APPROVE_OPTION) return null;
        
        return fileChooser.getSelectedFile();
	}
	
	private boolean generateFromData(School[] participants, School[] hosts, Configuration config) {
		if(!Tournament.isValid(participants, hosts, config)) {
			int secHosts = Tournament.getHostsForMeet(1, hosts, config).length;
			int regHosts = Tournament.getHostsForMeet(2, hosts, config).length;
			int semHosts = Tournament.getHostsForMeet(3, hosts, config).length;
			JOptionPane.showMessageDialog(frame, "Tournament cannot be generated with these configuration options. Not enough hosts \nYou have:\n " + secHosts + " Sectional Hosts\n" + regHosts + " Regional Hosts\n" + semHosts + "SemiState Hosts\nAvailable with your current configuration settings" , "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		tournament = new Tournament("Tournament", participants, hosts, config);
		return true;
	}

	private void generateFromFiles(String tournamentFile, String configFile) {
		List<School> allSchools = ReadSchoolFile.GetSchoolsFromFile(tournamentFile);
		Configuration config = new Configuration(configFile);

		List<School> hosts = new ArrayList<School>();
		List<School> participants = new ArrayList<School>();
		for(School school : allSchools)
		{
			if(school.participation != 0)
				participants.add(school);

			if(school.hostSectionals != 0 || school.hostRegionals != 0 || school.hostSemiState != 0)
				hosts.add(school);

			if(school.schoolName.contentEquals("Terre Haute LaVern Gibson"))
				hosts.add(school);
		}

		School[] hostsArray = new School[hosts.size()];
		School[] participantsArray = new School[participants.size()];
		hosts.toArray(hostsArray);
		participants.toArray(participantsArray);

		Tournament t = new Tournament("Boys Test Tournament", participantsArray, hostsArray, config);
		tournament = t;
		matchComboBox.loadMatches(tournament);
	}

	private class TournamentComboBox extends JComboBox implements ActionListener {
		public String levelName;
		public JPanel targetPanel;
		public Tournament tournament;
		public MapPanel map;

		public TournamentComboBox(String levelName, JPanel targetPanel, MapPanel map) {
			super();
			this.levelName = levelName;
			this.targetPanel = targetPanel;
			this.map = map;
			setPreferredSize(new Dimension(400, 20));
			setMaximumSize(new Dimension(400, 20));
			addActionListener(this);
		}

		public void loadMatches(Tournament t) {
			if(t == null) return;

			this.tournament = t;
			removeAllItems();

			if(t.sectionals.length > 0) {
				addItem("Show All Sectional Hosts");
				for(int i = 1; i < t.sectionals.length + 1; i++) {
					addItem("Sectional #" + i);
				}
			}

			if(t.regionals.length > 0) {
				addItem("Show All Regional Hosts");
				for(int i = 1; i < t.regionals.length + 1; i++) {
					addItem("Regional #" + i);
				}
			}

			if(t.semiState.length > 0) {
				addItem("Show All SemiState Hosts");
				for(int i = 1; i < t.semiState.length + 1; i++) {
					addItem("SemiState #" + i);
				}
			}
			if(t.finals.length > 0) {
				addItem("Show All Finals Hosts");
				for(int i = 1; i < t.finals.length + 1; i++) {
					addItem("Final #" + i);
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			targetPanel.removeAll();
			String selectedString = (String)getSelectedItem();

			if(selectedString == null) return;

			if(selectedString.startsWith("Show All")) {
				switch(selectedString) {
					case "Show All Sectional Hosts":
						map.displayMatch(tournament.sectionals);
						break;
					case "Show All Regional Hosts":
						map.displayMatch(tournament.regionals);
						break;
					case "Show All SemiState Hosts":
						map.displayMatch(tournament.semiState);
						break;
					case "Show All Finals Hosts":
						map.displayMatch(tournament.finals);
						break;
				}
			} else {
				int index = Integer.parseInt(selectedString.substring(selectedString.indexOf("#") + 1));
				Match targetMatch = null;
				if(selectedString.startsWith("Sectional")) {
					targetMatch = tournament.sectionals[index - 1];
				} else if(selectedString.startsWith("Regional")) {
					targetMatch = tournament.regionals[index - 1];
				} else if(selectedString.startsWith("SemiState")) {
					targetMatch = tournament.semiState[index - 1];
				} else if(selectedString.startsWith("Final")) {
					targetMatch = tournament.finals[index - 1];
				}

				map.displayMatch(targetMatch);
				targetPanel.add(new SchoolButton("Host: " + targetMatch.host.schoolName, targetMatch.host, map));
				for(int i = 0; i < targetMatch.schools.size(); i++) {
					if(targetMatch.schools.get(i) != targetMatch.host)
						targetPanel.add(new SchoolButton(targetMatch.schools.get(i).schoolName, targetMatch.schools.get(i), map));
				}
			}
			targetPanel.revalidate();
			targetPanel.repaint();
			frame.pack();
		}
	}

	private class SchoolButton extends JButton implements ActionListener {

		JFrame root;
		School school;
		MapPanel map;

		public SchoolButton(String name, School school, MapPanel map) {
			super();
			setText(name);
			this.school = school;
			this.map = map;
			this.setPreferredSize(new Dimension(200, 20));
			this.setMinimumSize(new Dimension(200, 20));
			this.setMaximumSize(new Dimension(400, 20));
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			map.displaySchool(school);
		}

	}

	//This is the popup Window that controls the Configuration Settings
	private class ConfigWindow extends JFrame {
		public Tournament tournament;

		private JTextField numSectionals;
		private JTextField numRegionals;
		private JTextField numSemiStates;

		private JTextField minSectionals;
		private JTextField minRegionals;
		private JTextField minSemiStates;
		
		private JCheckBox useBreakpointsCB;
		private JTextField breakpointsTF;
		private JTextField selectedBreakpointTF;
		private JButton acceptBtn;
		private JButton cancelBtn;

		public ConfigWindow(MainGui inputParent) {
			super();
			tournament = inputParent.tournament;
			numSectionals = new JTextField(2);
			numRegionals = new JTextField(2);
			numSemiStates = new JTextField(2);

			minSectionals = new JTextField(3);
			minRegionals = new JTextField(3);
			minSemiStates = new JTextField(3);
			
			breakpointsTF = new JTextField(10);
			useBreakpointsCB = new JCheckBox();
			
			selectedBreakpointTF = new JTextField(2);
			
			acceptBtn = new JButton("Accept");
			cancelBtn = new JButton("Cancel");

			JPanel content = new JPanel(new GridLayout(0, 2));
			setContentPane(content);
			content.add(new JLabel("Number of Sectionals: "));
			content.add(numSectionals);
			content.add(new JLabel("Number of Regionals: "));
			content.add(numRegionals);
			content.add(new JLabel("Number of SemiStates: "));
			content.add(numSemiStates);
			content.add(new JLabel("Minimum Teams Per Sectional"));
			content.add(minSectionals);
			content.add(new JLabel("Minimum Teams Per Regional"));
			content.add(minRegionals);
			content.add(new JLabel("Minimum Teams Per SemiState"));
			content.add(minSemiStates);
			content.add(new JLabel("Use Breakpoints"));
			content.add(useBreakpointsCB);
			content.add(new JLabel("Selected Class Breakpoint"));
			content.add(selectedBreakpointTF);
			content.add(new JLabel("Breakpoints"));
			content.add(breakpointsTF);
			content.add(acceptBtn);
			content.add(cancelBtn);
			
			acceptBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					save();
					setVisible(false);
				}
			});
			cancelBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

			setPreferredSize(new Dimension(300, 300));

			pack();
			loadConfigValues();
		}

		public void loadConfigValues() {
			if(tournament == null) return;
			
			Configuration config = tournament.config;

			numSectionals.setText(config.numberOfSectionalHosts + "");
			numRegionals.setText(config.numberOfRegionalHosts + "");
			numSemiStates.setText(config.numberOfSemiStateHosts + "");
			minSectionals.setText(config.minTeamsPerHost[0] + "");
			minRegionals.setText(config.minTeamsPerHost[1] + "");
			minSemiStates.setText(config.minTeamsPerHost[2] + "");
			useBreakpointsCB.setSelected(config.useBreakpoints);
			breakpointsTF.setText(printArray(config.classBreakpoints));
			selectedBreakpointTF.setText(config.selectedBreakpoint + "");
		}

		public void display() {
			setVisible(true);
			loadConfigValues();
		}

		public void save() {
			Configuration config = new Configuration();
			config.numberOfSectionalHosts = Integer.parseInt(numSectionals.getText());
			config.numberOfRegionalHosts = Integer.parseInt(numRegionals.getText());
			config.numberOfSemiStateHosts = Integer.parseInt(numSemiStates.getText());
			config.minTeamsPerHost[0] = Integer.parseInt(minSectionals.getText());
			config.minTeamsPerHost[1] = Integer.parseInt(minRegionals.getText());
			config.minTeamsPerHost[2] = Integer.parseInt(minSemiStates.getText());
			config.useBreakpoints = useBreakpointsCB.isSelected();
			config.selectedBreakpoint = Integer.parseInt(selectedBreakpointTF.getText());
			config.parseString("CLASS_BREAKPOINTS " + breakpointsTF.getText());
			
			tournament.config = config;
		}

		public void cancel() {
			setVisible(false);
		}
		
		private String printArray(int[] input) {
			if(input.length <= 0)
				return "";
			
			String retVal = "";
			retVal += input[0];
			
			for(int i = 1; i < input.length; i++)
				retVal += " " + input[i];
			
			return retVal;
		}

	}
}
