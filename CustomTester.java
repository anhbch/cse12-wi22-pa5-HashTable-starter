/**
 * Name: Anh Bach
 * ID: A17133630
 * Email: tbach@ucsd.edu
 * Sources used: PA 5 Example, Class Lectures.
 * This file contains CustomTester class to test all given
 * methods in Student, Course and Sanctuary classes. It tests 
 * different cases to make sure all the methods work propely.
 */

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class contains different custom tests for Student,
 * Course an Sanctuary classes. It tests all given methods 
 * that are inside those classes
 * 
 * IMPORTANT: Do not change the method names and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class CustomTester {

    private final static String EXCEPTION = "Exception is thrown!";

    // ----------------Student class----------------
    /**
     * Test the equals method when two different Student objects
     */
    @Test
    public void testEquals() {
        Student stu1 = new Student(new String("Hiroki"), 
            new String("Honda"), new String("A11223344"));
        Student stu2 = new Student(new String("Gavin"), 
            new String("Zhao"), new String("A22334455"));
        assertFalse(stu1.equals(stu2));
    }

    /**
     * Test the compareTo method when two students have different
     * last, first name and PID.
     * When two students same last name
     * When two students same last and first name
     */
    @Test
    public void testCompareTo() {
        // Different last name, first name, PID
        Student stu1 = new Student(new String("Hiroki"), 
            new String("Honda"), new String("A11223344"));
        Student stu2 = new Student(new String("Gavin"), 
            new String("Zhao"), new String("A22334455"));
        assertEquals(-1, stu1.compareTo(stu2));
        assertEquals(1, stu2.compareTo(stu1));
        assertEquals(0, stu1.compareTo(stu1));

        // Same last name
        Student stu3 = new Student(new String("Kai"), 
            new String("Honda"), new String("A33445566"));
        assertEquals(1, stu3.compareTo(stu1));
        assertEquals(-1, stu1.compareTo(stu3));

        // Same last and first name
        Student stu4 = new Student(new String("Hiroki"), 
            new String("Honda"), new String("A44556677"));
        assertEquals(1, stu4.compareTo(stu1));

        // Class example
        Student stu5 = new Student(new String("Angela"), 
            new String("Jones"), new String("A12345678"));
        Student stu6 = new Student(new String("Aaron"), 
            new String("Smith"), new String("A12345679"));
        assertEquals(-1, stu5.compareTo(stu6));
        
    }

    // ----------------Course class----------------
    /**
     * Test the enroll method when student is null and
     * student who is already enrolled
     */
    @Test
    public void testEnroll() {
        // When student is null
        Course course = new Course("Art", "1", "Art", 200);
        course.enrolled = new HashSet<>();
        try {
            Student stu1 = new Student(null, null, null);
            course.enroll(stu1);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }

        // When student is already enrolled
        Student stu2 = new Student("H", "H", "A11223344");
        course.enrolled.add(stu2);
        assertTrue(course.enrolled.contains(stu2));
        assertFalse(course.enroll(stu2));
    }

    /**
     * Test the unenroll method when the student doesn't enroll in the course
     * and when student is null
     */
    @Test
    public void testUnenroll() {
        Course course = new Course("Art", "1", "Art", 200);
        course.enrolled = new HashSet<>();
        Student stu1 = new Student(new String("Hiroki"), 
            new String("Honda"), new String("A11223344"));
        Student stu2 = new Student(new String("Gavin"), 
            new String("Zhao"), new String("A22334455"));
        Student stu3 = new Student(new String("Kai"), 
            new String("Honda"), new String("A33445566"));   
        course.enrolled.add(stu1); 
        course.enrolled.add(stu2);

        // When student doesn't enroll in course
        assertFalse(course.unenroll(stu3));

        // When student is null
        try {
            Student stu = new Student(null, null, null);
            course.unenroll(stu);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }
    }

    /**
     * Test the getRoster method when the enrolled collection is not empty 
     * and turn the collection of enrolled students into an ArrayList collection
     */
    @Test
    public void testGetRoster() {
        Course course = new Course("Art", "1", "Art", 200);
        course.enrolled = new HashSet<>();
        Student stu1 = new Student(new String("Hiroki"), 
            new String("Honda"), new String("A11223344"));
        Student stu2 = new Student(new String("Gavin"), 
            new String("Zhao"), new String("A22334455"));
        Student stu3 = new Student(new String("Kai"), 
            new String("Honda"), new String("A33445566")); 
        course.enrolled.add(stu1); 
        course.enrolled.add(stu2);
        course.enrolled.add(stu3);

        ArrayList<Student> originalList = new ArrayList<>();
        originalList.add(stu1);
        originalList.add(stu3);
        originalList.add(stu2);

        ArrayList<Student> copyList = new ArrayList<>();
        copyList = course.getRoster();

        assertEquals(originalList, copyList);
    }

    // ----------------Sanctuary class----------------
    /**
     * Test the constructor when maxAnimals < 0
     * and maxSpecies < 0
     */
    @Test
    public void testSanctuaryConstructor() {
        try {
            Sanctuary sanct1 = new Sanctuary(8, -5);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }

        try {
            Sanctuary sanct2 = new Sanctuary(-8, 50);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }

    }

    /**
     * Test the rescue method when sanctuary is full
     */
    @Test
    public void testRescueTestOne(){
        Sanctuary sanct = new Sanctuary(10, 3);
        sanct.sanctuary.put("A", 5);
        sanct.sanctuary.put("B", 3);
        sanct.sanctuary.put("C", 1);

        assertEquals(3, sanct.getTotalSpecies());
        assertEquals(9, sanct.getTotalAnimals());
        assertEquals(4, sanct.rescue("A", 5));

        // Add the 4rd species
        sanct.rescue("D", 2);
        assertEquals(2, sanct.rescue("D", 2));
        assertFalse(sanct.sanctuary.containsKey("D"));
    }

    /**
     * Test the rescue method when num <=0
     */
    @Test
    public void testRescueTestTwo(){
        Sanctuary sanct = new Sanctuary(10, 3);
        sanct.sanctuary.put("A", 5);
        sanct.sanctuary.put("B", 4);
        try {
            sanct.rescue("C", 0);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }
    }

    /**
     * Test the release method when num is bigger than 
     * number of animals of the species.
     */
    @Test
    public void testReleaseTestOne(){
        Sanctuary sanct = new Sanctuary(10, 3);
        sanct.sanctuary.put("A", 5);
        sanct.sanctuary.put("B", 4);

        try {
            sanct.release("A", 10);
            fail();
        }
        catch (IllegalArgumentException e) {
            System.out.println(EXCEPTION);
        }
    }

    /**
     * Test the release method when remove all of number of animals 
     * of the specifies 
     */
    @Test
    public void testReleaseTestTwo(){
        Sanctuary sanct = new Sanctuary(10, 3);
        sanct.sanctuary.put("A", 5);
        sanct.sanctuary.put("B", 4);

        sanct.release("A", 5);
        assertFalse(sanct.sanctuary.containsKey("A"));
    }
}

