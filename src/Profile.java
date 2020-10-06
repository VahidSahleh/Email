import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Caret;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Profile extends JFrame implements Serializable {
	ArrayList<String> al = new ArrayList<>();
	private JPanel contentPane;
	private JTextField textField_1;

	// private User user1,user2;
	// User user1,user2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField_1 = new JTextField();
		textField_1.setBounds(239, 39, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("Send to:");
		lblNewLabel.setBounds(171, 35, 56, 31);
		contentPane.add(lblNewLabel);
		User user1 = new User("vahid", "123");

		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBounds(258, 204, 97, 25);
		contentPane.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(46, 86, 324, 105);
		contentPane.add(textArea);
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(83, 303, 249, 63);
		contentPane.add(textArea_1);

		Client client = new Client();

		System.out.println("good");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				User user2 = new User(textField_1.getText(), "");

				try {
					// find user2 file
					StringBuilder str = new StringBuilder(user2.getUsername());
					str.append(".dat");
					String a = str.toString();
					FileInputStream fos1 = new FileInputStream(a);
					ObjectInputStream ios = new ObjectInputStream(fos1);

					// find user2 file and get the user
					User user3 = (User) ios.readObject();
					// send mail and user that you want to send a mail
					client.send(textArea.getText() + "#" + textField_1.getText());
					// read it from server
					String ss = client.read();
					System.out.println("good");
					// add the mail to arrayList of user that you send mail to.
					user3.getMg(ss);

					ios.close();
					// user2 = user3;
					// show the mails that you send on textArea.
					textArea_1.append(user3.getMessegae + "\n");
					textArea_1.append("\n");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		for (String a1 : user1.getMessegae) {
			textArea_1.append(a1 + "\n");
		}

	}
}
