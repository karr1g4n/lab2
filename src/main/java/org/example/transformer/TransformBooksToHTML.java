package org.example.transformer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.List;

public class TransformBooksToHTML {

    public static void generateHTML(List<Node> nodes, String xsltFilePath, String outputFilePath) {
        try {
            // Створюємо новий Document
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Створюємо корінь HTML-дерева
            Element html = doc.createElement("html");
            doc.appendChild(html);

            // Створюємо тіло HTML-дерева
            Element body = doc.createElement("body");
            html.appendChild(body);

            // Додаємо список зустрічей до тіла HTML-дерева
            for (Node node : nodes) {
                // Клонуємо вузол, оскільки node вже належить до попереднього Document
                Node clonedNode = doc.importNode(node, true);
                body.appendChild(clonedNode);
            }

            // Записуємо HTML-дерево у тимчасовий XML-файл
            File tempXmlFile = File.createTempFile("temp", ".xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult tempXmlResult = new StreamResult(tempXmlFile);
            transformer.transform(source, tempXmlResult);

            // Використовуємо XSLT-шаблон для трансформації тимчасового XML-файлу в HTML
            Transformer xsltTransformer = transformerFactory.newTransformer(new StreamSource(xsltFilePath));
            StreamSource tempXmlSource = new StreamSource(tempXmlFile);
            StreamResult htmlResult = new StreamResult(new File(outputFilePath));
            xsltTransformer.transform(tempXmlSource, htmlResult);

            // Видаляємо тимчасовий XML-файл
            tempXmlFile.delete();

            // Виводимо повідомлення про успішне завершення
            System.out.println("HTML-файл згенеровано успішно: " + outputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
