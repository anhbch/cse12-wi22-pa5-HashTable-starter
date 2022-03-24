/**
 * Name: Anh Bach
 * ID: A17133630
 * Email: tbach@ucsd.edu
 * Sources used: Lecture Slides, Oracle, Piazza
 * This file contains Course class that store students register
 * for specific courses in a form of HashSet. It contains all
 * the methods to help organize courses and students
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents the specific course that students
 * register for. Students' information are stored in HashSet.
 * It contains different methods to help keep track student registation. 
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    private final static String TEMPLATE = "%s %s [%s]\n%s";

    /**
     * Constuctor to initialize the course’s information 
     * with an initial enrollment of 0 students
     * @param department - the department that the course falls into
     * @param number - course's number
     * @param description - description of the course
     * @param capacity - maximum number of students that can be 
     * enrolled in this course
     */
    public Course(String department, String number, String description, 
        int capacity) {
        if (department == null || 
            number == null || description == null) {
            throw new IllegalArgumentException();
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.enrolled = new HashSet<Student>(0);
        this.capacity = capacity;
        this.department = department;
        this.number = number;
        this.description = description;
    }

    /**
     * Method to get course's department
     * @return the department name
     */
    public String getDepartment(){
        return this.department;
    }

    /**
     * Method to get course's number
     * @return course's number
     */
    public String getNumber(){
        return this.number;
    }

    /**
     * Method to get course's description
     * @return the description for the course
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Method to get course's capacity
     * @return the capacity of the course
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Method to check if student successfully enroll in the course
     * @param student - student that need to be added to the class
     * @return true for a successful enrollment, false otherwise.
     */
    public boolean enroll(Student student) {
        // Exception
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (isFull() || enrolled.contains(student)) {
            return false;
        }
        // Add student 
        return enrolled.add(student);
    }

    /**
     * Method to check if student sccessfully unenroll in the course
     * @param student - student that need to be unenrolled to the class
     * @return true for a successful unenrollment, false otherwise.
     */
    public boolean unenroll(Student student) {
        // Exception
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (!(enrolled.contains(student))) {
            return false;
        }
        // Remove student
        return enrolled.remove(student);
    }

    /**
     * Remove all the students from the course if the class
     * is cancelled
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Method to check the capacity of the course
     * @return true if course is at its capacity,
     * false otherwise
     */
    public boolean isFull() {
        // If course is full
        if (enrolled.size() == capacity) {
            return true;
        }
        // If course is not full
        return false;
    }

    /**
     * Find the current number of enrolled students
     * @return current number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Method to find the available seats of the course
     * @return number of students that can still enroll in the course 
     */
    public int getAvailableSeats() {
        return (capacity - enrolled.size());
    }

    /**
     * Method to create a shallow copy of all the students 
     * that are enrolled in this course
     * @return shallow copy of all the students enrolled in this course
     */
    @SuppressWarnings("unchecked")
    public HashSet<Student> getStudents() {
        HashSet<Student> copyEnrolled = new HashSet<>();
        // Shallow copy
        copyEnrolled = (HashSet<Student>) enrolled.clone();
        return copyEnrolled;
    }

    /**
     * Method to turn the collection of enrolled students 
     * into an ArrayList collection
     * @return an ArrayList version of enrolled students
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> newArray = new ArrayList<>();
        // Create iterator to traverse the collection
        Iterator<Student> it = enrolled.iterator();
        while (it.hasNext()) {
            newArray.add(it.next()); 
        }
        // Sort array list
        Collections.sort(newArray);
        return newArray;
    }

    /**
     * Generate the string representation for a course
     * @return a string representation for a Course object
     */
    public String toString() {
        return String.format(TEMPLATE, department, 
            number, capacity, description);
    }
}

