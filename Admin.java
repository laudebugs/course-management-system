package homework1;

/**
 * @author Laurence Bugasu Ininda
 */

import java.io.*;
import java.util.*;

public class Admin extends User implements Admin_Interface {


	public Admin() {
		this.setUsername("Admin");
		this.setPassword("Admin001");
		
	}
	/*
	This is the method where an admin can run all his commands
	*/
	public void do_stuff(ArrayList<Course>courses, ArrayList<Student>students) throws  IOException {
		//Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do today?\n");
		System.out.println("Choose one of the following options: ");
		System.out.println("Type 1 to Create a new Course\n"+
		"Type 2 to Delete a Course \n"+
				"Type 3 to Edit a Course\n"+
		"Type 4 To distplay information on a given course\n"+
				"Type 5 to Register a student without assigning them a course\n\n"+
		"Reports:\n"+
				"Type 6 to view all Courses\n"+
		"Type 7 to view all courses that are full\n"+
				"Type 8 to Output to a file the list of courses that are full\n"+
		"Type 9 to view the list of courses that a given student is registered on\n"+
				"Type 10 to Sort courses based on the current number of students registered\n"+
		"Type 11 to exit the Course Management System\n");
	
		//A boolean value that determines whether the dmin wants to quit or not
		boolean stop_sign = true;
		while(stop_sign) {
			Scanner in = new Scanner(System.in);
			
			//Ask the Admin for an action to perform
			System.out.println("Select an action to perform:");
			int input = in.nextInt();

			switch (input) {
			
			//A Case switch statement to switch between options that the admin selects
			case 1:this.create_a_new_course(courses);
				break;
			case 2:this.delete_a_course(courses);
				break;
			case 3:this.edit_a_course(courses);
				break;
			case 4: 
				//Display information on a given course
				Scanner in4 = new Scanner(System.in);
				Course c=null;
				System.out.println("Input the Course ID of the course that you would like to edit");
				String CID = in4.nextLine();
				for (int i=0;i<courses.size();i++) {
					if (courses.get(i).getCourse_ID().equals(CID)) {
						c= courses.get(i);
						break;
					}
				}
				c.display_course_info();
				//in4.close();
				break;
			case 5:register_new_student(students);
				break;
			case 6:view_all_courses(courses);
				break;
			case 7:this.view_full_courses(courses);
				break;
			case 8:
				//Output to a file the courses that are full
				System.out.println("You will output to a file named \"CoursesFull.txt\" the courses that are full.");
				BufferedWriter writer = new BufferedWriter(new FileWriter("CoursesFull.txt"));
				for(int i=0;i<courses.size();i++) {
					if (courses.get(i).getCurrently_enrolled_students()==courses.get(i).getMax_students()) {
						writer.write(courses.get(i).getCourse_name()+"\n");
					}
				}
				writer.close();
				break;
			case 9:
				Scanner in8= new Scanner(System.in);
				System.out.println("Input the student's first name: ");
				String fname = in8.nextLine();
				System.out.println("Input the student's last name : ");
				String lname = in8.nextLine();
				Student s=null;
				for(int i=0;i<students.size();i++) {
					if (students.get(i).getFirstname().equals(fname) && students.get(i).getLastname().equals(lname)){
						s=students.get(i);
						break;
					} 
				}
				this.view_registered_courses(s);
				//in8.close();
				break;
			case 10:courses=this.sort_on_registered_students(courses);
				break;
			case 11:
				stop_sign=false;
				System.out.println("You are now exiting the program...");
			}
		}
	}
	//This method creates a new course
	public void create_a_new_course(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);
		
		//Get information on the course
		System.out.println("You are creating a new course:...\n");
		System.out.println("Type in the name of the Course");
		String cname = in.nextLine();
		System.out.println("Input the Course ID");
		String cID = in.nextLine();
		System.out.println("Input the Course Instructor's name");
		String cInstructor = in.nextLine();
		System.out.println("Input the course location");
		String cLocation = in.nextLine();
		System.out.println("Input the course Section Number");
		int cSection = in.nextInt();
		System.out.println("Input the max number of students that can attend this course");
		int max_students = in.nextInt();
		
