package iteration1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GenerateMenu {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void GenerateScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateMenu window = new GenerateMenu();
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
	public GenerateMenu() {
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
		
		JLabel lblSchoolsFile = new JLabel("Schools File");
		lblSchoolsFile.setBounds(178, 61, 137, 26);
		frame.getContentPane().add(lblSchoolsFile);
		
		textField = new JTextField();
		textField.setBounds(37, 152, 233, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.setBounds(282, 152, 117, 25);
		frame.getContentPane().add(btnFind);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(97, 215, 117, 25);
		frame.getContentPane().add(btnOptions);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(235, 215, 117, 25);
		frame.getContentPane().add(btnOk);
	}
}
