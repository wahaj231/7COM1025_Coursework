
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC
 */
public class WeekendFitnessClubTest {

    Lesson lesson = new Lesson("Yoga", 100);
    int weekend1 = 1;
    int weekend2 = 2;
    String saturday = "Saturday";
    String sunday = "Sunday";
    Customer customer = new Customer("William", "1234");

    /**
     * Test of addLesson method, of class WeekendFitnessClub.
     */
    @Test
    public void testAddLesson() {
        System.out.println("addLesson");
        WeekendFitnessClub instance = new WeekendFitnessClub();
        boolean expResult = true;
        boolean result = instance.addLesson(lesson);
        assertEquals(expResult, result);
        expResult = false;
        result = instance.addLesson(lesson);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLesson method, of class WeekendFitnessClub.
     */
    @Test
    public void testGetLesson() {
        System.out.println("getLesson");
        int index = 0;
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addLesson(lesson);
        Lesson expResult = lesson;
        Lesson result = instance.getLesson(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassesOn method, of class WeekendFitnessClub.
     */
    @Test
    public void testGetClassesOn() {
        System.out.println("getClassesOn");
        String day = saturday;
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addData();
        ArrayList<LessonClass> result = instance.getClassesOn(day);
        for (LessonClass lessonClass : result) {
            assertEquals(saturday, lessonClass.getDay());
        }
    }

    /**
     * Test of getClassesOfType method, of class WeekendFitnessClub.
     */
    @Test
    public void testGetClassesOfType() {
        System.out.println("getClassesOfType");
        String type = "Yoga";
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addData();
        ArrayList<LessonClass> result = instance.getClassesOfType(type);
        for (LessonClass lessonClass : result) {
            assertEquals(type, lessonClass.getLesson().getName());
        }
    }

    /**
     * Test of bookForLesson method, of class WeekendFitnessClub.
     */
    @Test
    public void testBookForLesson() {
        System.out.println("bookForLesson");
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addLesson(lesson);
        instance.addLessonClass(new LessonClass(lesson, weekend1, sunday));
        LessonClass selected = instance.getClassesOn(sunday).get(0);
        boolean expResult = true;
        boolean result = instance.bookForLesson(customer, selected);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLessonClassesOfCustomer method, of class WeekendFitnessClub.
     */
    @Test
    public void testGetLessonClassesOfCustomer() {
        System.out.println("getLessonClassesOfCustomer");
        String name = customer.getName();
        WeekendFitnessClub instance = new WeekendFitnessClub();
        LessonClass lc = new LessonClass(lesson, weekend1, saturday);
        instance.addLessonClass(lc);
        lc.addCustomer(customer);
        ArrayList<LessonClass> result = instance.getLessonClassesOfCustomer(name);
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).equals(lc));
    }

    /**
     * Test of changeLessonBooking method, of class WeekendFitnessClub.
     */
    @Test
    public void testChangeLessonBooking() {
        System.out.println("changeLessonBooking");
        LessonClass to = new LessonClass(lesson, weekend1, saturday);
        LessonClass from = new LessonClass(lesson, weekend1, sunday);
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addLessonClass(from);
        instance.addLessonClass(to);
        from.addCustomer(customer);

        boolean expResult = true;
        boolean result = instance.changeLessonBooking(customer, from, to);
        assertEquals(expResult, result);
    }

    /**
     * Test of cancelLessonBooking method, of class WeekendFitnessClub.
     */
    @Test
    public void testCancelLessonBooking() {
        System.out.println("cancelLessonBooking");
        LessonClass from = new LessonClass(lesson, weekend1, sunday);
        WeekendFitnessClub instance = new WeekendFitnessClub();
        instance.addLessonClass(from);
        from.addCustomer(customer);

        instance.cancelLessonBooking(customer, from);
    }

    /**
     * Test of getCustomer method, of class WeekendFitnessClub.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        String name = customer.getName();
        WeekendFitnessClub instance = new WeekendFitnessClub();
        LessonClass lc = new LessonClass(lesson, weekend1, saturday);
        instance.addLessonClass(lc);
        lc.addCustomer(customer);

        Customer expResult = customer;
        Customer result = instance.getCustomer(name);
        assertEquals(expResult.getName(), result.getName());
    }

}
