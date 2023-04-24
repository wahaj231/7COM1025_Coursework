
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static WeekendFitnessClub wfc;
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        wfc = new WeekendFitnessClub();
        wfc.addData();
        
        String choice;

        do {

            printMainMenu();
            System.out.print("> ");
            choice = sc.nextLine();

            if (choice.equals("1")) {
                wfc.printReports();
            } else if (choice.equals("2")) {
                bookLesson();
            } else if (choice.equals("3")) {
                changeLessonBooking();
            } else if (choice.equals("4")) {
                cancelLessonBooking();
            } else if (choice.equals("5")) {
                writeReview();
            } else if (choice.equals("6")) {
                System.out.println("Good Bye!");
            } else {
                System.out.println("Invalid Choice");
            }

            System.out.println("\n");

        } while (!choice.equals("6"));

    }

    private static void printMainMenu() {
        System.out.println("1. Print Reports");
        System.out.println("2. Book a Lesson");
        System.out.println("3. Change Booking");
        System.out.println("4. Cancel Booking");
        System.out.println("5. Write Review");
        System.out.println("6. Exit");
    }

    private static void bookLesson() {
        LessonClass selected = getLessonToBook();
        if (selected == null) {
            return;
        }
        
        System.out.println("Enter Customer Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Customer Number: ");
        String number = sc.nextLine();

        Customer customer = new Customer(name, number);
        if (wfc.bookForLesson(customer, selected)) {
            System.out.println("Lesson is Booked!");
        }

    }

    private static void changeLessonBooking() {
        System.out.println("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer customer = wfc.getCustomer(name);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        ArrayList<LessonClass> lessonClasses = wfc.getLessonClassesOfCustomer(name);
        wfc.displayClasses(lessonClasses);
        System.out.print("Select Lesson Class To Change: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 1 || index > lessonClasses.size()) {
            System.out.println("Invalid Choice!");
            return;
        }
        LessonClass from = lessonClasses.get(index - 1);
        LessonClass to = getLessonToBook();
        if (to == null) {
            return;
        }

        wfc.changeLessonBooking(customer, from, to);

    }

    private static void cancelLessonBooking() {
        System.out.println("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer customer = wfc.getCustomer(name);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        ArrayList<LessonClass> lessonClasses = wfc.getLessonClassesOfCustomer(name);
        wfc.displayClasses(lessonClasses);
        System.out.print("Select Lesson Class To Cancel: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 1 || index > lessonClasses.size()) {
            System.out.println("Invalid Choice!");
            return;
        }
        LessonClass selected = lessonClasses.get(index - 1);

        wfc.cancelLessonBooking(customer, selected);
        System.out.println("Booking Cancelled!");

    }

    private static void writeReview() {
        System.out.println("Enter Customer Name: ");
        String name = sc.nextLine();

        Customer customer = wfc.getCustomer(name);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        ArrayList<LessonClass> lessonClasses = wfc.getLessonClassesOfCustomer(name);
        wfc.displayClasses(lessonClasses);
        System.out.print("Select Lesson Class To Write Review: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 1 || index > lessonClasses.size()) {
            System.out.println("Invalid Choice!");
            return;
        }
        LessonClass selected = lessonClasses.get(index - 1);

        System.out.print("Enter Comment: ");
        String comment = sc.nextLine();
        System.out.print("Input rating (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied): ");
        int rating = sc.nextInt();
        sc.nextLine();
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating");
            return;
        }

        Review review = new Review(comment, rating, customer);

        selected.addReview(review);
        System.out.println("Review is added!");

    }

    private static LessonClass getLessonToBook() {
        System.out.println("Display Timetable");
        System.out.println("1. By Day");
        System.out.println("2. By Fitness Type");
        System.out.print("> ");
        String choice = sc.nextLine();
        ArrayList<LessonClass> lessonClasses = new ArrayList<>();

        if (choice.equals("1")) {
            System.out.println("Select day");
            System.out.println("Saturday");
            System.out.println("Sunday");
            System.out.print("> ");
            String day = sc.nextLine();
            lessonClasses = wfc.getClassesOn(day);
        } else if (choice.equals("2")) {
            wfc.displayLessonTypes();
            System.out.print("> ");
            String type = sc.nextLine();
            lessonClasses = wfc.getClassesOfType(type);
        } else {
            System.out.println("Invalid Choice!");
            return null;
        }

        wfc.displayClasses(lessonClasses);
        System.out.print("Select Lesson Class: ");
        int index = sc.nextInt();
        sc.nextLine();
        if (index < 1 || index > lessonClasses.size()) {
            System.out.println("Invalid Choice!");
            return null;
        }
        return lessonClasses.get(index - 1);
    }

}
