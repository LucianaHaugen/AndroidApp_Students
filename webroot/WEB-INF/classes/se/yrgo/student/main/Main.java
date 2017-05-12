package se.yrgo.student.main;

import se.yrgo.student.storage.StudentStorageFactory;
import se.yrgo.student.storage.StudentStorage;
import se.yrgo.student.storage.StorageException;
import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;
import se.yrgo.student.formats.Formatter;
import se.yrgo.student.formats.FormatterFactory;
import se.yrgo.student.formats.FormatNotSupportedException;
import se.yrgo.student.db.DBUtil;

import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Main {
  public static void main(String[] args){
    if(args.length != 1){
    System.err.println("No format argument found.");
    System.exit(1);
    }

    try{
      String format = args[0];
      System.out.println("_______________________________");
      System.out.println("[M] Here is what we get: " + format);
      Formatter formatter = FormatterFactory.getFormatter(format);
      System.out.println("[M] Here is formatter on Main: " + formatter);
      StudentStorage storage = StudentStorageFactory.getStorage();
      System.out.println("_______________________________");
      List<Course> courses = storage.getCoursesFor(3);
      List<Student> students = storage.getAllStudents();

      formatter.loadFromList(students);
      String res1 = formatter.getDocument();
      System.out.println("[M] All students as " + formatter.getContentType() + ":");
      System.out.println(res1);

      formatter.loadCoursesForStudentId(courses);
      String res2 = formatter.getDocument();
      System.out.println("Students courses for StudentID = 1 as " + formatter.getContentType());
      System.out.println(res2);

      }catch(FormatNotSupportedException fnse){
        System.err.println(fnse.getMessage());
      }catch(StorageException se){
        System.err.println("Error accessing the storage: "
                               + se.getMessage());
      }
  }
}
