package se.yrgo.student.formats;

import java.util.List;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;


import se.yrgo.student.domain.Student;
import se.yrgo.student.domain.Course;

class XMLFormatter implements Formatter{

    private static XMLFormatter instance;

    static{
        instance = new XMLFormatter();
        FormatterFactory.registerFormatter("xml", instance);
    }

    private String document;
    private XMLFormatter(){

    }

    public void loadFromList(List<Student> students){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("STUDENTS");
            doc.appendChild(rootElement);

            for(Student student : students ){
                Element studentIdElement = doc.createElement("STUDENT");
                studentIdElement.setAttribute("id", student.getStudentId()+"");
                Element studentNameElement = doc.createElement("NAME");
                studentNameElement.appendChild(doc.createTextNode(student.getStudentName()));
                studentIdElement.appendChild(studentNameElement);
                rootElement.appendChild(studentIdElement);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            transformer.transform(source, result);
            this.document = sw.toString();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
    public String getDocument(){
        return document;
    }
    public String getContentType(){
        return "application/xml";
    }


  public void loadCoursesForStudentId(List<Course> courses){
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("COURSES");
        doc.appendChild(rootElement);

        for(Course course : courses){
            Element courseElement = doc.createElement("COURSEID");
            courseElement.setAttribute("id", course.getCourseId()+"");
            Element codeElement = doc.createElement("CODE");
            codeElement.appendChild(doc.createTextNode(course.getCourseCode()));
            courseElement.appendChild(codeElement);
            rootElement.appendChild(courseElement);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        transformer.transform(source, result);
        this.document = sw.toString();
    } catch (ParserConfigurationException pce) {
        pce.printStackTrace();
    } catch (TransformerException tfe) {
        tfe.printStackTrace();
    }
  }
}
