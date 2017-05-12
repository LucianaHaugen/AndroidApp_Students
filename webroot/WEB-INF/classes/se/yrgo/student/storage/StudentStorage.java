package se.yrgo.student.storage;

import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;
import java.util.List;

public interface StudentStorage{
    public List<Student> getAllStudents() throws StorageException;
    public Student getStudentById(int id) throws StorageException;
    public List<Course> getCoursesFor(int id) throws StorageException;
}
