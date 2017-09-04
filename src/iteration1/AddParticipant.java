package iteration1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddParticipant {

	private JFrame frame;
	private JTextField nameField;
	private JTextField enrollField;
	private JTextField latField;
	private JTextField longField;
	
	private String name;
	private int enrollment;
	private double lat;
	private double lon;
	private int sec;
	private int reg;
	private int semi;
	
	/**
	 * Launch the application.
	 */
	public static void AddParticipantScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddParticipant window = new AddParticipant();
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
	public AddParticipant() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddParticipant = new JLabel("Add Participant");
		lblAddParticipant.setBounds(161, 25, 134, 15);
		frame.getContentPane().add(lblAddParticipant);
		
		JLabel lblSchoolName = new JLabel("School Name");
		lblSchoolName.setBounds(44, 69, 139, 15);
		frame.getContentPane().add(lblSchoolName);
		
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setBounds(44, 122, 70, 15);
		frame.getContentPane().add(lblLatitude);
		
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setBounds(44, 149, 82, 15);
		frame.getContentPane().add(lblLongitude);
		
		JLabel lblEnrollment = new JLabel("Enrollment");
		lblEnrollment.setBounds(44, 96, 139, 15);
		frame.getContentPane().add(lblEnrollment);
		
		JCheckBox chckbxHostSectional = new JCheckBox("Host Sectional");
		chckbxHostSectional.setBounds(44, 200, 129, 23);
		frame.getContentPane().add(chckbxHostSectional);
		
		JCheckBox chckbxRegional = new JCheckBox("Regional");
		chckbxRegional.setBounds(187, 200, 99, 23);
		frame.getContentPane().add(chckbxRegional);
		
		JCheckBox chckbxSemistate = new JCheckBox("Semi-State");
		chckbxSemistate.setBounds(290, 200, 129, 23);
		frame.getContentPane().add(chckbxSemistate);
		
		nameField = new JTextField();
		nameField.setBounds(144, 69, 266, 19);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		enrollField = new JTextField();
		enrollField.setColumns(10);
		enrollField.setBounds(144, 95, 266, 19);
		frame.getContentPane().add(enrollField);
		
		latField = new JTextField();
		latField.setColumns(10);
		latField.setBounds(144, 122, 266, 19);
		frame.getContentPane().add(latField);
		
		longField = new JTextField();
		longField.setColumns(10);
		longField.setBounds(144, 148, 266, 19);
		frame.getContentPane().add(longField);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxHostSectional.isSelected()) 
					sec = 1;
				
				if (chckbxRegional.isSelected())
					reg = 1;
				
				if (chckbxSemistate.isSelected()) 
					semi = 1;
				
				name = nameField.getText();
				enrollment = Integer.parseInt(enrollField.getText());
				lat = Integer.parseInt(latField.getText());
				lon = Integer.parseInt(longField.getText());
				
				
				School newParticipant = new School(name, enrollment, 1, sec, reg, semi, lat, lon);
				
				// Remake the tournament
				
				JOptionPane.showMessageDialog(null, "Participant was added.", "Success", JOptionPane.INFORMATION_MESSAGE);
				
				frame.dispose();
				
			}
		});
		btnAdd.setBounds(161, 241, 117, 25);
		frame.getContentPane().add(btnAdd);
	}
}
