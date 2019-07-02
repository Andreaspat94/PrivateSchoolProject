/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privateschoolproject;

import java.text.ParseException;
import java.util.Scanner;

import java.util.ArrayList;

import java.util.List;



public class Course {

    private static Scanner s = new Scanner(System.in);

    private String title, stream, type;
    private String startingDate;
    private String endingDate;
    private List<Student> studentList = new ArrayList();
    private List<Trainer> trainerList = new ArrayList();
    private static List<Assignment> assignmentList = new ArrayList();

    public Course() throws ParseException {

        System.out.println("Please enter title :");
        setTitle(s.nextLine());
        System.out.println("Please enter stream :");
        setStream(s.nextLine());
        System.out.println("Please enter type :");
        setType(s.nextLine());
        System.out.println("Please enter starting date : (dd/MM/yyyy)");
        setStartingDate(s.nextLine());
        System.out.println("Please enter ending date : (dd/MM/yyyy)");
        setEndingDate(s.nextLine());
        System.out.println("Course added");
        System.out.println("");

    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public Student addStudentProcess(List<Student> list, List<Student> studentList) {
        if (list.isEmpty()) {
            System.out.println("No students in school\nPress 0 for Menu");
        } else {
            int i = 1;
            for (Student s : list) {
                System.out.println(i + "." + s.toString());
                i++;
            }
            System.out.println("");
            System.out.println("Choose number of student you want to add");

            int r = validation();         //checking if input is valid

            Student student = list.get(r - 1);

            if (addStudent(student, studentList) == 1) {
                studentList.add(student);
                System.out.println("");
                System.out.println("Student added");
            }

            //ton perase stin lista students tou course
            return student;
        }
        return null;
    }

    public static void addTrainerProcess(List<Trainer> list, List<Trainer> trainerList) {
        if (list.isEmpty()) {
            System.out.println("No trainers in school\nPress 0 for Menu");
        } else {
            int i = 1;
            for (Trainer t : list) {
                System.out.println(i + "." + s.toString());
                i++;
            }
            System.out.println("");
            System.out.println("Choose number of trainer you want to add");
            int r = validation();        //checking if input is valid

            Trainer trainer = list.get(r);
            addTrainer(trainer, trainerList);
            System.out.println("");
            System.out.println("Trainer added");
        }
    }

    public static int addStudent(Student student, List<Student> list) {

        for (Student st : list) {
            if (student.getFirstName().equals(st.getFirstName())) {
                if (student.getLastName().equals(st.getLastName())) {
                    if (student.getDateOfBirth().equals(st.getDateOfBirth())) {
                        System.out.println("Student " + student.getFirstName() + " "
                                + student.getLastName() + " with date of birth : "
                                + student.getDateOfBirth() + " has already enrolled"
                                + "in the course");
                        return -1;
                    }
                }
            }
        }
        return 1;
    }

    public static void addTrainer(Trainer trainer, List<Trainer> list) {
        for (Trainer tr : list) {
            if (trainer.getFirstName().equals(tr.getFirstName())) {
                if (trainer.getLastName().equals(tr.getLastName())) {
                    System.out.println("Trainer " + trainer.getFirstName() + " "
                            + trainer.getLastName() + " has already enrolled"
                            + "as a trainer of this course");
                    break;
                }
            }
        }
        list.add(trainer);
    }
    //this method adds the new assignment to a course. checks also if there is a duplicate 

    public int addAssignment(Assignment assignment) {
        for (Assignment as : assignmentList) {
            if (assignment.getTitle().equals(as.getTitle())) {
                if (assignment.getDescription().equals(as.getDescription())) {
                    if (assignment.getSubDateTime().equals(as.getSubDateTime())) {
                        System.out.println("This assignment has already assigned");
                        return -1;
                    }
                }
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "Course{" + "title=" + title + ", stream=" + stream + ", type=" + type + ", startingDate=" + startingDate + ", endingDate=" + endingDate + '}';
    }

    public static int validation() {
        int number;
        do {
            while (!s.hasNextInt()) {
                System.out.println("Input must be a number. Enter number");
                s.nextLine();
            }

            number = s.nextInt();

        } while (number < 0);

        while (number < 0) {
            System.out.println("Number can not be negative\nPlease enter a valid number");
            number = s.nextInt();
        }

        return number;
    }

    public void showAssignmentList() {
        int i = 1;
        for (Assignment a : assignmentList) {
            System.out.println(i + "." + a.toString());
        }
    }
}
