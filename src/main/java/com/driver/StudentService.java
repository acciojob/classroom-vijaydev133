package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository sr;
	
	public void addStudent(Student s) {
		sr.addStudent(s);;
	}
	
	public void addTeacher(Teacher t) {
		t.setNumberOfStudents(sr.getStudentDBSize()+1);
		sr.addTeacher(t);;
	}
	
	public Student getStudentByName(String name) {
		return sr.getStudentByName(name);
	}
	
	public Teacher getTeacherByName(String name) {
		return sr.getTeacherByName(name);
	}
	
	
	public void addStudentToTeacher(String s, String t) {
		
		sr.addStudentToTeacher(s, t);
	}
	
	public List<String> getStuByTea(String name){
		return sr.getStuByTea(name);
	}
	
	public List<String> getAllStu(){
		return sr.getAllStu();
	}
	
	public void deleteTeacherStudentByName(String t) {
		sr.deleteTeacherStudentByName(t);
	}
	
	public void deleteAllTeacher() {
		sr.deleteAllTeacher();
	}
	
}
