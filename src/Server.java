import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	static ArrayList<MyThread> list = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ArrayList<MyThread> list = new ArrayList<MyThread>();
		System.out.println("server");
		ServerSocket server = null;
		while (true) {
			try {
				server = new ServerSocket(12);

				Socket socket = server.accept();
				MyThread th = new MyThread(socket);
				th.start();
				th.saveUser();
				th.logUser();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	static class MyThread extends Thread {
		Socket socket;

		public MyThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				while (true) {
					DataInputStream in = new DataInputStream(this.socket.getInputStream());
					DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
					// String str=in.readUTF();
					String str1 = in.readUTF();
					// String[] tmp=str.split("#");
					String[] tmp1 = str1.split("#");
					// User st=new User(tmp[0]);
					// Mail mail = new Mail(tmp1[0]);
					// String mm = mail.setMail(mail.Mail);

					out.writeUTF(str1);

					// 17
					System.out.println(str1);
					// System.out.println(mail.Mail);

					// System.out.println(s.list);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
//save new user into file
		public void saveUser() {

			try {
				while (true) {
					DataInputStream in = new DataInputStream(this.socket.getInputStream());
					DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
					
					//get name and pass from client
					String str = in.readUTF();
					String[] tmp = str.split("#");
					User user1 = new User(tmp[0], tmp[1]);
					//get users name and make it string and use it for name of the file.
					String b = tmp[0].toString();
					StringBuilder str1 = new StringBuilder(b);
					out.writeUTF(str);
					System.out.println(str);
					str1.append(".dat");
					String a = str.toString();
					try {
						//create new file 
						FileOutputStream fos = new FileOutputStream(new File(a));
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						//add user object to file
						oos.writeObject(user1);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block

					} catch (IOException e) {
						// TODO Auto-generated catch block

					}
					// User user1 = new User(user , pass);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//login in with user object
		public void logUser() {
			try {
				while (true) {
					DataInputStream in = new DataInputStream(this.socket.getInputStream());
					DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
					
					Client cl = new Client();
					
					//read username and password from login class using client
					String str = cl.read();
					String[] tmp = str.split("#");
					User user1 = new User(tmp[0], tmp[1]);
					// out.writeUTF(str);
					System.out.println(tmp);
					//get the username of user object and use it for finding name of file 
					StringBuilder str1 = new StringBuilder(tmp[0]);
					str1.append(".dat");
					String a = str.toString();
					try {
						FileInputStream fos1 = new FileInputStream(a);
						ObjectInputStream ios = new ObjectInputStream(fos1);
						User user = (User) ios.readObject();
						
						if (user1.getUsername().equals(user.getUsername())
								&& user1.getPassword().equals(user.getPassword())) {
							System.out.print("true");
							//send the username and pass form server to client
							out.writeUTF(user.getUsername() + "," + user.getPassword());
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// 17
					// System.out.println(str);
					// System.out.println(tmp[0]);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public void sendMail() {

			try {
				while (true) {
					DataInputStream in = new DataInputStream(this.socket.getInputStream());
					DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
					Client cl = new Client();
					String str = cl.read();
					String[] tmp = str.split("#");
					System.out.println(tmp);
					StringBuilder str1 = new StringBuilder(tmp[1]);
					str1.append(".dat");
					String a = str.toString();
					try {
						FileInputStream fos1 = new FileInputStream(a);
						ObjectInputStream ios = new ObjectInputStream(fos1);
						User user = (User) ios.readObject();
						if (tmp[1].equals(user.getUsername())) {
							System.out.print("true");
							//send mail from server to client
							out.writeUTF(tmp[0]);
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
