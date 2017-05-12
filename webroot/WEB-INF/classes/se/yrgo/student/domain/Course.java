package se.yrgo.student.domain;

public class Course{

    private String courseCode;
    private int courseId;
    public Course(int courseId, String courseCode){
        this.courseCode = courseCode;
        this.courseId = courseId;
    }

    public String getCourseCode(){
        return courseCode;
    }

    public int getCourseId(){
        return courseId;
    }

    @Override
    public String toString(){
        return new StringBuilder().append(courseCode)
            .append(" (")
            .append(courseId)
            .append(")")
            .toString();
    }
}
