
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class LessonClassTest {

    Lesson lesson = new Lesson("Yoga", 100);
    int weekend1 = 1;
    int weekend2 = 2;
    String saturday = "Saturday";
    String sunday = "Sunday";
    Customer customer = new Customer("William", "1234");

    /**
     * Test of isOnSameDay method, of class LessonClass.
     */
    @Test
    public void testIsOnSameDay() {
        System.out.println("isOnSameDay");
        LessonClass booking = new LessonClass(lesson, weekend1, saturday);
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        LessonClass instance2 = new LessonClass(lesson, weekend1, sunday);
        assertTrue(instance.isOnSameDay(booking));
        assertFalse(instance2.isOnSameDay(booking));
    }

    /**
     * Test of getLesson method, of class LessonClass.
     */
    @Test
    public void testGetLesson() {
        System.out.println("getLesson");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        Lesson expResult = lesson;
        Lesson result = instance.getLesson();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeekend method, of class LessonClass.
     */
    @Test
    public void testGetWeekend() {
        System.out.println("getWeekend");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        int expResult = weekend1;
        int result = instance.getWeekend();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDay method, of class LessonClass.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        String expResult = saturday;
        String result = instance.getDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumOfCustomers method, of class LessonClass.
     */
    @Test
    public void testGetNumOfCustomers() {
        System.out.println("getNumOfCustomers");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        int expResult = 0;
        int result = instance.getNumOfCustomers();
        assertEquals(expResult, result);
        instance.addCustomer(customer);
        expResult = 1;
        result = instance.getNumOfCustomers();
        assertEquals(expResult, result);

    }

    /**
     * Test of getAverageRating method, of class LessonClass.
     */
    @Test
    public void testGetAverageRating() {
        System.out.println("getAverageRating");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        double expResult = 0.0;
        double result = instance.getAverageRating();
        assertEquals(expResult, result, 0.0);
        instance.addReview(new Review("", 5, customer));
        expResult = 5.0;
        result = instance.getAverageRating();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of calculateIncome method, of class LessonClass.
     */
    @Test
    public void testCalculateIncome() {
        System.out.println("calculateIncome");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        double expResult = 0.0;
        double result = instance.calculateIncome();
        assertEquals(expResult, result, 0.0);
        instance.addCustomer(customer);
        expResult = lesson.getPrice();
        result = instance.calculateIncome();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of addCustomer method, of class LessonClass.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        boolean expResult = true;
        boolean result = instance.addCustomer(customer);
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addCustomer(new Customer("a", "2"));
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addCustomer(new Customer("b", "3"));
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addCustomer(new Customer("c", "4"));
        assertEquals(expResult, result);
        expResult = true;
        result = instance.addCustomer(new Customer("d", "5"));
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addCustomer(new Customer("e", "6"));
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomer method, of class LessonClass.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        String name = customer.getName();
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        instance.addCustomer(customer);
        Customer expResult = customer;
        Customer result = instance.getCustomer(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeCustomer method, of class LessonClass.
     */
    @Test
    public void testRemoveCustomer() {
        System.out.println("removeCustomer");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        int expResult = 0;
        int result = instance.getNumOfCustomers();
        assertEquals(expResult, result);
        instance.addCustomer(customer);
        expResult = 1;
        result = instance.getNumOfCustomers();
        assertEquals(expResult, result);
        instance.removeCustomer(customer);
        expResult = 0;
        result = instance.getNumOfCustomers();
        assertEquals(expResult, result);
    }

    /**
     * Test of addReview method, of class LessonClass.
     */
    @Test
    public void testAddReview() {
        System.out.println("addReview");
        LessonClass instance = new LessonClass(lesson, weekend1, saturday);
        double expResult = 0.0;
        double result = instance.getAverageRating();
        assertEquals(expResult, result, 0.0);
        instance.addReview(new Review("", 5, customer));
        expResult = 5.0;
        result = instance.getAverageRating();
        assertEquals(expResult, result, 0.0);
    }

}
