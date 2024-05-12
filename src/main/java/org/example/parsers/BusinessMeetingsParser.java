package org.example.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessMeetingsParser {

    public static List<Node> filterMeetingsByDate(String filename, Date userDate) {
        List<Node> matchingMeetings = new ArrayList<>();
        try {
            // Отримання DOM документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filename));

            NodeList meetingNodes = doc.getElementsByTagName("Meeting");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < meetingNodes.getLength(); i++) {
                Node meetingNode = meetingNodes.item(i);
                if (meetingNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element meetingElement = (Element) meetingNode;
                    Element dateTimeElement = (Element) meetingElement.getElementsByTagName("DateTime").item(0);
                    String dateString = dateTimeElement.getTextContent().substring(0, 10); // Отримання лише дати з часу
                    Date meetingDate = dateFormat.parse(dateString);
                    if (meetingDate.equals(userDate)) {
                        // Додаємо відповідні зустрічі до списку
                        matchingMeetings.add(meetingNode);
                        System.out.println("Meeting found:");
                        System.out.println("Date: " + dateString);
                        System.out.println("Attendee: " + meetingElement.getElementsByTagName("Attendee").item(0).getTextContent());
                        System.out.println("Location: " + meetingElement.getElementsByTagName("Location").item(0).getTextContent());
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchingMeetings;
    }
}
