/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolproject;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author George
 */
public class Assignment {

    private String title;
    private String description;
    private LocalDate subDateTime;
    private double oralMark, totalMark;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Assignment() throws ParseException {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter title :");
        setTitle(s.nextLine());
        System.out.println("Please enter description :");
        setDescription(s.nextLine());
        System.out.println("Please enter submit date time : (dd/MM/yyyy)");
        setSubDateTime(s.nextLine());
        System.out.println("Please enter oral mark in : (integer)");
        setOralMark(s.nextDouble());
        System.out.println("Please enter total mark :(integer)");
        setTotalMark(s.nextDouble());
        System.out.println("Assignment added");
        System.out.println("");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(String subDateTime) throws ParseException {
        LocalDate dateFromString = LocalDate.parse(subDateTime, formatter);

        this.subDateTime = dateFromString;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Assignment{" + "title=" + title + ", description=" + description + ", subDateTime=" + subDateTime.format(formatter) + ", oralMark=" + oralMark + ", totalMark=" + totalMark + '}';
    }

}
