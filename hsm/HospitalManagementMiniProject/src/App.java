import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // display title
        System.out.println("The Hospital Management System");
        System.out.println("==============================");

        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n\nWelcome Back!");
            System.out.println("Are you a Patient or a Manager?");
            System.out.println("1. Patient");
            System.out.println("2. Manager");
            System.out.println("3. Exit\n");
            System.out.println("Enter your choice: ");
            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nHello Patient!\n");
                    System.out.println("1. New Patient Registration");
                    System.out.println("2. Book an Appointment");
                    System.out.println("3. Cancel an Appointment");
                    System.out.println("4. Exit\n");
                    System.out.println("Enter your choice: ");
                    int patient_choice = scan.nextInt();
                    switch (patient_choice) {
                        case 1:
                            System.out.println("\nNew Patient Registration\n");
                            PatientReg p = new PatientReg();
                            p.newPatientReg();
                            break;
                        case 2:
                            System.out.println("\nBook an Appointment\n");
                            AppointmentBooking a = new AppointmentBooking();
                            System.out.println(a.bookAppointment());
                            break;
                        case 3:
                            System.out.println("\nCancel an Appointment\n");
                            CancelAppointment c = new CancelAppointment();
                            System.out.println(c.cancelAppointment());
                            break;
                        case 4:
                            System.out.println("\nExiting...");
                            break;
                        default:
                            System.out.println("\nInvalid choice");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\nHello Manager!\n");
                    System.out.println("1. View all patients");
                    System.out.println("2. View all appointments");
                    System.out.println("3. View all patients and their appointments");
                    System.out.println("4. View all appointments on a date");
                    System.out.println("5. Exit");
                    System.out.println("\n");
                    System.out.println("Enter your choice: ");
                    int manager_choice = scan.nextInt();
                    switch (manager_choice) {
                        case 1:
                            System.out.println("View all patients");
                            ViewAllPatients v = new ViewAllPatients();
                            v.viewAllPatients();
                            break;
                        case 2:
                            System.out.println("View all appointments");
                            ViewAllAppointments a = new ViewAllAppointments();
                            a.viewAllAppointments();
                            break;
                        case 3:
                            System.out.println("View all patients with appointments");
                            ViewAllPatientsWithAppointments p = new ViewAllPatientsWithAppointments();
                            p.viewAllPatientsWithAppointments();
                            break;
                        case 4:
                            System.out.println("View all appointments on a date");
                            ViewAllAppointmentsOnDate d = new ViewAllAppointmentsOnDate();
                            d.viewAllAppointmentsOnDate();
                            break;
                        case 5:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("\nInvalid choice");
                    break;
            }
        } while (choice != 3);
        // scan.close();
    }
}
