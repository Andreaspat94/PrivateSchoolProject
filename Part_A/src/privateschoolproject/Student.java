/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {

    private String firstName, lastName, dateOfBirth;
    private double tuitionFees;
    private List<Assignment> assignmentsPerStudent = new ArrayList();
    private List<Course> personalCourseList = new ArrayList();

    public Student() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter first name :");
        setFirstName(s.nextLine());
        System.out.println("Please enter last name :");
        setLastName(s.nextLine());
        System.out.println("Please enter date of birth :(dd/MM/yyyy)");
        setDateOfBirth(s.nextLine());
        System.out.println("Please enter tuition fees :");
        setTuitionFees(s.nextDouble());
        System.out.println("Student added");
        System.out.println("");

    }

    public List<Course> getPersonalCourseList() {
        return personalCourseList;
    }

    public void setPersonalCourseList(List<Course> personalCourseList) {
        this.personalCourseList = personalCourseList;
    }

    public List<Assignment> getAssignmentsPerStudent() {
        return assignmentsPerStudent;
    }

    public void setAssignmentsPerStudent(List<Assignment> assignmentsPerStudent) {
        this.assignmentsPerStudent = assignmentsPerStudent;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getTuitionFees() {
        return tuitionFees;
    }

    public void setTuitionFees(double tuitionFees) {
        this.tuitionFees = tuitionFees;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
                + '}';
    }
    // ", tuitionFees=" + tuitionFees +

    public void addAssignment(Assignment a) {
        for (Assignment as : assignmentsPerStudent) {
            if (as.equals(a)) {
                System.out.println("Assignment is already in the list");
                break;
            }
        }
        assignmentsPerStudent.add(a);
    }

    public void showAssignmentList() {
        for (Assignment a : assignmentsPerStudent) {
            System.out.println(a.toString());
        }

    }

}
