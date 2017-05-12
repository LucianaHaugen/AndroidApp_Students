package se.yrgo.student.servlets;

import se.yrgo.student.storage.StudentStorageFactory;
import se.yrgo.student.storage.StudentStorage;
import se.yrgo.student.storage.StorageException;
import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;
import se.yrgo.student.formats.Formatter;
import se.yrgo.student.formats.FormatterFactory;
import se.yrgo.student.formats.FormatNotSupportedException;

import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class studentServlet extends HttpServlet{

  public void init() throws ServletException{
    // Do initiation here...
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
   Formatter formatter;
   StringBuilder page = new StringBuilder(253);


   try{
       String format = request.getParameter("format");
       String studentId = request.getParameter("id");

       if(format!=null && studentId!=null){
         // solving the problem when Winstone sends data with encoding
         response.setContentType("application/" + format);
         response.setCharacterEncoding("UTF-8");

         formatter = FormatterFactory.getFormatter(format);
         StudentStorage storage = StudentStorageFactory.getStorage();
         if(studentId.equals("all")){
            List<Student> students = storage.getAllStudents();
            formatter.loadFromList(students);
         }
          else{
            try{
               int studId = Integer.parseInt(studentId);
               List<Course> courses = storage.getCoursesFor(studId);
               formatter.loadCoursesForStudentId(courses);
            }
            catch(NumberFormatException nfe){
               System.err.println(nfe.getMessage());
            }
         }

         page.append(formatter.getDocument());
       }
       else if(format==null){
         response.setContentType("text/html");
         page.append("<html><body><h1>Missing parameter: format</h1></body></html>");
       }
       else{
         response.setContentType("text/html");
         page.append("<html><body><h1>Unknown format: "+format+"</h1></body></html>");
       }
   }
   catch(FormatNotSupportedException fnse){
            System.err.println(fnse.getMessage());
   }
   catch(StorageException se){
            System.err.println("Error accessing the storage: "
                               + se.getMessage());
   }
   PrintWriter out = response.getWriter();
   out.println(page);
   out.flush();
  }
}
