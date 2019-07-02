/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_app_part_b;

import static dao.AssignmentDao.deleteAssignment;
import static dao.AssignmentDao.insertAssignment;
import static dao.AssignmentDao.printAssignments;
import static dao.AssignmentsPerCourseDao.insertAssignmentToCourse;
import static dao.AssignmentsPerCourseDao.printAssignmentsPerCourse;
import static dao.CourseDao.deleteCourse;
import static dao.CourseDao.insertCourse;
import static dao.CourseDao.printCourses;
import dao.ProfessionDao;
import static dao.StudentAssignmentsDao.insertAssignmentToStudent;
import static dao.StudentAssignmentsDao.printStudentAssignmentsPerCourse;
import static dao.StudentDao.insertStudent;
import static dao.StudentDao.printStudents;
import static dao.StudentDao.updateStudent;
import static dao.StudentsPerCourseDao.insertStudentToCourse;
import static dao.StudentsPerCourseDao.printStudentsPerCourse;
import static dao.StudentsPerCourseDao.printStudentsWithManyCourses;
import static dao.TrainerDao.deleteTrainer;
import static dao.TrainerDao.insertTrainer;
import static dao.TrainerDao.printTrainers;
import static dao.TrainerDao.updateTrainer;
import static dao.TrainersPerCourseDao.insertTrainerToCourse;
import static dao.TrainersPerCourseDao.printTrainersPerCourse;
import java.util.Scanner;

public class School_App_part_B {

    static ProfessionDao pdao = new ProfessionDao();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean valid = true;
        System.out.println("Welcome to AFDemp!\nPlease select:");
        printMenu();
        int input;
        while (valid) {
            input = validation();
            switch (input) {
                case 0:
                    valid = false;
                    break;
                case 1:
                    /*PRINT LIST OF ALL STUDENTS*/
                    printStudents();
                    message();
                    break;
                case 2:
                    /*PRINT LIST OF ALL TRAINERS*/
                    printTrainers();
                    message();
                    break;
                case 3:
                    /*PRINT LIST OF ALL ASSIGNMENTS*/
                    printAssignments();
                    message();
                    break;
                case 4:
                    /*PRINT LIST OF ALL COURSES*/
                    printCourses();
                    message();
                    break;
                case 5:
                    /*PRINT STUDENTS PER COURSE*/
                    printStudentsPerCourse();
                    message();
                    break;
                case 6:
                    /*PRINT TRAINERS PER COURSE*/
                    printTrainersPerCourse();
                    message();
                    break;
                case 7:
                    /*PRINT ASSIGNMENTS PER COURSE*/
                    printAssignmentsPerCourse();
                    message();
                    break;
                case 8:
                    /*PRINT ASSIGNMENTS PER STUDENT PER COURSE*/
                    printStudentAssignmentsPerCourse();
                    message();
                    break;
                case 9:
                    /*PRINT STUDENTS WITH MORE THAN ONE COURSE*/
                    printStudentsWithManyCourses();
                    message();
                    break;
                case 10:
                    /*INSERT STUDENT*/
                    insertStudent();

                    break;
                case 11:
                    /*UPDATE STUDENT*/
                    updateStudent();

                    break;
                case 12:
                    printMenu();

                    break;
                case 13:
                    /*INSERT TRAINER*/
                    insertTrainer();
                    break;
                case 14:
                    /*UPDATE TRAINER*/
                    updateTrainer();
                    break;
                case 15:
                    /*DELETE TRAINER*/
                    deleteTrainer();
                    break;
                case 16:
                    /*INSERT COURSE*/
                    insertCourse();
                    break;

                case 18:
                    /*DELETE COURSE*/
                    deleteCourse();
                    break;
                case 19:
                    /*INSERT ASSIGNMENT */
                    insertAssignment();
                    break;

                case 21:
                    /*DELETE ASSIGNMENT*/
                    deleteAssignment();
                    break;
                case 22:
                    /* INSERT STUDENT TO COURSE*/
                    insertStudentToCourse();
                    break;
                case 23:
                    /*INSERT ASSIGNMENT TO COURSE*/
                    insertAssignmentToCourse();
                    break;
                case 24:
                    /*INSERT TRAINER TO COURSE*/
                    insertTrainerToCourse();
                    break;
                case 25:
                    /*INSERT ASSIGNMENTS TO STUDENT*/
                    insertAssignmentToStudent();
                    break;
                case 26:

                    break;

            }
        }

    }

    public static void printMenu() {

        System.out.println(" 1. Print all students  \n"
                + " 2. Print all trainers  \n"
                + " 3. Print all assignments \n"
                + " 4. Print all courses\n"
                + " 5. Print all students per course\n"
                + " 6. Print all trainers per course \n"
                + " 7. Print all assignments per course\n"
                + " 8. Print all asssignments per course per student\n"
                + " 9. Print all students that belong to more than one course\n"
                + " 10. Insert student\n"
                + " 11. Update Student\n"
                + " 12. Print Menu\n"
                + " 13. Insert Trainer\n"
                + " 14. Update Trainer\n"
                + " 15. Delete Trainer\n"
                + " 16. Insert Course\n"
                + " 18. Delete Course\n"
                + " 19. Insert Assignment\n"
                + " 21. Delete Assignment\n"
                + " 22. Insert student to course\n"
                + " 23. Insert assignment to course\n"
                + " 24. Insert trainer to course\n"
                + " 25. Insert assignment to student\n"
                + " 0. Exit\n"
        );
    }

    public static int validation() {
        int number = -1;
        do {
            System.out.print("Please enter a number between 0 and 25 (except 17 and 20) : ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
            } else {
                System.out.println("Invalid Input. Please enter an integer");
                scanner.nextLine();
            }

        } while (number < 0);

        return number;
    }

    public static void message() {
        System.out.println("Press 12 for menu\nPress 0 to exit");
    }

}
