package org.example;

import org.example.parsers.BusinessMeetingsParser;
import org.example.transformer.TransformBooksToHTML;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("dd-MM-yyyy:");
        String inputDate = scanner.nextLine();

        // Создаем объект SimpleDateFormat для разбора введенной даты
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(inputDate);
        var nodeLost = BusinessMeetingsParser.filterMeetingsByDate("D:\\angular\\lab2\\src\\main\\java\\resources\\BusinessMeetings.xml", date);
        TransformBooksToHTML.generateHTML(nodeLost, "D:\\angular\\lab2\\src\\main\\java\\resources\\BusinessMeeting.xsl", "out.html");
    }
}
