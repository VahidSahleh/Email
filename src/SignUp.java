import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.awt.event.ActionEvent;

public class SignUp extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(157, 48, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(157, 102, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				User user = new User(textField.getText(), textField_3.getText());

				Client client = new Client();
				// send user and pass form client to server
				client.send(textField.getText() + "#" + textField_3.getText());

				Profile pr = new Profile();
				pr.setVisible(true);
			}
		});
		btnNewButton.setBounds(157, 180, 97, 25);
		contentPane.add(btnNewButton);

	}
}