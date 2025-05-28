import java.util.ArrayList;
import java.util.Scanner;

class Job {
    int id;
    String title;
    String company;
    String location;

    public Job(int id, String title, String company, String location) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
    }

    public void displayJob() {
        System.out.println("[" + id + "] " + title + " at " + company + " - " + location);
    }
}

class User {
    String username;
    String password;
    ArrayList<Job> appliedJobs = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void applyToJob(Job job) {
        appliedJobs.add(job);
        System.out.println("✅ Applied to: " + job.title);
    }

    public void showAppliedJobs() {
        if (appliedJobs.isEmpty()) {
            System.out.println("You have not applied to any jobs yet.");
        } else {
            System.out.println("📌 Applied Jobs:");
            for (Job job : appliedJobs) {
                job.displayJob();
            }
        }
    }
}

public class JobPortalApp {
    static ArrayList<Job> jobs = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        populateJobs();

        while (true) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                showUserMenu();
            }
        }
    }

    static void populateJobs() {
        jobs.add(new Job(1, "Software Engineer", "Google", "Bangalore"));
        jobs.add(new Job(2, "Java Developer", "TCS", "Chennai"));
        jobs.add(new Job(3, "Web Developer", "Infosys", "Hyderabad"));
        jobs.add(new Job(4, "Data Analyst", "Wipro", "Pune"));
    }

    static void showMainMenu() {
        System.out.println("\n=== Job Portal ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> {
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    static void register() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        users.add(new User(username, password));
        System.out.println("✅ Registered successfully! Please login.");
    }

    static void login() {
        System.out.print("Enter username: ");
        String uname = scanner.nextLine();
        System.out.print("Enter password: ");
        String pwd = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(uname) && user.password.equals(pwd)) {
                currentUser = user;
                System.out.println("✅ Login successful. Welcome, " + uname + "!");
                return;
            }
        }
        System.out.println("❌ Invalid credentials.");
    }

    static void showUserMenu() {
        System.out.println("\n=== Job Seeker Menu ===");
        System.out.println("1. View Available Jobs");
        System.out.println("2. Apply to a Job");
        System.out.println("3. View Applied Jobs");
        System.out.println("4. Logout");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> displayJobs();
            case 2 -> applyToJob();
            case 3 -> currentUser.showAppliedJobs();
            case 4 -> {
                currentUser = null;
                System.out.println("Logged out.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    static void displayJobs() {
        System.out.println("=== Available Jobs ===");
        for (Job job : jobs) {
            job.displayJob();
        }
    }

    static void applyToJob() {
        displayJobs();
        System.out.print("Enter Job ID to apply: ");
        int id = scanner.nextInt();

        for (Job job : jobs) {
            if (job.id == id) {
                currentUser.applyToJob(job);
                return;
            }
        }
        System.out.println("❌ Invalid Job ID.");
    }
}