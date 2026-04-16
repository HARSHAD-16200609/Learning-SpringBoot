package com.engineeringdigest.collectionframework;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final String name;
    private final double cgpa;

    public Student(String name, double cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Double.compare(student.cgpa, cgpa) == 0 && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cgpa);
    }

    @Override
    public int compareTo(Student other) {
        int cgpaComparison = Double.compare(other.cgpa, this.cgpa); // higher CGPA first
        if (cgpaComparison != 0) {
            return cgpaComparison;
        }
        return this.name.compareTo(other.name); // alphabetical tie-breaker
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', cgpa=" + cgpa + "}";
    }
}

