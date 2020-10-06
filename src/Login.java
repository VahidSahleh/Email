import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Login extends JFrame implements Serializable {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(155, 57, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(155, 107, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * try { FileInputStream fos1 = new FileInputStream(new File("output.dat"));
				 * ObjectInputStream ios = new ObjectInputStream(fos1); int a=0; while(a<3) {
				 * User user =(User)ios.readObject();
				 * if(user.getUsername().equals(textField.getText()) &&
				 * user.getPassword().equals(textField_1.getText())) { Profile pr = new
				 * Profile(); pr.setVisible(true);
				 * 
				 * } a++; } } catch (FileNotFoundException e) { // TODO Auto-generated catch
				 * block e.printStackTrace(); } catch (ClassNotFoundException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
				 * TODO Auto-generated catch block e.printStackTrace(); }
				 */
				
				User user = new User(textField.getText(), textField_1.getText());
				Client client = new Client();
				//send user and pass to server if it exist go to profile
				client.send(textField.getText() + "#" + textField_1.getText());
				String str = client.read();
				String[] tmp = str.split("#");
				if (textField.getText().equals(tmp[0]) && textField_1.getText().equals(tmp[1])) {
					Profile pr = new Profile();
					pr.setVisible(true);
				}

			}
		});
		btnNewButton.setBounds(165, 151, 97, 25);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(66, 60, 56, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pass:");
		lblNewLabel_1.setBounds(66, 110, 56, 16);
		contentPane.add(lblNewLabel_1);
	}
}
