package Bt_XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Scanner;

public class B1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();
        scanner.close();

        createAndListDirectoryTreeXML(directoryPath);
    }

    public static void createAndListDirectoryTreeXML(String directoryPath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("directory");
            doc.appendChild(rootElement);

            File directory = new File(directoryPath);
            if (directory.exists() && directory.isDirectory()) {
                listDirectory(directory, rootElement, doc);
            } else {
                System.out.println("Đường dẫn thư mục không hợp lệ hoặc không tồn tại.");
                return;
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(File directory, Element parentElement, Document doc) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName().replaceAll("[^a-zA-Z0-9.-]", "_"); // Chuyển đổi tên file sang dạng hợp lệ cho XML
                if (file.isDirectory()) {
                    Element directoryElement = doc.createElement(fileName);
                    parentElement.appendChild(directoryElement);
                    listDirectory(file, directoryElement, doc);
                } else {
                    Element fileElement = doc.createElement("file");
                    fileElement.setTextContent(fileName);
                    parentElement.appendChild(fileElement);
                }
            }
        }
    }
}
