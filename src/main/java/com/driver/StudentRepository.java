package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

	
	private ConcurrentHashMap<String,Student> studentDB;
	
	private ConcurrentHashMap<String,Teacher> teacherDB;

	public StudentRepository() {
		
		this.studentDB = new ConcurrentHashMap<>();
		this.teacherDB = new ConcurrentHashMap<>();
	}
	
	public void addStudent(Student s) {
		studentDB.put(s.getName(), s);
	}
	
	public void addTeacher(Teacher t) {
		teacherDB.put(t.getName(), t);
	}
	
	public int getStudentDBSize() {
		return studentDB.size();
	}
	
	public Student getStudentByName(String name) {
		return studentDB.get(name);
	}
	
	public Teacher getTeacherByName(String name) {
		return teacherDB.get(name);
	}
	
	public void addStudentToTeacher(String s, String t) {
		
		if(teacherDB.containsKey(t) && studentDB.containsKey(s)) {
			Teacher teacher = teacherDB.get(t);
			teacher.getStudents().add(studentDB.get(s));
			teacher.setNumberOfStudents(teacher.getStudents().size());
			
		}
	}
	
	public void deleteTeacherStudentByName(String t) {
		if(teacherDB.containsKey(t) ) {
			
			Teacher teacher = teacherDB.get(t);
			
			for(Student s: teacher.getStudents()) {
				deleteStudentByName(s.getName());
			}
			
			deleteTeacherByName(t);
			
		}
	}
	
	public void deleteAllTeacher() {
		for(String t: teacherDB.keySet()) {
			deleteTeacherStudentByName(t);
		}
	}
	
	public void deleteStudentByName(String s) {
		if(studentDB.containsKey(s)) {
			
			studentDB.remove(s);
		}
	}
	
	public void deleteTeacherByName(String t) {
		
		if(teacherDB.containsKey(t)) {
			
			teacherDB.remove(t);
		}
	}
	
	public List<String> getStuByTea(String name){
		ArrayList<String> list = new ArrayList<>();
		for(Student st: teacherDB.get(name).getStudents()) {
			list.add(st.getName());
		}
		return list;
	}
	
	public List<String> getAllStu(){
		ArrayList<String> list = new ArrayList<>();
		for(String st: studentDB.keySet()) {
			list.add(st);
		}
		
		return list;
	}
	
}
