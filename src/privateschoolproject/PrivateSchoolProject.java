/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolproject;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import static privateschoolproject.Course.addStudent;

import static privateschoolproject.Course.addTrainer;
import static privateschoolproject.Course.addTrainerProcess;
import static privateschoolproject.Course.validation;
import static privateschoolproject.PrivateSchoolProject.scanner;

public class PrivateSchoolProject {

    public static Scanner scanner = new Scanner(System.in);
    public static List<Student> listOfStudents = new ArrayList();
    public static List<Course> listOfCourses = new ArrayList();
    public static List<Trainer> listOfTrainers = new ArrayList();
    public static List<Assignment> listOfAssignments = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        boolean valid = true;
        int input;
        start();
        System.out.println("/* Before attempting anything, please enter data\nwith the following sequence:\n1.Course, 2.Assignment to course, 3.Student 4.Add student to course\n"
                + "Then add assignmets to the courses you have added.*/");
        while (valid) {
            while (!scanner.hasNextInt()) {
                System.out.println("No integer input.\nPlease enter a number bettween 0 to 6");
                scanner.next();
            }
            input = scanner.nextInt();

            switch (input) {
                case 0:
                    start();
                    break;
                case 1:
                    printMenu1();
                    System.out.println("---------------------\nAttention!!\nAdd courses before proceed to option 5.");
                    break;
                case 5:
                    showCoursesList();
                    System.out.println("---------------------");
                    System.out.println("Do you want to select a course? (y/n)");
                    String temp = scanner.next();
                    if (temp.equals("y")) {

                        Course course = courseSelection();

                        printMenu4();

                        boolean valid2 = true;
                        int choice;
                        while (valid2) {
                            while (!scanner.hasNextInt()) {
                                System.out.println("No integer input.\nPlease enter a number between 0 to 7");
                                scanner.next();
                            }
                            choice = scanner.nextInt();

                            switch (choice) {
                                case 0:
                                    printMenu4();
                                    break;
                                case 1:
                                    System.out.println("Attention! You should firstly assign students in school platform"
                                            + "and then add them to the course.\n(Press 0 for Menu and then press 2 for Students)");
                                    System.out.println("");
                                    List<Student> list = course.getStudentList();
                                    if (list.isEmpty()) {
                                        System.out.println("There is no student in this course\nPress 0 for Menu");
                                    } else {
                                        int i = 1;
                                        for (Student st : list) {
                                            System.out.println(i + ". " + list.get(i - 1).toString());
                                            i++;
                                        }
                                    }
                                    System.out.println("");

                                    break;
                                case 2:
                                    List<Trainer> list2 = course.getTrainerList();
                                    if (list2.size() == 0) {
                                        System.out.println("There is no trainer in this course\nPress 0 for Menu");
                                    } else {
                                        int k = 1;
                                        for (Trainer tr : list2) {
                                            System.out.println(k + ". " + list2.get(k - 1).toString());
                                            k++;
                                        }
                                    }
                                    System.out.println("");
                                    break;
                                case 3:
                                    List<Assignment> list3 = course.getAssignmentList();
                                    if (list3.size() == 0) {
                                        System.out.println("There is no assignment in this course\nPress 0 for Menu");
                                    } else {
                                        int x = 1;
                                        for (Assignment as : list3) {
                                            System.out.println(x + ". " + list3.get(x - 1).toString());
                                            x++;
                                        }
                                    }
                                    System.out.println("");

                                    break;
                                case 4:
                                    //put a student in the course
                                    try {

                                        Student stud = course.addStudentProcess(listOfStudents, course.getStudentList());
                                        //add the course to personalCourseList of that student.
                                        if (stud != null) {
                                            stud.getPersonalCourseList().add(course);
                                        }
                                        // assign to the student all the assignments of this course
                                        for (Assignment as : course.getAssignmentList()) {
                                         
                                            stud.getAssignmentsPerStudent().add(as);
                                        }
                                    } catch (NullPointerException e) {
                                        e.printStackTrace();
                                    }

                                    break;
                                case 5:
                                    System.out.println("Attention! You should firstly add trainers in school platform"
                                            + "before attempt to add a trainer in the course");
                                    addTrainerProcess(listOfTrainers, course.getTrainerList());
                                    break;
                                case 6:
                                    //add an assignment to this course
                                    try {
                                        Assignment a = new Assignment();

                                        course.getAssignmentList().add(a);
                                        listOfAssignments.add(a);

//                                    course.addAssignment(new Assignment,.getAssignmentList());
                                        System.out.println("");
                                        System.out.println("Press 0 for Menu");
                                    } catch (DateTimeParseException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 7:
                                    start();
                                    valid2 = false;
                            }

                        }
                        break;
                    } else if (temp.equals("n")) {
                        scanner.next();
                        start();
                    }
                    break;
                case 6:
                    Course newCourse = new Course();
                    if (addCourse(newCourse)) {
                        listOfCourses.add(newCourse);
                    }
                    printMenu1();
                    break;
                case 2:
                    printMenu2();
                    System.out.println("--------------------\nAttention!!\nPlease add students before proceed to other options.");
                    break;
                case 7:
                    int i = 1;
                    for (Student s : listOfStudents) {
                        System.out.println(i + "." + s.toString());
                        i++;
                    }

                    break;

                case 8:
                    Student newStudent = new Student();
                    if (addStudent(newStudent, listOfStudents) == 1) {
                        listOfStudents.add(newStudent);
                    }
                    printMenu2();
                    break;
                case 9:
                    //print all assignments per student
                    int x = 1;
                    for (Student s : listOfStudents) {

                        System.out.println(x + ". " + s.toString());
                        s.showAssignmentList();
                        System.out.println("");
                        x++;
                    }
                    break;
                case 10:
                    //Print list of students that belong to more than one course
                    int l = 1;

                    for (Student s : listOfStudents) {
                        if (s.getPersonalCourseList().size() > 1) {
                            System.out.println(l + ". " + s.toString());
                            l++;
                            System.out.println("With courses :");
                            for (Course c : s.getPersonalCourseList()) {
                                System.out.println(c.toString());

                            }
                        }
                    }
                    System.out.println("--------------------");
                    System.out.println("Print 0 for Menu");
                    break;
                case 11:
                    submitDate();
                    break;

                case 3:
                    printMenu3();
                    System.out.println("");
                    System.out.println("Attention! You should firstly add trainers in school platform"
                            + "before process anything else");
                    break;
                case 12:
                    if (listOfStudents.isEmpty()) {
                        System.out.println("There are no assigned trainers in the school");
                    } else {
                        for (Trainer t : listOfTrainers) {
                            System.out.println(t.toString());
                        }
                    }
                    System.out.println("");
                    System.out.println(" 0. Back to Previous Menu");
                    break;
                case 13:
                    Trainer newTrainer = new Trainer();
                    addTrainer(newTrainer, listOfTrainers);
                    System.out.println("");
                    System.out.println(" 0. Back to Previous Menu");
                    break;
                case 4:
                    valid = false;
            }

        }

    }

