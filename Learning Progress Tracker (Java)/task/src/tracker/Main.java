//package tracker;
//
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Learning Progress Tracker");
//        List<Student> students = new ArrayList<>();
//        boolean isAddStudentsCommand = false;
//        while (true) {
//            String command = scanner.nextLine().trim();
//            if (command.isBlank()) {
//                System.out.println("No Input");
//            } else if (command.equalsIgnoreCase("exit")) {
//                System.out.println("Bye!");
//                break;
//            } else if (command.equalsIgnoreCase("add students")) {
//                handleAddStudentsCommand(scanner, students);
//            } else if (command.equalsIgnoreCase("list")) {
//                printListOfIds(students, scanner, isAddStudentsCommand);
//            } else if (command.equalsIgnoreCase("add points")) {
//                addPoints(scanner, students);
//            } else if (command.equalsIgnoreCase("find")) {
//                find(students, scanner);
//            }
//
//            else if (command.equalsIgnoreCase("back")) {
//                handleBackCommand(isAddStudentsCommand,students);
//            } else {
//                System.out.println("Unknown command!");
//            }
//        }
//    }
//    private static void handleAddStudentsCommand(Scanner scanner, List<Student> students) {
//        System.out.println("Enter student credentials or 'back' to return:");
//
//        while (true) {
//            boolean isLengthValid = true;
//            String firstName, lastName, email;
//            String[] studentsData = scanner.nextLine().trim().split(" ");
//            if (studentsData.length == 1 && studentsData[0].equalsIgnoreCase("back")) {
//                handleBackCommand(true, students);
//                break;
//            }
//            if (studentsData.length < 3) {
//                isLengthValid = false;
//                System.out.println("Incorrect credentials.");
//            }
//            firstName = studentsData[0];
//            lastName = buildLastName(studentsData);
//            email = studentsData[studentsData.length - 1];
//            String namePattern = "^[a-zA-Z]+(?:['-][a-zA-Z]+)*$";
//            String lastnamePattern = "^[a-zA-Z]+(?:[' -][a-zA-Z]+)*$";
//            String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{1,}$";
//            if (isEmailAlreadyExists(email,students)) {
//                System.out.println("This email is already taken.");
//            }
//            boolean isFirstnameValid = validateName(firstName, namePattern, "first", isLengthValid);
//            boolean isLastnameValid = validateName(lastName, lastnamePattern, "last", isLengthValid);
//            boolean isEmailValid = validateEmail(email, emailPattern, isLengthValid);
//            if (!isEmailAlreadyExists(email,students) && isLengthValid && isFirstnameValid && isLastnameValid && isEmailValid) {
//                Student student = new Student(firstName, lastName, email);
//                students.add(student);
//                System.out.println("The student has been added.");
//            }
//        }
//    }
//
//    private static String buildLastName(String[] studentsData) {
//        StringBuilder lastNameBuilder = new StringBuilder();
//        for (int i = 1; i < studentsData.length - 1; i++) {
//            lastNameBuilder.append(" ").append(studentsData[i]);
//        }
//        return lastNameBuilder.toString().trim();
//    }
//
//    private static void handleBackCommand(boolean isAddStudentsCommand, List<Student> students) {
//        if (isAddStudentsCommand) {
//            System.out.println("Total " + students.size() + " students have been added.");
//        } else {
//            System.out.println("Enter 'exit' to exit the program");
//        }
//    }
//
//    private static boolean validateName(String name, String pattern, String firstOrLast, boolean isLengthValid) {
//        boolean isValid = name.matches(pattern) && name.length() > 1;
//        if (!isValid && isLengthValid) {
//            System.out.println("Incorrect " + firstOrLast +  " name." );
//        }
//        return isValid;
//    }
//
//    private static boolean validateEmail(String email, String pattern, boolean isLengthValid) {
//        boolean isValid = email.matches(pattern) && email.length() > 3;
//        if (!isValid && isLengthValid) {
//            System.out.println("Incorrect email." );
//        }
//        return isValid;
//    }
//
//    private static boolean isEmailAlreadyExists(String email, List<Student> students) {
//        for (Student student : students) {
//            if (student.getEmail().equalsIgnoreCase(email)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static void printListOfIds(List<Student> students, Scanner scanner, boolean isAddStudentsCommand) {
//        if (students.isEmpty()) {
//            System.out.println("No students found");
//        } else {
//            System.out.println("Students:");
//            for (Student student : students) {
//                System.out.println(student.getStudentId());
//            }
//        }
//        while (scanner.hasNextLine()) {
//            String command = scanner.nextLine();
//            if (command.isBlank()) {
//                System.out.println("No Input");
//            } else
//            if (command.equalsIgnoreCase("exit")) {
//                System.out.println("Bye!");
//                break;
//            } else if (command.equalsIgnoreCase("add students")) {
//                handleAddStudentsCommand(scanner, students);
//            }  else if (command.equalsIgnoreCase("add points")) {
//
//                addPoints(scanner, students);
//            } else if (command.equalsIgnoreCase("find")) {
//                find(students, scanner);
//            }
//
//            else if (command.equalsIgnoreCase("back")) {
//                handleBackCommand(isAddStudentsCommand,students);
//            } else {
//                System.out.println("Unknown command!");
//            }
//        }
//    }
//
//
//
//private static void addPoints(Scanner scanner, List<Student> students) {
//    System.out.println("Enter an id and points or 'back' to return:");
//    while (true) {
//
//        String[] pointsWithIdArray = scanner.nextLine().split(" ");
//        if (pointsWithIdArray.length == 1 && pointsWithIdArray[0].equalsIgnoreCase("back")) {
//            handleBackCommand(true, students);
//            break;
//        }
//        if (pointsWithIdArray.length != 5) {
//            System.out.println("Incorrect points format");
//        }
//
//        int studentId = 0;
//        int point1;
//        int point2;
//        int point3;
//        int point4;
//        try {
//            studentId = Integer.parseInt(pointsWithIdArray[0]);
//            point1 = Integer.parseInt(pointsWithIdArray[1]);
//            point2 = Integer.parseInt(pointsWithIdArray[2]);
//            point3 = Integer.parseInt(pointsWithIdArray[3]);
//            point4 = Integer.parseInt(pointsWithIdArray[4]);
//        } catch (Exception e) {
//            point1 = point2 = point3 = point4 = -1;
//        }
//
//        if (pointsWithIdArray.length == 5  && !isStudentIdValid(students, studentId)) {
//            System.out.println("No student is found for id=" + pointsWithIdArray[0]);
//        }
//        if (isStudentIdValid(students, studentId) && pointsWithIdArray.length == 5  && !isPointValid(point1, point2, point3, point4)) {
//            System.out.println("Incorrect points format");
//        }
//        if (pointsWithIdArray.length == 5  && isStudentIdValid(students, studentId) && isPointValid(point1, point2, point3, point4)) {
//            Student student = getStudentById(students, studentId);
//            point1 += student.getJava();
//            point2 += student.getDSA();
//            point3 += student.getDatabases();
//            point4 += student.getSpring();
//            student.setJava(point1);
//            student.setDSA(point2);
//            student.setDatabases(point3);
//            student.setSpring(point4);
//            System.out.println("Points updated");
//        }
//    }
//
//}
//
//
//    private static boolean isStudentIdValid(List<Student> students, int studentId) {
//        for (Student student : students) {
//            if (student.getStudentId() == studentId) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean isPointValid(int point1, int point2, int point3, int point4) {
//        return point1 >= 0 && point2 >= 0 && point3 >= 0 && point4 >= 0;
//    }
//
//    private static void find(List<Student> students, Scanner scanner) {
//        System.out.println("Enter an id or 'back' to return");
//        while (scanner.hasNextInt()) {
//            int id = scanner.nextInt();
//            if (!isStudentIdValid(students, id)) {
//                System.out.println("No student is found for id= " + id);
//            } else {
//                Student student = getStudentById(students, id);
//                System.out.println(id + " points: " + " Java=" + student.getJava() + " DSA=" + student.getDSA() + " Databases=" + student.getDatabases() + " Spring=" + student.getSpring());
//            }
//
//        }
//        if (!scanner.hasNextInt()) {
//            String command = scanner.nextLine();
//            if (command.equalsIgnoreCase("back")) {
//                handleBackCommand(true, students);
//            }
//        }
//
//    }
//    private static Student getStudentById(List<Student> students, int id) {
//        for (Student student : students) {
//            if (student.getStudentId() == id) {
//                return student;
//            }
//        }
//        return new Student();
//    }
//
//
//}
//
//
package tracker;
import java.util.*;

public class Main {

    private static final String COMMAND_EXIT = "exit";
    private static final String COMMAND_ADD_STUDENTS = "add students";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_ADD_POINTS = "add points";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_BACK = "back";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        List<Student> students = new ArrayList<>();
        boolean isAddingStudentsMode = false;

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
                printListOfIds(students, scanner, isAddingStudentsMode);
            } else if (command.equalsIgnoreCase(COMMAND_ADD_POINTS)) {
                addPoints(scanner, students);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                find(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_BACK)) {
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

    private static void printListOfIds(List<Student> students, Scanner scanner, boolean isAddingStudentsMode) {
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
                addPoints(scanner, students);
            } else if (command.equalsIgnoreCase(COMMAND_FIND)) {
                find(students, scanner);
            } else if (command.equalsIgnoreCase(COMMAND_BACK)) {
                handleBackCommand(isAddingStudentsMode, students);
            } else {
                System.out.println("Unknown command!");
            }
        }
    }

    private static void addPoints(Scanner scanner, List<Student> students) {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String[] pointsWithIdArray = scanner.nextLine().split(" ");

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
}


// Rest of the code follows with the suggested improvements...

