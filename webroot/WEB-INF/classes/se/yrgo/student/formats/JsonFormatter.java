package se.yrgo.student.formats;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;


class JsonFormatter implements Formatter{
    private static JsonFormatter instance;
    static{
        instance = new JsonFormatter();
        FormatterFactory.registerFormatter("json", instance);
    }

    private String document;

    private JsonFormatter(){

    }
    /* Laddar upp listen från Students till JsonFormatter
      och omvandlar till Json objekt, skriver ut objekt till
      document.

    */

    public void loadFromList(List<Student> list){
        StringBuilder page = new StringBuilder();
        Map<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        StringWriter writer = new StringWriter();
        JsonWriterFactory jwf = Json.createWriterFactory(config);
        JsonWriter jWriter = jwf.createWriter(writer);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for( Student student : list ){
            jab.add(Json.createObjectBuilder()
                    .add("studentName", student.getStudentName())
                    .add("studentID", student.getStudentId()));
        }
        job.add("students", jab.build());
        jWriter.writeObject(job.build());
        jWriter.close();
        page.append(writer.toString());
        this.document = page.toString();
    }

    public void loadCoursesForStudentId(List<Course> list){
        StringBuilder page = new StringBuilder();
        Map<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        StringWriter writer = new StringWriter();
        JsonWriterFactory jwf = Json.createWriterFactory(config);
        JsonWriter jWriter = jwf.createWriter(writer);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Course course : list){
            jab.add(Json.createObjectBuilder()
                    .add("courseID", course.getCourseId())
                    .add("CourseCode", course.getCourseCode()));
        }
        job.add("courses", jab.build());
        jWriter.writeObject(job.build());
        jWriter.close();
        page.append(writer.toString());
        this.document = page.toString();
    }
    public String getDocument(){
        return document;
    }
    public String getContentType(){
        return "application/json";
    }
}
