package iteration1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateSchoolFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField enrollmentTextField;
	private JTextField latTextField;
	private JTextField lonTextField;
	private JCheckBox regionalCheckBox;
	private JCheckBox semiStateCheckBox;
	private JCheckBox sectionalCheckBox;
	private JComboBox sectionalComboBox;
	public Tournament tournament;

	/**
	 * Create the frame.
	 */
	public CreateSchoolFrame(Tournament t) {
		tournament = t;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JLabel lblAddNewSchool = new JLabel("Add New School");
		lblAddNewSchool.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_7.add(lblAddNewSchool);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 30));
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblSchoolName = new JLabel("School Name:");
		lblSchoolName.setMinimumSize(new Dimension(80, 14));
		lblSchoolName.setMaximumSize(new Dimension(80, 14));
		lblSchoolName.setPreferredSize(new Dimension(80, 14));
		lblSchoolName.setHorizontalAlignment(SwingConstants.TRAILING);
		panel.add(lblSchoolName);
		
		nameTextField = new JTextField();
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 30));
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblEnrollment = new JLabel("Enrollment:");
		lblEnrollment.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnrollment.setPreferredSize(new Dimension(80, 14));
		panel_1.add(lblEnrollment);
		
		enrollmentTextField = new JTextField();
		panel_1.add(enrollmentTextField);
		enrollmentTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(32767, 30));
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Latitude:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setPreferredSize(new Dimension(80, 14));
		panel_2.add(lblNewLabel);
		
		latTextField = new JTextField();
		panel_2.add(latTextField);
		latTextField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 30));
		contentPane.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLongitude.setPreferredSize(new Dimension(80, 14));
		panel_3.add(lblLongitude);
		
		lonTextField = new JTextField();
		panel_3.add(lonTextField);
		lonTextField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		sectionalCheckBox = new JCheckBox("Host Sectional");
		panel_4.add(sectionalCheckBox);
		
		regionalCheckBox = new JCheckBox("Host Regional");
		panel_4.add(regionalCheckBox);
		
		semiStateCheckBox = new JCheckBox("Host SemiState");
		panel_4.add(semiStateCheckBox);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		sectionalComboBox = new JComboBox();
		sectionalComboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Sectional"}));
		panel_6.add(sectionalComboBox);
		
		Component verticalGlue = Box.createVerticalGlue();
		contentPane.add(verticalGlue);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JButton btnCancel = new JButton("Create");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				create();
			}
		});
		panel_5.add(btnCancel);
		
		JButton btnCreate = new JButton("Cancel");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		panel_5.add(btnCreate);
	}
	
	//Event callback for "Create" button
	public School create() {
		School returnSchool;
		try {
			String name = nameTextField.getText();
			int enrollment = Integer.parseInt(enrollmentTextField.getText());
			double latitude = Double.parseDouble(latTextField.getText());
			double longitude = Double.parseDouble(lonTextField.getText());
			int hostSectionals = sectionalCheckBox.isSelected() ? 1 : 0;
			int hostRegionals = regionalCheckBox.isSelected() ? 1 : 0;
			int hostSemiState = semiStateCheckBox.isSelected() ? 1 : 0;
			returnSchool = new School(name, enrollment, 1, hostSectionals, hostRegionals, hostSemiState, longitude, latitude);
		} catch(Exception e) {
			errorMessage("Invalid Parameter Entered!");
			return null;
		}
		
		if(sectionalComboBox.getSelectedIndex() > 0) {
			Match selectedSectional = tournament.sectionals[sectionalComboBox.getSelectedIndex() - 1];
			selectedSectional.schools.add(returnSchool);
			setVisible(false);
			return returnSchool;
		} else {
			errorMessage("Must selected a host Sectional for the team");
			return null;
		}
	}
	
	private void errorMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Eror", JOptionPane.ERROR_MESSAGE);
	}
	
	//Event callback for "Cancel" button
	private void cancel() {
		setVisible(false);
	}
	
	//Called whenever the window is opened up by the Main GUI window
	public void display() {
		if(tournament == null) return;
		
		nameTextField.setText("");
		enrollmentTextField.setText("");
		latTextField.setText("");
		lonTextField.setText("");
		sectionalCheckBox.setSelected(false);
		regionalCheckBox.setSelected(false);
		semiStateCheckBox.setSelected(false);
		sectionalComboBox.removeAllItems();
		sectionalComboBox.addItem("Select Sectional");
		populateSectionalsList();
		setVisible(true);
	}
	
	private void populateSectionalsList() {
		if(tournament == null) return;
		
		for(int i = 0; i < tournament.sectionals.length; i++) {
			sectionalComboBox.addItem("Sectional #" + (i + 1));
		}
	}

}
