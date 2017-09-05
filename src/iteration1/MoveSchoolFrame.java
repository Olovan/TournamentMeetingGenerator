package iteration1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MoveSchoolFrame extends JFrame {

	public Tournament tournament;
	private JPanel contentPane;
	private JButton btnAccept;
	private JComboBox schoolBox;
	private JComboBox destMatchBox;
	private JComboBox srcMatchBox;


	/**
	 * Create the frame.
	 */
	public MoveSchoolFrame(Tournament t) {
		tournament = t;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		srcMatchBox = new JComboBox();
		srcMatchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sourceUpdated();
			}
		});
		panel.add(srcMatchBox);
		srcMatchBox.setModel(new DefaultComboBoxModel(new String[] {"Select Match to move School from"}));
		
		destMatchBox = new JComboBox();
		panel.add(destMatchBox);
		destMatchBox.setModel(new DefaultComboBoxModel(new String[] {"Select Match to move School To"}));
		
		schoolBox = new JComboBox();
		contentPane.add(schoolBox);
		schoolBox.setModel(new DefaultComboBoxModel(new String[] {"Select School"}));
		
		btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accept();
			}
		});
		contentPane.add(btnAccept);
		setVisible(false);
	}
	
	//Listener for accept button
	private void accept() {
		//Make sure there are no errors
		if(!verifyArguments()) {
			JOptionPane.showMessageDialog(this, "Invalid Arguments", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		//Perform the swap
		int schoolIndex = schoolBox.getSelectedIndex();
		Match srcMatch = getMatchFromBox(srcMatchBox);
		Match destMatch = getMatchFromBox(destMatchBox);
		School targetSchool = srcMatch.schools.get(schoolIndex - 1);
		
		srcMatch.schools.remove(targetSchool);
		destMatch.schools.add(targetSchool);
		setVisible(false);
		return;
	}
	
	//Listener for SourceMatch dropdown changing 
	private void sourceUpdated() {
		schoolBox.removeAllItems();
		schoolBox.addItem("Select School");
		Match srcMatch = getMatchFromBox(srcMatchBox);
		
		if(srcMatch == null) return; //Bail out if error happens
		
		for(School s : srcMatch.schools) {
			schoolBox.addItem(s.schoolName);
		}
	}
	
	//verifies that you are making a legal transfer between different matches
	private boolean verifyArguments() {
		Match srcMatch = getMatchFromBox(srcMatchBox);
		Match destMatch = getMatchFromBox(destMatchBox);
		int schoolIndex = schoolBox.getSelectedIndex();
		
		//Everything must be set to a value
		if(srcMatch == null || destMatch == null || schoolIndex <= 0)
			return false;
		
		//The schools must both be from the same tournament level
		if(contains(tournament.sectionals, srcMatch) && contains(tournament.sectionals, destMatch))
			return true;
		if(contains(tournament.regionals, srcMatch) && contains(tournament.regionals, destMatch))
			return true;
		if(contains(tournament.semiState, srcMatch) && contains(tournament.semiState, destMatch))
			return true;
		
		return false;
	}
	
	//called when the window is displayed by the MainGui
	public void display() {
		setVisible(true);
		loadMatchInformation();
	}
	
	//Load in all the matches
	private void loadMatchInformation() {
		srcMatchBox.removeAllItems();
		destMatchBox.removeAllItems();
		schoolBox.removeAllItems();
		srcMatchBox.addItem("Select Match to move School from");
		destMatchBox.addItem("Select Match to move School to");
		schoolBox.addItem("Select School");
		
		for(int i = 0; i < tournament.sectionals.length; i++) {
			srcMatchBox.addItem("Sectional #" + (i+1));
			destMatchBox.addItem("Sectional #" + (i+1));
		}
		for(int i = 0; i < tournament.regionals.length; i++) {
			srcMatchBox.addItem("Regional #" + (i+1));
			destMatchBox.addItem("Regional #" + (i+1));
		}
		for(int i = 0; i < tournament.semiState.length; i++) {
			srcMatchBox.addItem("SemiState #" + (i+1));
			destMatchBox.addItem("SemiState #" + (i+1));
		}
	}
	
	private Match getMatchFromBox(JComboBox input) {
		int index = input.getSelectedIndex();
		
		if(index <= 0)
			return null;
		
		Match targetMatch;
		
		if(index <= tournament.sectionals.length) {
			targetMatch = tournament.sectionals[index - 1];
			return targetMatch;
		}
		index -= tournament.sectionals.length;
		
		if(index <= tournament.regionals.length) {
			targetMatch = tournament.regionals[index - 1];
			return targetMatch;
		}
		index -= tournament.regionals.length;
		
		if(index <= tournament.semiState.length) {
			targetMatch = tournament.semiState[index - 1];
			return targetMatch;
		}
		
		//If you reach this point you have a bogus index
		return null;
	}
	
	private boolean contains(Match[] array, Match target) {
		for(Match match : array) {
			if(match == target)
				return true;
		}
		return false;
	}

}
