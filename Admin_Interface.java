package homework1;

/**
 * @author Laurence Bugasu Ininda
 */


import java.io.IOException;
import java.util.ArrayList;

import homework1.Admin.InvalidChoiceException;

public interface Admin_Interface {
	


	public void do_stuff(ArrayList<Course>courses, ArrayList<Student>students) throws  IOException;
	
	public void create_a_new_course(ArrayList<Course>courses);
	
	public void delete_a_course(ArrayList<Course>courses);
	
	public void edit_a_course(ArrayList<Course>courses);
	
	public void view_full_courses(ArrayList<Course>courses);
	
	public void display_course_info(ArrayList<Course>courses);
	
	public String students_in_a_course(Course c);

	public void register_new_student(ArrayList<Student>students);
	
	public ArrayList<Course> sort_on_registered_students(ArrayList<Course>courses);
	
	
}
