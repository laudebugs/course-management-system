package homework1;

/**
 * @author Laurence Bugasu Ininda
 */

import java.util.ArrayList;

public interface Student_Interface {

	public void do_stuff(ArrayList<Course>courses);
	
	public String get_courses();
	
	public void register_to_course(ArrayList<Course>courses);
	
	public void add_to_Student_courses(Course c);
	
	public void remove_from_student_courses(Course c);

	public void withdraw_from_course(ArrayList<Course>courses);
}
