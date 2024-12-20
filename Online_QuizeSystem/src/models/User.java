package models;

public abstract class User {
    protected String username;
    protected String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public abstract void accessLevel();
}

class Admin extends User {
    public Admin(String username) {
        super(username, "Admin");
    }

    @Override
    public void accessLevel() {
        System.out.println("Admin has full access.");
    }
}

class Teacher extends User {
    public Teacher(String username) {
        super(username, "Teacher");
    }

    @Override
    public void accessLevel() {
        System.out.println("Teacher can create and manage quizzes.");
    }
}

class Student extends User {
    public Student(String username) {
        super(username, "Student");
    }

    @Override
    public void accessLevel() {
        System.out.println("Student can take quizzes and view results.");
    }
}

class UserRoleFactory {
    public static User createUser(String role, String username) {
        if (role.equalsIgnoreCase("Admin")) {
            return new Admin(username);
        } else if (role.equalsIgnoreCase("Teacher")) {
            return new Teacher(username);
        } else if (role.equalsIgnoreCase("Student")) {
            return new Student(username);
        }
        return null;
    }
}
