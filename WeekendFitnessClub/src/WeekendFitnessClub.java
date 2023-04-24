
import java.util.ArrayList;
import java.util.Random;

public class WeekendFitnessClub {

    private ArrayList<Lesson> lessons;
    private ArrayList<LessonClass> classes;
    private final int MAX_LESSONS_PER_DAY = 2;

    public WeekendFitnessClub() {
        lessons = new ArrayList<>();
        classes = new ArrayList<>();
    }

    public void addData() {

        addLesson(new Lesson("Spin", 100));
        addLesson(new Lesson("Yoga", 120));
        addLesson(new Lesson("Zumba", 130));
        addLesson(new Lesson("BodySculpt", 140));

        //8 Weekends
        for (int i = 1; i <= 8; i++) {
            //4 Lesson classes

            //2 For Saturday
            addLessonClass(new LessonClass(getLesson(0), i, "Saturday"));
            addLessonClass(new LessonClass(getLesson(1), i, "Saturday"));

            //2 For Sunday
            addLessonClass(new LessonClass(getLesson(2), i, "Sunday"));
            addLessonClass(new LessonClass(getLesson(3), i, "Sunday"));
        }

        Customer c1 = new Customer("William", "232442");
        Customer c2 = new Customer("Benjamin", "232442");
        Customer c3 = new Customer("Lucas", "232442");
        Customer c4 = new Customer("Henry", "232442");
        Customer c5 = new Customer("Theodore", "232442");
        Customer c6 = new Customer("Emma", "232442");
        Customer c7 = new Customer("Amelia", "232442");
        Customer c8 = new Customer("Olivia", "232442");
        Customer c9 = new Customer("Charlotte", "232442");
        Customer c10 = new Customer("Harper", "232442");

        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        customers.add(c5);
        customers.add(c6);
        customers.add(c7);
        customers.add(c8);
        customers.add(c9);
        customers.add(c10);

        bookForLesson(c1, classes.get(0));
        bookForLesson(c2, classes.get(1));
        bookForLesson(c3, classes.get(2));
        bookForLesson(c4, classes.get(3));
        bookForLesson(c5, classes.get(4));
        bookForLesson(c6, classes.get(5));
        bookForLesson(c7, classes.get(6));
        bookForLesson(c8, classes.get(7));
        bookForLesson(c9, classes.get(8));
        bookForLesson(c10, classes.get(9));
        bookForLesson(c10, classes.get(0));
        bookForLesson(c9, classes.get(1));
        bookForLesson(c8, classes.get(2));
        bookForLesson(c7, classes.get(3));
        bookForLesson(c6, classes.get(4));
        bookForLesson(c5, classes.get(5));
        bookForLesson(c4, classes.get(6));
        bookForLesson(c3, classes.get(7));
        bookForLesson(c2, classes.get(8));
        bookForLesson(c1, classes.get(9));

        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            Customer cust = customers.get(rnd.nextInt(customers.size()));
            LessonClass lc = classes.get(rnd.nextInt(classes.size()));
            bookForLesson(cust, lc);
        }
        for (LessonClass classe : classes) {
            for (Customer customer : classe.getCustomers()) {
                Review review = new Review("", rnd.nextInt(5) + 1, customer);
                classe.addReview(review);
            }
        }

    }

    public boolean addLesson(Lesson lesson) {
        for (Lesson l : lessons) {
            if (l.equals(lesson)) {
                System.out.println("Lesson with this name is already added!");
                return false;
            }
        }
        lessons.add(lesson);
        return true;
    }

    public Lesson getLesson(int index) {
        return lessons.get(index);
    }

    public boolean addLessonClass(LessonClass lessonClass) {
        //Check if particular day has booking capacity
        int count = 0;
        for (LessonClass b : classes) {
            if (b.isOnSameDay(lessonClass)) {
                count++;
            }
        }
        if (count == MAX_LESSONS_PER_DAY) {
            System.out.println("Maximum " + MAX_LESSONS_PER_DAY + " fitness lessons per day are allowed");
            return false;
        } else {
            classes.add(lessonClass);
            return true;
        }
    }

    public void displayHighestIncomeLessonType() {
        ArrayList<Double> incomes = new ArrayList<>();
        double highest = Double.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < lessons.size(); i++) {
            Lesson lesson = lessons.get(i);
            double income = 0;
            for (LessonClass cls : classes) {
                if (cls.getLesson().equals(lesson)) {
                    income += cls.calculateIncome();
                }
            }
            incomes.add(income);
            if (income >= highest) {
                highest = income;
                index = i;
            }
        }
        System.out.print(lessons.get(index).getName());
        System.out.println(", Total Income: " + highest);

    }

    public void printReports() {
        System.out.println("--------- Report 1 ---------");
        System.out.printf("\n%-10s %-10s %-10s %-10s %-20s %s\n", "Week", "Day", "Lesson", "Customers", "Average Rating", "Income");
        for (LessonClass clas : classes) {
            System.out.printf("%-10d %-10s %-10s     %-2d     %-20.2f %.2f\n", clas.getWeekend(), clas.getDay(), clas.getLesson().getName(), clas.getNumOfCustomers(), clas.getAverageRating(), clas.calculateIncome());
        }

        System.out.println("\n\n--------- Report 2 ---------");
        System.out.print("Highest Income Lesson Type: ");
        displayHighestIncomeLessonType();

    }

    public ArrayList<LessonClass> getClassesOn(String day) {
        ArrayList<LessonClass> dayClasses = new ArrayList<>();
        for (LessonClass cls : classes) {
            if (cls.getDay().equalsIgnoreCase(day)) {
                dayClasses.add(cls);
            }
        }
        return dayClasses;
    }

    public ArrayList<LessonClass> getClassesOfType(String type) {
        ArrayList<LessonClass> dayClasses = new ArrayList<>();
        for (LessonClass cls : classes) {
            if (cls.getLesson().getName().equalsIgnoreCase(type)) {
                dayClasses.add(cls);
            }
        }
        return dayClasses;
    }

    public void displayClasses(ArrayList<LessonClass> list) {
        System.out.println("Lesson Classes");
        int i = 1;
        System.out.printf("\n%-10s %-10s %-10s %-10s %-10s %s\n", "Sr. No.", "Week", "Day", "Lesson", "Customers", "Average Rating");
        for (LessonClass clas : list) {
            System.out.printf("%-10d %-10d %-10s %-10s     %-2d     %.2f\n", (i++), clas.getWeekend(), clas.getDay(), clas.getLesson().getName(), clas.getNumOfCustomers(), clas.getAverageRating());
        }
    }

    public void displayLessonTypes() {
        System.out.println("Lesson Types");
        for (Lesson lesson : lessons) {
            System.out.println(lesson.getName());
        }
    }

    public boolean bookForLesson(Customer customer, LessonClass lessonClass) {
        if (lessonClass.getCustomer(customer.getName()) != null) {
            return false;
        }
        if (lessonClass.addCustomer(customer)) {
            return true;
        }
        return false;
    }

    public ArrayList<LessonClass> getLessonClassesOfCustomer(String name) {
        ArrayList<LessonClass> dayClasses = new ArrayList<>();
        for (LessonClass cls : classes) {
            if (cls.getCustomer(name) != null) {
                dayClasses.add(cls);
            }
        }
        return dayClasses;
    }

    public boolean changeLessonBooking(Customer customer, LessonClass from, LessonClass to) {
        if (bookForLesson(customer, to)) {
            from.removeCustomer(customer);
            return true;
        }
        return false;
    }

    public void cancelLessonBooking(Customer customer, LessonClass selected) {
        selected.removeCustomer(customer);
    }

    public Customer getCustomer(String name) {
        for (LessonClass classe : classes) {
            Customer customer = classe.getCustomer(name);
            if (customer != null) {
                return customer;
            }
        }
        return null;
    }

}
