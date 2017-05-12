package se.yrgo.student.formats;

import java.util.List;
import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;


public interface Formatter{
    public void   loadFromList(List<Student> listofStudentes);
    public void   loadCoursesForStudentId(List<Course> listofCourses);
    public String getDocument();
    public String getContentType();
}
