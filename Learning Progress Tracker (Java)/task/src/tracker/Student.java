package tracker;


public class Student {
    private static int nextId = 1000;
    private static final double javaToCompletePoints = 600;
    private static final double dsaToCompletePoints = 400;
    private static final double databasesToCompletePoints = 480;
    private static final double springToCompletePoints = 550;

    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int Java;
    private int DSA;
    private int Databases;
    private int Spring;
    private double completedJavaPercentage;
    private double completedDSAPercentage;
    private double completedDatabasesPercentage;
    private double completedSpringPercentage;



    public Student() {
    }

    public Student(String firstName, String lastName, String email) {
        this.studentId = generateNextId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.completedJavaPercentage = getPercentage(javaToCompletePoints,(double) Java);
//        this.completedDSAPercentage = getPercentage(dsaToCompletePoints, (double) DSA);
//        this.completedDatabasesPercentage = getPercentage(databasesToCompletePoints, (double) Databases);
//        this.completedSpringPercentage = getPercentage(springToCompletePoints, (double) Spring);
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
        getPercentage(javaToCompletePoints, java);
        Java = java;
    }

    public int getDSA() {
        return DSA;
    }

    public void setDSA(int DSA) {
        getPercentage(javaToCompletePoints, DSA);
        this.DSA = DSA;
    }

    public int getDatabases() {
        return Databases;
    }

    public void setDatabases(int databases) {
        getPercentage(javaToCompletePoints, databases);
        Databases = databases;
    }

    public int getSpring() {
        return Spring;
    }

    public void setSpring(int spring) {
        getPercentage(javaToCompletePoints, spring);
        Spring = spring;
    }

    public double getPercentage(double maxPoints, double points) {
        double result = points / maxPoints * 100;
        return result;
    }
    public double getCompletedJavaPercentage() {
        return completedJavaPercentage;
    }

    public void setCompletedJavaPercentage(int java) {
        this.completedJavaPercentage = getPercentage(javaToCompletePoints, java);
    }

    public double getCompletedDSAPercentage() {
        return completedDSAPercentage;
    }

    public void setCompletedDSAPercentage(int dsa) {
        this.completedDSAPercentage = getPercentage(dsaToCompletePoints, dsa);;
    }

    public double getCompletedDatabasesPercentage() {
        return completedDatabasesPercentage;
    }

    public void setCompletedDatabasesPercentage(int databases) {
        this.completedDatabasesPercentage = getPercentage(databasesToCompletePoints, databases);
    }
    public double getCompletedSpringPercentage() {
        return completedSpringPercentage;
    }
    public void setCompletedSpringPercentage(int spring) {
        this.completedSpringPercentage = getPercentage(springToCompletePoints, spring);
    }

//    public double getCompletedJavaPercentage() {
//        return completedJavaPercentage;
//    }
//
//    public void setCompletedJavaPercentage(double completedJavaPercentage) {
//        this.completedJavaPercentage = completedJavaPercentage;
//    }
//
//    public double getCompletedDSAPercentage() {
//        return completedDSAPercentage;
//    }
//
//    public void setCompletedDSAPercentage(double completedDSAPercentage) {
//        this.completedDSAPercentage = completedDSAPercentage;
//    }
//
//    public double getCompletedDatabasesPercentage() {
//        return completedDatabasesPercentage;
//    }
//
//    public void setCompletedDatabasesPercentage(double completedDatabasesPercentage) {
//        this.completedDatabasesPercentage = completedDatabasesPercentage;
//    }
//    public double getCompletedSpringPercentage() {
//        return completedSpringPercentage;
//    }
//    public void setCompletedSpringPercentage(double completedSpringPercentage) {
//        this.completedSpringPercentage = completedSpringPercentage;
//    }
}