    public static void printMenu1() {
        System.out.println("Courses :");
        System.out.println(" 5. Full list of courses \n"
                + " 6. Add a new course \n"
                + " 0. Menu");
    }

    public static void printMenu2() {
        System.out.println("Students :");
        System.out.println(" 7. Full list of students \n"
                + " 8. Add a new student \n"
                + " 9. Print list of all assignments per student \n"
                + " 10. Print list of students that belong to more than one course \n"
                + " 11. Enter a date(dd/MM/yyyy) to print the students who submit their assignments in that week.\n"
                + " 0. Back to Previous Menu");

    }

    public static void printMenu3() {
        System.out.println("Trainers :");
        System.out.println(" 12. Full list of trainers \n"
                + " 13. Add a new trainer \n"
                + " 0. Back to Previous Menu");
    }

    public static void printMenu4() {
        System.out.println("Course Modification Menu :");
        System.out.println(" 1. Print students of this course \n"
                + " 2. Print trainers of this course \n"
                + " 3. Print assignments of this course \n"
                + " 4. Add a student to this course\n"
                + " 5. Add a trainer to this course \n"
                + " 6. Add an assignment to this course \n"
                + " 7. Go back to initial menu\n"
                + " 0. Menu");
    }

    public static void start() {

        System.out.println("Welcome to our Private School application. \n\n Press :\n"
                + "0. Menu.\n"
                + "1. Courses.\n"
                + "2. Students.\n"
                + "3. Trainers.\n"
                + "4. Exit");
    }
    //validation and addition

