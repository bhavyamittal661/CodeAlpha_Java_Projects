//Student Grade Tracker

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    // Constructor
    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // Add grade method
    public void addGrade(int grade) {
        grades.add(grade);
    }

    // Calculate average grade
    public double calculateAverage() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return grades.isEmpty() ? 0 : (double) sum / grades.size();
    }

    // Find highest grade
    public int getHighestGrade() {
        return grades.stream().max(Integer::compare).orElse(0);
    }

    // Find lowest grade
    public int getLowestGrade() {
        return grades.stream().min(Integer::compare).orElse(0);
    }

    // Display student details
    public void displayDetails() {
        System.out.println("\nStudent Name: " + name);
        System.out.println("Grades: " + grades);
        System.out.println("Average Grade: " + calculateAverage());
        System.out.println("Highest Grade: " + getHighestGrade());
        System.out.println("Lowest Grade: " + getLowestGrade());
    }
}

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Input student details
        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter student " + (i + 1) + " name: ");
            String name = scanner.nextLine();
            Student student = new Student(name);

            System.out.print("Enter number of grades for " + name + ": ");
            int numGrades = scanner.nextInt();

            // Input grades
            for (int j = 0; j < numGrades; j++) {
                System.out.print("Enter grade " + (j + 1) + ": ");
                int grade = scanner.nextInt();
                student.addGrade(grade);
            }

            students.add(student);
            scanner.nextLine(); // Consume the newline
        }

        // Display all student details
        System.out.println("\n==== Student Grade Report ====");
        for (Student student : students) {
            student.displayDetails();
        }

        scanner.close();
    }
}