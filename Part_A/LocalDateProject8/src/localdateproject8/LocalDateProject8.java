/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localdateproject8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author George
 */
public class LocalDateProject8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //etsi ftiaxno hmeromhnies;
       LocalDate today = LocalDate.now();
        System.out.println(today);
        
        LocalDate date = LocalDate.of(2019,6,13);
        System.out.println(date);
      // apo edw kai pera gia to project  
      String hmnia  = "24/06/2019";
      
      //Get date from string (parse)
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate date2FromString = LocalDate.parse(hmnia, formatter);
        System.out.println("dateFromString="+date2FromString);
        
        //gia na to ektupwseis se string (format)
//        String newString = data2FromString.format(formatter);
//        System.out.println("newString =="+ newString);
        
        //
       //LocalDate date = LocalDate.of(2019,6,13);
        //System.out.println(date);
        LocalDate firstDayOfWeek = date;
        while(firstDayOfWeek.getDayOfWeek() !=DayOfWeek.MONDAY) {
            firstDayOfWeek = firstDayOfWeek.minusDays(1);
        }
        System.out.println("first day of week is " + firstDayOfWeek);
       
    }
    

    
    
}
