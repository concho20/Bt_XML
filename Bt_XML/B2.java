package Bt_XML;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import java.io.*;
 
public class B2 {
    public static void main(String argv[]) {
 
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
 
            // tạo phần tử gốc có tên class
            Element rootElement = doc.createElement("class");
            // thêm thuộc tính totalStudents vào thẻ class
            doc.appendChild(rootElement);
            Attr totalStudentAttr = doc.createAttribute("totalStudents");
            totalStudentAttr.setValue("3");
            rootElement.setAttributeNode(totalStudentAttr);
 
            // tạo phần tử student1
            Element student1 = doc.createElement("student");
            rootElement.appendChild(student1);
            // tạo thuộc tính rollno cho student1
            Attr attr1 = doc.createAttribute("rollno");
            attr1.setValue("1");
            student1.setAttributeNode(attr1);
            // tạo thẻ firstname
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("Khoi"));
            student1.appendChild(firstname);
            // tạo thẻ lastname
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("Nguyen"));
            student1.appendChild(lastname);
            
            Element gpa = doc.createElement("gpa");
            gpa.appendChild(doc.createTextNode("4.0"));
            student1.appendChild(gpa);
 
            // tạo phần tử student2
            Element student2 = doc.createElement("student");
            rootElement.appendChild(student2);
            // tạo thuộc tính rollno cho student2
            Attr attr2 = doc.createAttribute("rollno");
            attr2.setValue("2");
            student2.setAttributeNode(attr2);
            // tạo thẻ firstname
            Element firstname2 = doc.createElement("firstname");
            firstname2.appendChild(doc.createTextNode("Nguyen"));
            student2.appendChild(firstname2);
            // tạo thẻ lastname
            Element lastname2 = doc.createElement("lastname");
            lastname2.appendChild(doc.createTextNode("Khoi"));
            student2.appendChild(lastname2);
            
            Element gpa2 = doc.createElement("gpa");
            gpa2.appendChild(doc.createTextNode("4.0"));
            student2.appendChild(gpa2);
 
            // ghi nội dung vào file XML
            TransformerFactory transformerFactory = 
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(
                    new File("D:\\StudyAtSchool\\Java\\Java2\\src\\students.xml"));
            transformer.transform(source, result);
 
            // ghi kết quả ra console để kiểm tra
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
