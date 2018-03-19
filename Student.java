package homework1;

/**
 * @author Laurence Bugasu Ininda
 */

import java.util.*;

public class Student extends User implements Student_Interface{

	private ArrayList<Course>student_courses = new ArrayList<>();

	//The Two student constructors
	public Student() {
		
	}
	public Student(String firstname, String lastname) {
		this.setUsername("Student");
		this.setPassword("Student001");
		this.setFirstname(firstname);
		this.setLastname(lastname);
	}
	
	//This is the method that will enab le the student to perform all of their actions
	public void do_stuff(ArrayList<Course>courses){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to your course management system.");
		System.out.println("The following are the available actions to perform:\n"+
		"Input 1 to View all courses that are available\n"+
				"Input 2 to view all courses that are Not full\n"+
		"Input 3 to Register on a course\n"+
				"Input 4 to Withdraw from a Course\n"+
		"Input 5 to view all courses that you are registered in\n"+
				"Input 6 to exit the System");
		
		//The boolean statement allows the Student to log off  if they wish
		boolean stop_sign = true;
		while (stop_sign) {
			System.out.println("Select an action");
			int choice = in.nextInt();
			switch (choice) {
			case 1:this.view_all_courses(courses);
				break;
			case 2:this.view_unfull_courses(courses);
				break;
			case 3:this.register_to_course(courses);
				break;
			case 4:this.withdraw_from_course(courses);
				break;
			case 5:this.view_registered_courses(this);
				break;
			case 6:
				stop_sign=false;
				System.out.println("Exiting System...");
			}
		
		//in.close();
		}
	}
	
	//Allows the Student to see which courses they are registered for
	@Override
	public void view_registered_courses(Student s) {
		
		
		System.out.println("The following student " +s.getFirstname()+" "+s.getLastname()+ " is registered in the following courses: \n" + s.get_courses());
	}
	
	//Returns the courses that the student is registered for
	public String get_courses() {
		String courses = "";
		
		for(int i = 0; i<this.student_courses.size();i++) {
			courses+=this.student_courses.get(i).getCourse_name()+"\n";
		}
		return courses;
	}
	
	
	//Allows a student to register in a course
	public void register_to_course(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);

		//Get information on a student and the course thet they want to register to
		System.out.println("Enter your first name");
		String fname = in.nextLine();
		System.out.println("Enter your last name");
		String lname = in.nextLine();
		System.out.println("Enter the name of the course that you would like to register for:");
		String course_name = in.nextLine();
		System.out.println("Enter the course section:");
		int section = in.nextInt();
		
		//Find the course
		for(int i=0; i<courses.size();i++) {
			if (courses.get(i).getCourse_name().equals(course_name) && courses.get(i).getCourse_section()==section) {
				//Check if student is already registered for the course
				boolean exists=false;
				for (int t=0; t<this.student_courses.size();t++) {
					if(this.student_courses.get(t).getCourse_name().equals(course_name)&&this.student_courses.get(t).getCourse_section()==section) {
						exists=true;
					}
				}
				//Check if course is full
				if (courses.get(i).getMax_students()==courses.get(i).getCurrently_enrolled_students()) {
					System.out.println("Sorry, you are trying to register for a course that is already full... Please try Later...");
				}
				else if(exists) {
					System.out.println("You are already registered for this course.");
				}
				else {
					//Add the number of students currently enrolled in the course by 1
					courses.get(i).setCurrently_enrolled_students(courses.get(i).getCurrently_enrolled_students()+1);
					courses.get(i).addStudent(this);
					//Add the Course to the course arrayList of the student
					Course c = courses.get(i);
					Course to_Add = new Course(c.getCourse_name(), c.getCourse_ID(), c.getMax_students(), c.getCurrently_enrolled_students(),c.getStudents(), c.getCourse_instructor(), c.getCourse_section(), c.getCourse_location()); 
					this.add_to_Student_courses(to_Add);
					System.out.println("You have been added to the course: "+ courses.get(i));
				}
				break;
			}
		}
	}
	
	//Allows a student to view all courses that are available
	@Override
	public void view_all_courses(ArrayList<Course>courses) {
		System.out.println("The following are all the Courses in the system");
		System.out.printf("%-41s %-25s %-19s",  "Course Name:", "Course ID","Section No");
		System.out.println(courses.size());
		for(int i=0;i<courses.size();i++) {
			Course m=courses.get(i);
			System.out.printf("%-41s %-25s %-19d\n",  m.getCourse_name(), m.getCourse_ID(), m.getCourse_section());
		}
	}
	//Adds a course to the student course arrayList
	public void add_to_Student_courses(Course c) {
		this.student_courses.add(c);
	}
	//Removes a course from The student Course arraylist
	public void remove_from_student_courses(Course c) {
		this.student_courses.remove(c);
	}
	
	//Withdraws a student from a course
	public void withdraw_from_course(ArrayList<Course>courses) {
		Scanner in = new Scanner(System.in);
		System.out.println("Input your first name");
		String fname = in.nextLine();
		System.out.println("Input your last name");
		String lname = in.nextLine();
		System.out.println("Input the course name that you would like to withdraw from:");
		String course_name = in.nextLine();
		System.out.println("Input the section of the course that you would like to withdraw from" );
		int section = in.nextInt();
		
		
		//Verify the students name
		if(this.getFirstname().equals(fname)&& this.getLastname().equals(lname)) {
			//Loop through the students courses
			for(int i =0;i<courses.size();i++) {
				//Loop through the courses to point to the specific course
				if (courses.get(i).getCourse_name().equals(course_name)&& courses.get(i).getCourse_section()==section){

					System.out.println("Size of student arrayList is:"+courses.get(i).getCurrently_enrolled_students());
					//	Remove this student from the Course
					courses.get(i).getStudents().remove(this);
					//Reduce number of currently enrolled students by 1
					courses.get(i).setCurrently_enrolled_students(courses.get(i).getCurrently_enrolled_students()-1);
					//Remove course from student Courses
					for(int k=0; k<this.student_courses.size();k++) {
						if (this.student_courses.get(k).getCourse_name().equals(course_name)&& this.student_courses.get(k).getCourse_section()==section) {
							this.student_courses.remove(k);
							System.out.println("You have been withdrawn from the course");
						}
					}

				}
			}
		}
		else {
			System.out.println("You are trying to withdraw another student from a course");
			System.out.println("Or you typed your name incorrectly");
			System.out.println("Please try again later...");
		}
	}
}
