package tracker;


public class Student {
    private static int nextId = 1000;
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;

    private int Java;
    private int DSA;
    private int Databases;
    private int Spring;

    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.studentId = generateNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private static int generateNextId() {
        return nextId++;
    }

    public int getJava() {
        return Java;
    }

    public void setJava(int java) {
        Java = java;
    }

    public int getDSA() {
        return DSA;
    }

    public void setDSA(int DSA) {
        this.DSA = DSA;
    }

    public int getDatabases() {
        return Databases;
    }

    public void setDatabases(int databases) {
        Databases = databases;
    }

    public int getSpring() {
        return Spring;
    }

    public void setSpring(int spring) {
        Spring = spring;
    }
}
