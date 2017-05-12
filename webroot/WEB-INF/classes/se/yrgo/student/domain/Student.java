package se.yrgo.student.domain;

public class Student{

    private String studentName;
    private int studentId;
    public Student(int id, String studentName){
        this.studentName = studentName;
        this.studentId   = studentId;
    }

    public String getStudentName(){
        return studentName;
    }

    public int getStudentId(){
        return studentId;
    }

    @Override
    public String toString(){
        return new StringBuilder().append(studentName)
            .append(" (")
            .append(studentId)
            .append(")")
            .toString();
    }
}
