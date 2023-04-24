
import java.util.ArrayList;

public class LessonClass {

    private Lesson lesson;
    private ArrayList<Customer> customers;
    private ArrayList<Review> reviews;
    private int weekend;
    private String day;
    private final int MAX_CUSTOMERS = 5;

    public LessonClass(Lesson lesson, int weekend, String day) {
        this.lesson = lesson;
        this.weekend = weekend;
        this.day = day;
        this.customers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public boolean isOnSameDay(LessonClass booking) {
        return weekend == booking.weekend && day.equals(booking.day);
    }

    public Lesson getLesson() {
        return lesson;
    }

    public int getWeekend() {
        return weekend;
    }

    public String getDay() {
        return day;
    }

    public int getNumOfCustomers() {
        return customers.size();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
    
    

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }

    public double calculateIncome() {
        return lesson.getPrice() * customers.size();
    }

    public boolean addCustomer(Customer customer) {
        if (customers.size() == MAX_CUSTOMERS) {
            System.out.println("Max 5 customers can book for a lesson class.");
            return false;
        }
        customers.add(customer);
        return true;
    }

    public Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    public void removeCustomer(Customer customer) {
        customers.remove(getCustomer(customer.getName()));
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

}
