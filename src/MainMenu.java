import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while(true) {

            System.out.println("\n===== COURSE REGISTRATION SYSTEM =====");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Add Course");
            System.out.println("4. View Courses");
            System.out.println("5. Register Course");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                    AddStudent.addStudent();
                    break;

                case 2:
                    ViewStudents.viewStudents();
                    break;

                case 3:
                    AddCourse.addCourse();
                    break;

                case 4:
                    ViewCourses.viewCourses();
                    break;

                case 5:
                    RegisterCourse.registerCourse();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}