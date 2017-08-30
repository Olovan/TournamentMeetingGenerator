package iteration1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TournamentMenu {

	private JFrame frame;
	private JTextField textField;

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
		btnFind.setBounds(282, 152, 117, 25);
		frame.getContentPane().add(btnFind);
		
		textField = new JTextField();
		textField.setBounds(37, 152, 233, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(235, 215, 117, 25);
		frame.getContentPane().add(btnOk);
	}

}