    public static boolean addCourse(Course course) {

        for (Course c : listOfCourses) {
            if (c.getTitle().equals(course.getTitle())) {
                if (c.getStream().equals(course.getStream())) {
                    if (c.getType().equals(course.getType())) {
                        System.out.println("Course " + course.getTitle() + " is already in the list ");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Choose and store a course/student/assignment from the
     * listOfCourses/listOfStudents/listOfAssignments in a variable.
     */
    public static Course courseSelection() {

        System.out.println("");
        System.out.println("Press number of course you want to enter");

        int i = validation();

        System.out.println("Course selected");

        Course course = listOfCourses.get(i);
        System.out.println("You have selected " + course.getTitle() + ", " + course.getStream() + ", " + course.getType());

        return course;

    }

    public static Student studentSelection() {
        System.out.println("");
        System.out.println("Press number of student you want to choose");

        int i = validation();

        System.out.println("Student selected");

        Student student = listOfStudents.get(i);
        System.out.println("You have selected" + student.getFirstName() + ", " + student.getLastName() + ", "
                + student.getDateOfBirth());

        return student;
    }

    public static Assignment assignmentSelection() {
        System.out.println("");
        System.out.println("Press number of assignment you want to choose");

        int i = validation();

        System.out.println("Assignment selected");

        Assignment a = listOfAssignments.get(i);
        System.out.println("You have selected" + a.getTitle() + ", " + a.getDescription());

        return a;
    }

    public static void showCoursesList() {
        if (listOfCourses.size() == 0) {
            System.out.println("There is no course in school");
        } else {
            System.out.println("Courses :");
            int i = 0;
            for (Course s : listOfCourses) {
                System.out.println(i + "." + s.getTitle() + ", " + s.getStream() + ", " + s.getType());
                i++;
            }
        }

    }

    public static void submitDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter date : ");

        try {
            String inputDate = scanner.next();

            LocalDate date = LocalDate.parse(inputDate, formatter);   // formatter sto parse?

            LocalDate firstDayOfWeek = date;
            while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
                firstDayOfWeek = firstDayOfWeek.minusDays(1);
            }

            // NOW FIRSTDAYOFWEEK IS MONDAY WITH A DATE(DD/MM/YYYY)
            int r = 1;
            System.out.println("Students that have to submit their assignments during the week :" + firstDayOfWeek + " - " + firstDayOfWeek.plusDays(4));
            for (Student st : listOfStudents) {
                if (st.getAssignmentsPerStudent().size() > 0) {
                    for (int i = 0; i < 5; i++) {
                        for (Assignment as : st.getAssignmentsPerStudent()) {
                            if (as.getSubDateTime().isEqual(firstDayOfWeek)) {
                                System.out.println(r + ". " + st.toString());
                                System.out.println("Submision Date: " + firstDayOfWeek);
                                r++;
                            }
                        }
                        firstDayOfWeek = firstDayOfWeek.plusDays(1);
                    }
                }
            }

            System.out.println("--------------------");
            System.out.println("Print 0 for Menu");

        } catch (DateTimeParseException e) {
            e.printStackTrace();

        }
    }
}
