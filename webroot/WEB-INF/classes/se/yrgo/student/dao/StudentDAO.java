package se.yrgo.student.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.yrgo.student.domain.Student;
import se.yrgo.student.db.DBUtil;

public class StudentDAO{

  private DBUtil db;
  private static final String SELECT_ALL = "SELECT id, name FROM students";
  private static final String SELECT_STUDENT = "SELECT id, name FROM students WHERE id=";

  public StudentDAO(){
    db = DBUtil.getInstance();
    }

  public List<Student> getAllStudents(){
    List<Student> listOfStudents = new ArrayList<Student>();
    try{
        ResultSet rs = db.query(SELECT_ALL);
        while(rs.next()){
          listOfStudents.add(new Student(rs.getInt("id"), rs.getString("name")));
        }
    }catch(SQLException e){
        System.err.println("Database error: " + e.getMessage());
        e.printStackTrace();
      }
    return listOfStudents;
  }

  public Student getStudentById(int id){
    Student student = null;
    try{
        String query = SELECT_STUDENT + id;
        ResultSet rs = db.query(query);
        if(rs.next() ){
          student = new Student(rs.getInt("id"), rs.getString("name"));
        }else{
          System.out.println("Could not find student with id " + id);
        }
    }catch(SQLException e){
        System.err.println("Database error: " + e.getMessage());
        e.printStackTrace();
    }
    if(student != null) {
      return student;
    } else {
      throw new IllegalStateException("Stundent not found");
    }
  }
}
