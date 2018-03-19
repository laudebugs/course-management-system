package homework1;

/**
 * @author Laurence Bugasu Ininda
 */

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Course implements java.io.Serializable, Comparable<Course> {

	private String course_name;
	private String course_ID;
	private int max_students;
	private int currently_enrolled_students;
	private ArrayList<Student>students=new ArrayList<Student>();
	private String course_instructor;
	private int course_section;
	private String course_location;
	
	//Two overloaded constructors
	Course(String course_name, String course_ID, int max_students, int current,ArrayList<Student>students, String course_instructor, int course_section, String course_location) {
		this.course_name=course_name;
		this.course_ID= course_ID;
		this.max_students= max_students;
		this.currently_enrolled_students=current;;
		this.students = students;
		this.course_instructor=course_instructor;
		this.course_section = course_section;
		this.course_location= course_location;
	}
	
	public Course() {
	}
	
	//Getters and setters for the Course class
	public String toString(Course c) {
		
		return c.getCourse_name()+" : "+c.getCourse_ID()+" : "+c.getCourse_section();
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_ID() {
		return course_ID;
	}
	public void setCourse_ID(String course_ID) {
		this.course_ID = course_ID;
	}
	public int getMax_students() {
		return max_students;
	}
	public void setMax_students(int max_students) {
		this.max_students = max_students;
	}
	public int getCurrently_enrolled_students() {
		return currently_enrolled_students;
	}
	public void setCurrently_enrolled_students(int currently_enrolled_students) {
		this.currently_enrolled_students = currently_enrolled_students;
	}
	public String getStudentsNames() {
		String s="";
		for (int i=0; i<this.students.size();i++) {
			s+="/n"+this.students.get(i);
		}
		return s;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void addStudent(Student s) {
		
		this.students.add(s);
	}
	public String getCourse_instructor() {
		return course_instructor;
	}
	public void setCourse_instructor(String course_instructor) {
		this.course_instructor = course_instructor;
	}
	public int getCourse_section() {
		return course_section;
	}
	public void setCourse_section(int course_section) {
		this.course_section = course_section;
	}
	public String getCourse_location() {
		return course_location;
	}
	public void setCourse_location(String course_location) {
		this.course_location = course_location;
	}
	
	//Gives information on this course
	public void display_course_info() {
		System.out.println("The following is the information on the course"+ this.getCourse_ID());
		System.out.println("Course Name: " +this.getCourse_name()+
				"\nCourse ID:"+ this.getCourse_ID()+
				"\nMaximum students in Course: "+ this.getMax_students()+
				"\nCurrently Enrolled Students: "+ this.getCurrently_enrolled_students()+
				"\nCourse Instructor: "+ this.getCourse_instructor()+
				"\nCourse Section: "+this.getCourse_section()+
				"\nCourse Location: "+this.getCourse_location()
				);
	}
	public String toString() {
		return this.getCourse_name();
	}
	//This method is used to sort the courses based on the number of students currently enrolled
	public int compareTo(Course c) {
		if (c.getCurrently_enrolled_students()>this.getCurrently_enrolled_students()) {
			return 1;
		}
		else if (c.getCurrently_enrolled_students()<this.getCurrently_enrolled_students()) {
			return -1;
		}
		else return 0;
	}
}