		//Create the new course
		Course c = new Course(cname, cID, max_students, 0,null, cInstructor, cSection,  cLocation);
		courses.add(c);
		//in.close();
	}
	//This method will delete a course
	public void delete_a_course(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);
		
		//Gather information on which course to delete
		System.out.println("You are now going to delete a course...\n");
		System.out.println("Input the course name");
		String cname = in.nextLine();
		System.out.println("Input the Course Section");
		int cSection = in.nextInt();
		System.out.println("deleting...\n\n");
		for(int i = 0; i<courses.size();i++) {
			if (courses.get(i).getCourse_name().equals(cname) && courses.get(i).getCourse_section()==cSection) {
				Course delete_this = courses.get(i);
				//Delete the course
				courses.remove(delete_this);
				System.out.println("You have succesfully deleted the following course: "+delete_this);
				break;
			}
		}
		//in.close();
	}
	//This method allows the Admin to edit information on a given course
	public void edit_a_course(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to the course Editor");
		
		//Gather information on which course to edit
		System.out.println("Input the mane of the course that you would like to edit");
		String cname = in.nextLine();
		System.out.println("Input the Section of the course");
		int cSection = in.nextInt();
		Course edit_this = new Course();
		for (int i=0; i<courses.size(); i++) {
			if (courses.get(i).getCourse_name().equals(cname) && courses.get(i).getCourse_section()==cSection) {
				edit_this = courses.get(i);
				break;
			}
		}
		//Ask the user which information he would like to edit on the specific course
		System.out.println("What do you want to edit?\n"+
		"Input 1 for the Course Name"+
				"Input 2 for the Course Section "+
		"Input 3 to edit the course Instructor");
		
		Scanner newStuff = new Scanner(System.in);
		int choice = in.nextInt();
		//Use the Switch case statements to perform the appropriate action
		switch (choice) {
		case 1:			
			System.out.println("Input the new Course name:");
			String change_name = newStuff.nextLine();
			edit_this.setCourse_name(change_name);
			break;
		case 2:		
			System.out.println("Input the new Course Section Number:");
			int change_section = newStuff.nextInt();
			edit_this.setCourse_section(change_section);
			break;
		case 3:			
			System.out.println("Input the new Course Instructor:");
			String change_instructor = newStuff.nextLine();
			edit_this.setCourse_instructor(change_instructor);
			break;
		}
		//newStuff.close();
		
		
	}
	public void view_full_courses(ArrayList<Course>courses) {
		System.out.println("The following are the courses that are full");
		for (int i =0; i<courses.size();i++) {
			if (courses.get(i).getCurrently_enrolled_students()==courses.get(i).getMax_students()) {
				Course m = courses.get(i);
				System.out.println("The following are all the Courses that are Full");
				System.out.printf("%-41s %-25s %-19s %-19s",  "Course Name:", "Course ID","Registered Students", "Max Students");
				System.out.printf("%-41s %-25s %-19d %-19d\n",  m.getCourse_name(), m.getCourse_ID(), m.getCurrently_enrolled_students(), m.getMax_students());
				
			}
		}
	}
	public void display_course_info(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);
		System.out.println("You have selected to view the info of a certain course");
		System.out.println("Input the course ID:\n");
		String course_ID = in.nextLine();
		for (int i=0; i<courses.size();i++) {
			if (courses.get(i).getCourse_ID().equals(course_ID)) {
				courses.get(i).display_course_info();
			}
		}
		//in.close();
	}
	public String students_in_a_course(Course c) {
		
		return c.getStudentsNames();
	}
	public void register_new_student(ArrayList<Student>students) {
		Scanner in = new Scanner(System.in);
		System.out.println("We will now register a student");
		System.out.println("Give me the students first name");
		String fname = in.nextLine();
		System.out.println("Give me the students Last name");
		String lname = in.nextLine();
		students.add(new Student(fname, lname));
		System.out.println("Successfully registered a student.");
		
	}

	//We will look for a faster way to sort the courses later in
	public ArrayList<Course> sort_on_registered_students(ArrayList<Course>courses) {
		Course c;
		for (int x=0;x<courses.size();x++) {
			for (int y=x; y<courses.size();y++){
				if (courses.get(x).getCurrently_enrolled_students()<courses.get(y).getCurrently_enrolled_students()) {
					c=courses.get(x);
					courses.set(x, courses.get(y));
					courses.set(y, c);
				}
			}
		}
		return courses;
	}
	public class InvalidChoiceException extends Exception{
		
	}
}
