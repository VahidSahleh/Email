import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveOb{
	User us1;
	public SaveOb(User us) {
		this.us1 = us;
		StringBuilder str = new StringBuilder(us1.getUsername());
		 str.append(".dat");
		 String a = str.toString();
		 //File myObj = new File(a);
		try {
			FileOutputStream fos = new FileOutputStream(new File(a));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(us1);
			
			
			oos.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
