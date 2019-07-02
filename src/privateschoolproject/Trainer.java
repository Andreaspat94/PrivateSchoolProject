/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolproject;

import java.util.Scanner;


public class Trainer {
    private String firstName;
    private String lastName;
    private String subject;
    
    
    public Trainer() {
         Scanner s = new Scanner(System.in);
        System.out.println("Please enter first name :");
        setFirstName(s.nextLine());
        System.out.println("Please enter last name :");            
        setLastName(s.nextLine());
        System.out.println("Please enter subject :");
        subject=s.nextLine();
        System.out.println("Trainer added");
        System.out.println("");
      
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
}
