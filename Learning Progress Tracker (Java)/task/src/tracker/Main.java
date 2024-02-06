package tracker;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {
    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_ADD_STUDENTS = "add students";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD_POINTS = "add points";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_BACK = "back";
    private static final String COMMAND_STATISTICS = "statistics";
    private static final String COMMAND_JAVA = "java";
    private static final String COMMAND_DSA = "dsa";
    private static final String COMMAND_DATABASES = "databases";
    private static final String COMMAND_SPRING = "spring";
    private static final String COMMAND_NOTIFY = "notify";



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        List<Student> students = new ArrayList<>();
        boolean isAddingStudentsMode = false;
        Set<String> notifiedUsers = new HashSet<>();

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.isBlank()) {
                System.out.println("No Input");
            } else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                System.out.println("Bye!");
                break;
            } else if (command.equalsIgnoreCase(COMMAND_ADD_STUDENTS)) {
                handleAddStudentsCommand(scanner, students);
            } else if (command.equalsIgnoreCase(COMMAND_LIST)) {
                printListOfIds(students, scanner, isAddingStudentsMode,notifiedUsers);
            } else if (command.equalsIgnoreCase(COMMAND_ADD_POINTS)) {
                addPoints(scanner, students, notifiedUsers);
            } else if (command.equalsIgnoreCase(COMMAND_STATISTICS)) {
                getStatistics(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                find(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_NOTIFY)) {
                getNotified(students, notifiedUsers, scanner);
            }
            else if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(isAddingStudentsMode, students);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    private static void handleAddStudentsCommand(Scanner scanner, List<Student> students) {
        System.out.println("Enter student credentials or 'back' to return:");

        while (true) {
            String firstName, lastName, email;
            String[] studentsData = scanner.nextLine().trim().split(" ");
            if (studentsData.length == 1 && studentsData[0].equalsIgnoreCase(COMMAND_STATISTICS)) {
                System.out.println("ENTRY STATISTICS");
                getStatistics(students, scanner);
            } else
            if (studentsData.length == 1 && studentsData[0].equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(true, students);
                break;
            }
            if (studentsData.length < 3) {
                System.out.println("Incorrect credentials.");
                continue;
            }
            firstName = studentsData[0];
            lastName = buildLastName(studentsData);
            email = studentsData[studentsData.length - 1];
            String namePattern = "^[a-zA-Z]+(?:['-][a-zA-Z]+)*$";
            String lastnamePattern = "^[a-zA-Z]+(?:[' -][a-zA-Z]+)*$";
            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{1,}$";
            if (isEmailAlreadyExists(email, students)) {
                System.out.println("This email is already taken.");
                continue;
            }
            boolean isFirstnameValid = validateName(firstName, namePattern, "first");
            boolean isLastnameValid = validateName(lastName, lastnamePattern, "last");
            boolean isEmailValid = validateEmail(email, emailPattern);
            if (isFirstnameValid && isLastnameValid && isEmailValid) {
                Student student = new Student(firstName, lastName, email);
                students.add(student);
                System.out.println("The student has been added.");
            }
        }
    }
    private static String buildLastName(String[] studentsData) {
        StringBuilder lastNameBuilder = new StringBuilder();
        for (int i = 1; i < studentsData.length - 1; i++) {
            lastNameBuilder.append(" ").append(studentsData[i]);
        }
        return lastNameBuilder.toString().trim();
    }
    private static void handleBackCommand(boolean isAddingStudentsMode, List<Student> students) {
        if (isAddingStudentsMode) {
            System.out.println("Total " + students.size() + " students have been added.");
        } else {
            System.out.println("Enter 'exit' to exit the program");
        }
    }
    private static boolean validateName(String name, String pattern, String firstOrLast) {
        boolean isValid = name.matches(pattern) && name.length() > 1;
        if (!isValid) {
            System.out.println("Incorrect " + firstOrLast + " name.");
        }
        return isValid;
    }
    private static boolean validateEmail(String email, String pattern) {
        boolean isValid = email.matches(pattern) && email.length() > 3;
        if (!isValid) {
            System.out.println("Incorrect email.");
        }
        return isValid;
    }
    private static boolean isEmailAlreadyExists(String email, List<Student> students) {
        return students.stream().anyMatch(student -> student.getEmail().equalsIgnoreCase(email));
    }
    private static void printListOfIds(List<Student> students, Scanner scanner, boolean isAddingStudentsMode, Set<String> notifiedUsers) {
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            students.forEach(student -> System.out.println(student.getStudentId()));
        }

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.isBlank()) {
                System.out.println("No Input");
            } else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                System.out.println("Bye!");
                break;
            } else if (command.equalsIgnoreCase(COMMAND_ADD_STUDENTS)) {
                handleAddStudentsCommand(scanner, students);
            } else if (command.equalsIgnoreCase(COMMAND_ADD_POINTS)) {
                addPoints(scanner, students, notifiedUsers);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                find(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(isAddingStudentsMode, students);
            } else if (command.equalsIgnoreCase(COMMAND_STATISTICS)) {
                getStatistics(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_NOTIFY)) {
                getNotified(students,notifiedUsers, scanner);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }
    private static void addPoints(Scanner scanner, List<Student> students, Set<String> notifiedUsers) {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String[] pointsWithIdArray = scanner.nextLine().split(" ");
            if (pointsWithIdArray.length == 1 && pointsWithIdArray[0].equalsIgnoreCase(COMMAND_STATISTICS)) {
                getStatistics(students, scanner);
            } else
            if (pointsWithIdArray.length == 1 && pointsWithIdArray[0].equalsIgnoreCase(COMMAND_NOTIFY)) {
                getNotified(students,notifiedUsers, scanner);
            } else
            if (pointsWithIdArray.length == 1 && pointsWithIdArray[0].equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(true, students);
                break;
            }
            if (pointsWithIdArray.length != 5) {
                System.out.println("Incorrect points format");
                continue;
            }
            int  point1, point2, point3, point4;
            String studentId = pointsWithIdArray[0];
            try {
                point1 = Integer.parseInt(pointsWithIdArray[1]);
                point2 = Integer.parseInt(pointsWithIdArray[2]);
                point3 = Integer.parseInt(pointsWithIdArray[3]);
                point4 = Integer.parseInt(pointsWithIdArray[4]);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect points format");
                continue;
            }
            if (!isStudentIdValid(students, studentId)) {
                System.out.println("No student is found for id=" + pointsWithIdArray[0]);
            } else if (!isPointValid(point1, point2, point3, point4)) {
                System.out.println("Incorrect points format");
            } else {
                int id = Integer.parseInt(studentId);
                Student student = getStudentById(students, id);
                updateStudentPoints(student, point1, point2, point3, point4);
                System.out.println("Points updated");
            }
        }
    }

    private static void updateStudentPoints(Student student, int point1, int point2, int point3, int point4) {
        student.setJava(student.getJava() + point1);
        student.setDSA(student.getDSA() + point2);
        student.setDatabases(student.getDatabases() + point3);
        student.setSpring(student.getSpring() + point4);
        student.setCompletedDSAPercentage(student.getDSA());
        student.setCompletedDatabasesPercentage(student.getDatabases());
        student.setCompletedSpringPercentage(student.getSpring());
        student.setCompletedJavaPercentage(student.getJava());
    }

    private static boolean isStudentIdValid(List<Student> students, String studentId) {
        int id;
        try {
            id = Integer.parseInt(studentId);
        } catch (NumberFormatException e) {
            return false;
        }
        return students.stream().anyMatch(student -> student.getStudentId() == id);
    }
    private static boolean isStudentIdValid(List<Student> students, int studentId) {
        return students.stream().anyMatch(student -> student.getStudentId() == studentId);
    }

    private static boolean isPointValid(int point1, int point2, int point3, int point4) {
        return point1 >= 0 && point2 >= 0 && point3 >= 0 && point4 >= 0;
    }

    private static void find(List<Student> students, Scanner scanner) {
        System.out.println("Enter an id or 'back' to return");
        while (scanner.hasNextInt()) {
            int id = scanner.nextInt();
            if (!isStudentIdValid(students, id)) {
                System.out.println("No student is found for id= " + id);
            } else {
                Student student = getStudentById(students, id);
                System.out.println(id + " points: " + " Java=" + student.getJava() +
                        " DSA=" + student.getDSA() + " Databases=" + student.getDatabases() +
                        " Spring=" + student.getSpring());
            }
        }
        if (!scanner.hasNextInt()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(true, students);
            }
        }
    }

    private static Student getStudentById(List<Student> students, int id) {
        return students.stream().filter(student -> student.getStudentId() == id).findFirst().orElse(new Student());
    }

    private static void getStatistics(List<Student> students, Scanner scanner) {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        String mostPopular = mostTakenCourse(students);
        String leastPopular = leastTakenCourse(students);
        if (mostPopular.equalsIgnoreCase("Java, DSA, Databases, Spring")) {
            leastPopular = "n/a";
        }

        String highestActivity = mostPopular;
        String leastActivity = leastPopular;
        String easiestCourse = easiestCourse(students);
        String hardestCourse = hardestCourse(students);
        if (easiestCourse.equalsIgnoreCase("Java, DSA, Databases, Spring")) {
            hardestCourse = "n/a";
        }
        System.out.println("Most popular: " + mostPopular);
        System.out.println("Least popular: " + leastPopular);
        System.out.println("Highest activity: " + highestActivity);
        System.out.println("Lowest activity: " + leastActivity);
        System.out.println("Easiest course: " + easiestCourse);
        System.out.println("Hardest course: " + hardestCourse);
        while(true) {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(COMMAND_JAVA)) {
                handleCourseCommand(students, "java");
            } else if (command.equalsIgnoreCase(COMMAND_DSA)) {
                handleCourseCommand(students, "dsa");
            } else if (command.equalsIgnoreCase(COMMAND_DATABASES)) {
                handleCourseCommand(students, "databases");
            } else if (command.equalsIgnoreCase(COMMAND_SPRING)) {
                handleCourseCommand(students, "spring");
            } else if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(true, students);
                break;
            } else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Unknown course.");
            }
        }

    }

    private static void handleCourseCommand(List<Student> students, String course) {
        if (course.equalsIgnoreCase("dsa")) {
            sortAndPrintResults(students, "DSA");
        } else {
            if (course.equalsIgnoreCase("java")) {
                sortAndPrintResults(students, "Java");
            }
            if (course.equalsIgnoreCase("databases")) {
                sortAndPrintResults(students, "Databases");
            }
            if (course.equalsIgnoreCase("spring")) {
                sortAndPrintResults(students, "Spring");
            }
        }
    }
    private static void sortAndPrintResults(List<Student> students, String course) {
        Comparator<Student> comparator = Comparator
                .<Student, Integer>comparing(student -> getPointsForCourse(student, course))
                .reversed()
                .thenComparingInt(Student::getStudentId);

        System.out.println(course);
        System.out.println("id points completed");

        // Sort the list using the provided comparator
        students.sort(comparator);

        // Print the results for the current course
        for (Student student : students) {
            int id = student.getStudentId();
            int points = getPointsForCourse(student, course);
            String completedPercentage = getCompletedPercentageForCourse(student, course);
            if (points > 0) {
                System.out.println(id + " " + points + " " + completedPercentage);
            }

        }
    }
    private static int getPointsForCourse(Student student, String course) {
        return switch (course) {
            case "Java" -> student.getJava();
            case "DSA" -> student.getDSA();
            case "Databases" -> student.getDatabases();
            case "Spring" -> student.getSpring();
            default -> throw new IllegalArgumentException("Unknown course: " + course);
        };
    }
    private static String getCompletedPercentageForCourse(Student student, String course) {
        String javaPercentageInRounded = getNumberInRounding(student.getCompletedJavaPercentage()) + "%";
        String databasePercentageInRounded = getNumberInRounding(student.getCompletedDatabasesPercentage()) + "%";
        String dsaPercentageInRounded = getNumberInRounding(student.getCompletedDSAPercentage()) + "%";
        String springPercentageInRounded = getNumberInRounding(student.getCompletedSpringPercentage()) + "%";
        return switch (course) {
            case "Java" -> javaPercentageInRounded;
            case "DSA" -> dsaPercentageInRounded;
            case "Databases" -> databasePercentageInRounded;
            case "Spring" -> springPercentageInRounded;
            default -> throw new IllegalArgumentException("Unknown course: " + course);
        };
    }

    private static String leastTakenCourse(List<Student> students) {
        int javaCount = 0;
        int dsaCount = 0;
        int databasesCount = 0;
        int springCount = 0;

        for (Student student : students) {
            javaCount += student.getJava() > 0 ? 1 : 0;
            dsaCount += student.getDSA() > 0 ? 1 : 0;
            databasesCount += student.getDatabases() > 0 ? 1 : 0;
            springCount += student.getSpring() > 0 ? 1 : 0;
        }

        int minCount = Math.min(javaCount, Math.min(dsaCount, Math.min(databasesCount, springCount)));
        String result = "";
        if (minCount == 0) {
            return "n/a";
        } else {
            if (javaCount == minCount) {
                result = "Java";
            }
            if (dsaCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }

    private static String mostTakenCourse(List<Student> students) {
        int javaCount = 0;
        int dsaCount = 0;
        int databasesCount = 0;
        int springCount = 0;

        for (Student student : students) {
            javaCount += student.getJava() > 0 ? 1 : 0;
            dsaCount += student.getDSA() > 0 ? 1 : 0;
            databasesCount += student.getDatabases() > 0 ? 1 : 0;
            springCount += student.getSpring() > 0 ? 1 : 0;
        }
        int maxCount = Math.max(javaCount, Math.max(dsaCount, Math.max(databasesCount, springCount)));

        String result = "";
        if (maxCount == 0) {
            return "n/a";
        } else {
            if (javaCount == maxCount) {
                result = "Java";
            }
            if (dsaCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }

    private static String highestActivityCourse(List<Student> students) {
        double javaCount = 0;
        double dsaCount = 0;
        double databasesCount = 0;
        double springCount = 0;

        for (Student student : students) {
            javaCount += student.getCompletedJavaPercentage();
            dsaCount += student.getCompletedDSAPercentage();
            databasesCount += student.getCompletedDatabasesPercentage();
            springCount += student.getCompletedSpringPercentage();
        }
        double maxCount = Math.max(javaCount, Math.max(dsaCount, Math.max(databasesCount, springCount)));
        String result = "";
        if (maxCount == 0) {
            return "n/a";
        } else {
            if (javaCount == maxCount) {
                result = "Java";
            }
            if (dsaCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }

    private static String leastActivityCourse(List<Student> students) {
        double javaCount = 0;
        double dsaCount = 0;
        double databasesCount = 0;
        double springCount = 0;

        for (Student student : students) {
            javaCount += student.getCompletedJavaPercentage();
            dsaCount += student.getCompletedDSAPercentage();
            databasesCount += student.getCompletedDatabasesPercentage();
            springCount += student.getCompletedSpringPercentage();
        }

        double minCount = Math.min(javaCount, Math.min(dsaCount, Math.min(databasesCount, springCount)));
        String result = "";
        if (minCount == 0) {
            return "n/a";
        } else {
            if (javaCount == minCount) {
                result = "Java";
            }
            if (dsaCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }

    private static String easiestCourse(List<Student> students) {
        int javaCount = 0;
        int dsaCount = 0;
        int databasesCount = 0;
        int springCount = 0;

        for (Student student : students) {
            javaCount += student.getJava();
            dsaCount += student.getDSA();
            databasesCount += student.getDatabases();
            springCount += student.getSpring();
        }

        int  maxCount = Math.max(javaCount, Math.max(dsaCount, Math.max(databasesCount, springCount)));
        String result = "";
        if (mostTakenCourse(students).equalsIgnoreCase("n/a")) {
            return "n/a";
        } else {
            if (javaCount == maxCount) {
                result = "Java";
            }
            if (dsaCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == maxCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }

    private static String hardestCourse(List<Student> students) {
        int javaCount = 0;
        int dsaCount = 0;
        int databasesCount = 0;
        int springCount = 0;

        for (Student student : students) {
            javaCount += student.getJava();
            dsaCount += student.getDSA();
            databasesCount += student.getDatabases();
            springCount += student.getSpring();
        }

        int minCount = Math.min(javaCount, Math.min(dsaCount, Math.min(databasesCount, springCount)));
        String result = "";
        if (mostTakenCourse(students).equalsIgnoreCase("n/a")) {
            return "n/a";
        }  else {
            if (javaCount == minCount) {
                result = "Java";
            }
            if (dsaCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "DSA";
            }
            if (databasesCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Databases";
            }
            if (springCount == minCount) {
                if (!result.isEmpty()) {
                    result += ", ";
                }
                result += "Spring";
            }
        }
        return result;
    }
    private static double getNumberInRounding(double originalNumber) {
        // Using BigDecimal for precision
        BigDecimal decimalNumber = new BigDecimal(originalNumber);

        // Rounding with HALF_UP
        BigDecimal roundedNumber = decimalNumber.setScale(1, RoundingMode.HALF_UP);

        // Convert the rounded BigDecimal back to double if needed
        return roundedNumber.doubleValue();
    }

    private static void getNotified(List<Student> students, Set<String> notifiedUsers, Scanner scanner) {
        getNotificationsForUsers(students, notifiedUsers);
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase(COMMAND_NOTIFY)) {
                getNotificationsForUsers(students, notifiedUsers);
            } else if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(true, students);
                break;
            } else if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                System.out.println("Bye!");
                break;
            }
        }
    }
    private static void notifyUser(Student student, String course) {
        System.out.println("To: " + student.getEmail());
        System.out.println("Re: Your Learning Progress");
        System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName() + "! You have accomplished our " + course + " course!");
    }
    private static boolean isNotNotified(Set<String> notifiedUsers, String email) {
        return  !notifiedUsers.contains(email);
    }

    private static void getNotificationsForUsers(List<Student> students, Set<String> notifiedUsers) {
        int count = 0;
        for (Student student : students) {
            boolean isNotNotified = isNotNotified(notifiedUsers, student.getEmail());
            boolean isJavaCompleted = student.getCompletedJavaPercentage() == 100;
            boolean isDsaCompleted = student.getCompletedDSAPercentage() == 100;
            boolean isDatabaseCompleted = student.getCompletedDatabasesPercentage() == 100;
            boolean isSpringCompleted = student.getCompletedSpringPercentage() == 100;
            if (isNotNotified) {
                if (isJavaCompleted) {
                    notifyUser(student, "Java");
                    notifiedUsers.add(student.getEmail());
                    count++;
                }
                if (isDsaCompleted) {
                    notifyUser(student, "DSA");
                    notifiedUsers.add(student.getEmail());
                    if (!isJavaCompleted) {
                        count++;
                    }
                }
                if (isDatabaseCompleted) {
                    notifyUser(student, "Database");
                    notifiedUsers.add(student.getEmail());
                    if (!isDsaCompleted) {
                        count++;
                    }
                }
                if (isSpringCompleted) {
                    notifyUser(student, "Spring");
                    notifiedUsers.add(student.getEmail());
                    if (!isDatabaseCompleted) {
                        count++;
                    }
                }
            }
        }
        System.out.println("Total " + count + " students have been notified.");
    }
}


