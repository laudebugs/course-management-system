package homework1;
/**
 * This is the course management System to Maintain and manage Courses at a University
 *  @author Laurence Bugasu Ininda
 *  Sat 10th Feb 2018
 */
import java.io.*;
import java.util.*
;public class App {

	public static void main(String[]args) throws FileNotFoundException{
		System.out.println("Welcome to the course Management System.");
		ArrayList<Course>courses=new ArrayList<Course>();
		ArrayList<Student>students=new ArrayList<Student>();
		//We try to deserialize the file "Courses.ser"
		//If the Serialized file exists, then, We will load the data from it, 
		//otherwise we will load data from the MyUniversityCourses.csv" file
		File f = new File("Courses.ser");
		File g = new File("students.ser");
		if (f.exists()&& !f.isDirectory() && g.exists()&& !g.isDirectory()) {
			try {
				 //FileInputSystem recieves bytes from a file
				FileInputStream fis  = new FileInputStream("Courses.ser");
				FileInputStream fis2 = new FileInputStream("students.ser");
				//ObjectInputStream does the deserialization-- it reconstructs the data into an object
				ObjectInputStream ois  = new ObjectInputStream (fis);
				ObjectInputStream ois2 = new ObjectInputStream (fis2);
				//Cast as Employee. readObject will take the object from ObjectInputStream
			    courses = (ArrayList<Course>)ois.readObject();
			    students = (ArrayList<Student>)ois2.readObject();
			    ois.close();
			    fis.close();
			}
			catch(Exception exe) {
	
			}
		}
		else {
			Scanner input = new Scanner(new File("src/homework1/MyUniversityCourses.csv"));
			//Read out the first line that is not necessary in this program
			input.nextLine();
			//Create the Course Objects if they never existed in the first place
			while(input.hasNextLine()) {
				//Parsing and breaking down the csv file into values
				String[]r=input.nextLine().split(",");
				Course C = new Course(r[0], r[1], Integer.parseInt(r[2]), Integer.parseInt(r[3]),students, r[5], Integer.parseInt(r[6]),  r[7]);
				courses.add(C);
			}
		}
		Admin ad = new Admin();
		Scanner in = new Scanner(System.in);
		//This will check whether the user wants to keep running the program
		boolean keepRunning = true;
		while (keepRunning) {
			
			//Ask for Username and Password
			System.out.println("Please Login... ");
			System.out.println("Input your Username:");
			String username = in.nextLine();
			System.out.println("Input your password:");
			String password = in.nextLine();
			
			//Check whether user is an Admin or Student
			if (username.equals(ad.getUsername()) && password.equals(ad.getPassword())){
				try {
					ad.do_stuff(courses, students);
				}
				catch (IOException e) {
					System.out.println("Sorry, Something went wrong");
				}
			}
			else if (username.equals("Student") && password.equals("Student101")){
	
				System.out.println("Enter your First name: ");
				String fname = in.nextLine();
				System.out.println("Enter your Last name: ");
				String lname = in.nextLine();
				
				//A cboolean variable to check whether student was already registered in the system
				boolean exists= false;
				for(int i=0;i<students.size();i++) {
					if (students.get(i).getFirstname().equals(fname)&&students.get(i).getLastname().equals(lname)) {
						System.out.println("Welsome Back...");
						
						//This method loads all other student commands
						students.get(i).do_stuff(courses);
						exists= true;
						break;
					} 
				}
				//If student wasn't registered, now the app wil register them
				if (!exists) {
					System.out.println("You are a new Student.");
					System.out.println("You are being registered to the system...\n");
					Student st = new Student(fname, lname);
					students.add(st);
					st.do_stuff(courses);
				}
			}
			else {
				System.out.println("Incorrect Login Information. Please try again");
			}
			//FileOutput Stream writes data to a file
			//We serialize all the Students and Courses ArrayList
			try {
				FileOutputStream fos = new FileOutputStream("courses.ser");
				FileOutputStream fos2 = new FileOutputStream("students.ser");
				//ObjectOutputStream writes objects to a stream (A sequence of data)
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
				//Writes the specific object to the OOS
				oos.writeObject(courses);
				oos2.writeObject(students);
				//Close both streams
				oos.close();
				oos2.close();
				fos.close();
				fos2.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			//Ask the user if they want to exit or not
			System.out.println("Exit program?\nY for yes\nN for No");
			String choice = in.nextLine();
			if (choice.equals("Y")) {
				System.out.println("Exiting Program...");
				keepRunning =false;
			}
			
		}
		in.close();
	}
}
