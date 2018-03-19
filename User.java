package homework1;

/**
 * @author Laurence Bugasu Ininda
 */

import java.util.ArrayList;

public class User implements java.io.Serializable{

	private String username;
	private String password;
	private String firstname;
	private String lastname;
	

	public User() {
		
	}

	//This methos allows a user to view all courses available
	public void view_all_courses(ArrayList<Course>courses) {

		System.out.println("The following are all the Courses in the system");
		System.out.printf("%-41s %-25s %-19s %-19s",  "Course Name:", "Course ID","Registered Students", "Max Students");
		System.out.println(courses.size());
		for(int i=0;i<courses.size();i++) {
			Course m=courses.get(i);
			System.out.printf("%-41s %-25s %-19d %-19d\n",  m.getCourse_name(), m.getCourse_ID(), m.getCurrently_enrolled_students(), m.getMax_students());
		}
	}
	//This method allows a user to view all courses that are not full
	public void view_unfull_courses(ArrayList<Course>courses) {
		System.out.println("The following are the courses that not full");
		System.out.printf("%-41s %-25s %-19s\n",  "Course Name:", "Course ID","Section");
		System.out.println(courses.size());
		for(int i=0;i<courses.size();i++) {
			Course m=courses.get(i);
			if (m.getCurrently_enrolled_students()<m.getMax_students()) {
				System.out.printf("%-41s %-25s %-19d\n",  m.getCourse_name(), m.getCourse_ID(), m.getCourse_section());
			}
		}
	}
	
	//This method will be overridden in the Student class to fit the requirement
	public void view_registered_courses(Student s) {
		System.out.println("The following student " + " is registered in the following courses: \n" + s.get_courses());
	}
	
	//Getters and setters for the user class
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String toString(User s) {
		return s.getFirstname()+" "+s.getLastname();
	}
	
}
