package se.yrgo.student.storage;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import se.yrgo.student.domain.Course;
import se.yrgo.student.domain.Student;
import se.yrgo.student.db.DBUtil;

public class StudentStorageDB implements StudentStorage{
    private DBUtil db;
    private static final String SELECT_ALL_STUDENTS = "SELECT id, name FROM students";
    private static final String SELECT_COURSE = "SELECT registrations.course_id AS id, course_code AS code FROM registrations JOIN students ON students.id = registrations.student_id JOIN courses ON courses.id = registrations.course_id WHERE registrations.student_id =";

    public StudentStorageDB(){
        db = DBUtil.getInstance();
    }

    // kan skapa problem med Srvleten. Ta bort när projektet är klart.
    public Student getStudentById(int id) throws StorageException{
        try{
            String query = SELECT_ALL_STUDENTS + id;
            ResultSet rs = db.query(query);
            if(rs.next() ){
                return new Student(rs.getInt("id"), rs.getString("name"));
            }else{
                throw new StorageException("Could not find student with id " +
                                           id);
            }
        }catch(SQLException sqle){
            throw new StorageException(sqle.getMessage());
        }
    }


    public List<Student> getAllStudents() throws StorageException{
        try{
            List<Student> students = new ArrayList<Student>();
            ResultSet rs = db.query(SELECT_ALL_STUDENTS);
            while(rs.next()){
                students.add(new Student(rs.getInt("id"), rs.getString("name")));
            }
            if(students.size() != 0){
                return students;
            }else{
                throw new StorageException("No students could be fetched");
            }
        }catch(SQLException sqle){
            throw new StorageException(sqle.getMessage());
        }
    }

    // Lägger till Courses iom Interfacet
    public List<Course> getCoursesFor(int student_id) throws StorageException{
        try{
            List<Course> courses = new ArrayList<Course>();
            System.out.println(SELECT_COURSE + student_id + ";");
            ResultSet rs = db.query(SELECT_COURSE + student_id + ";");
            System.out.println(rs);
            while(rs.next()){
                courses.add(new Course(rs.getInt("id"), rs.getString("code")));
            }
            if(courses.size() != 0){
                return courses;
            }else{
                throw new StorageException("No students could be fetched");
            }
        }catch(SQLException sqle){
            throw new StorageException(sqle.getMessage());
        }
    }
}
