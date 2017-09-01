package iteration1;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import java.io.File;
import java.awt.event.ActionListener;

public class TournamentMenu {

	private JFrame frame;
	private JTextField textField;
	private File selectedFile;

	/**
	 * Launch the application.
	 */
	public static void TournamentScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TournamentMenu window = new TournamentMenu();
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
	public TournamentMenu() {
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
		
		JLabel lblTournamentFile = new JLabel("Tournament File");
		lblTournamentFile.setBounds(160, 61, 137, 26);
		frame.getContentPane().add(lblTournamentFile);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				
				// Initial directory
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				
				// Allows only a certain extension to be chosen.
		        fileChooser.setAcceptAllFileFilterUsed(false);
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized File", "ser");
		        fileChooser.addChoosableFileFilter(filter);
		        fileChooser.showOpenDialog(null);
		        
				int result = fileChooser.showOpenDialog(fileChooser);
		     
				
				// Check if user selects a file
				if (result == JFileChooser.APPROVE_OPTION) {
				    selectedFile = fileChooser.getSelectedFile();
				    textField.setText(selectedFile.getAbsolutePath());
				    // TODO: Send to next menu
				} else {
					JOptionPane.showMessageDialog(null, "You didn't select a file.", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFind.setBounds(282, 152, 117, 25);
		frame.getContentPane().add(btnFind);
		
		textField = new JTextField();
		textField.setBounds(37, 152, 233, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selectedFile.length() != 0) {
					MapAndListGUI mapgui = new MapAndListGUI(selectedFile);
				} else {
					JOptionPane.showMessageDialog(null, "You didn't select a file.", "Warning", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnOk.setBounds(160, 213, 117, 25);
		frame.getContentPane().add(btnOk);
	}

}
