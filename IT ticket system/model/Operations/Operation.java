package Operations;

import Repository.TechnicianRepository;
import Repository.TicketRepository;
import Services.TechnicianService;
import Services.TicketService;
import Model.Ticket;
import Model.Technician;

import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Operation {
    /* 
    int ticketId = 0;
    String clientName;
    int floor;
    String department;
    String division;
    String problem;
    */

    private TicketService service;
    private TicketRepository ticketRepo;
    private Technician technician;
    private TechnicianService tech;
    private TechnicianRepository technicianRepo;

    private Scanner scanner = new Scanner(System.in);

    // Operations constructor
    public Operation
    (
            TicketService service,
            TechnicianService tech,
            TicketRepository ticketRepo,
            TechnicianRepository technicianRepo
    ) {
        this.service = service;
        this.tech = tech;
        this.ticketRepo = ticketRepo;
        this.technicianRepo = technicianRepo;
    }

    // ============================== TICKET MENU  ==============================
    public void ticketMenu() {
        while (true) {
            System.out.println("1 - Log a ticket");
            System.out.println("2 - View tickets");
            System.out.println("3 - Update Status");
            System.out.println("4 - Assign Ticket");
            System.out.println("5 - Exit\n");

            int ans = scanner.nextInt();
            scanner.nextLine();

            /*
            if (ans == 1) {
                System.out.println(" Feature coming soon");

            } else if (ans == 2) {
                logTicket();
                break;
            } else if (ans == 3) {
                System.out.println("Feature coming soon");
            }
            */

            switch (ans) {
                case 1 -> logTicket();
                case 2 -> service.viewTickets(); // Import method from ticket service
                case 3 -> updateStatus();
                case 4 -> assignTicket();
                case 5 -> Exit();
            }
        }
    }

    // ============================== LOG TICKET ==============================
    public void logTicket() {
        System.out.println("Log a ticket: (Y/N)");
        String ans = scanner.nextLine().toUpperCase();

        if (ans.equalsIgnoreCase("Y")) {
            createTicket();
        } else if (ans.equalsIgnoreCase("N")) {
            Exit();
        }
    }

    // ============================== CREATE TICKET ==============================
    public void createTicket() {
        // prompt client name
        System.out.print("Clients name: ");
        String name = scanner.nextLine();

        // Prompt floor level and save as a variable
        int floor = floorLevel();

        // prompt department method and save as a variable
        String department = department();

        // prompt department method and save as a variable
        String division = division();

        // prompt department method and save as a variable
        String problemDesc = problem();
    }

    // ============================== FLOOR ==============================
    public int floorLevel() {
        while (true) {
            System.out.println("Which floor level are you from: ");
            System.out.println("1) 1st floor");
            System.out.println("2) 2nd floor");
            System.out.println("3) 3rd floor");
            System.out.println("4) 4th floor");
            System.out.println("5) 5th floor");
            System.out.println("6) 6th floor");

            int ans = scanner.nextInt();
            scanner.nextLine();

            switch (ans) {
                case 1 -> {
                    return 1;
                }
                case 2 -> {
                    return 2;
                }
                case 3 -> {
                    return 3;
                }
                case 4 -> {
                    return 4;
                }
                case 5 -> {
                    return 5;
                }
                case 6 -> {
                    return 6;
                }
                default -> {
                    System.out.println("Invalid input, try again.\n");
                }
            }
        }
    }

    // ============================== DEPARTMENT ==============================
    public String department() {
        while (true) {
            System.out.println("Which department is there the issue : ");
            System.out.println("1) Network");
            System.out.println("2) Hardware");
            System.out.println("3) Software");
            System.out.println("4) other");
            int ans = scanner.nextInt();
            scanner.nextLine();

            String department;
            switch (ans) {
                case 1 -> department = "Network";
                case 2 -> department = "Hardware";
                case 3 -> department = "Software";

                case 4 -> {
                    System.out.println("Which departmental is your problem within: ");
                    department = scanner.nextLine();
                }
                default -> {
                    System.out.println("Invalid input, input again\n");
                    continue;
                }
            }
            return department;
        }
    }

    // ============================== DIVISION ==============================
    public String division() {
        while (true) {
            System.out.println("Which department are you from ?");
            System.out.println("1) Call center");
            System.out.println("2) Lecturer");
            System.out.println("3) Receptionist");
            System.out.println("4) Student");
            System.out.println("5) other");

            int ans = scanner.nextInt();
            scanner.nextLine();

            String division;
            switch (ans) {
                case 1 -> division = " Call center";
                case 2 -> division = " Lecturer";
                case 3 -> division = " Receptionist";
                case 4 -> division = " Student";

                case 5 -> {
                    System.out.println("Which division is your problem within: ");
                    division = scanner.nextLine();
                }

                default -> {
                    System.out.println("Invalid input, input again\n");
                    continue;
                }
            }
            return division;
        }
    }

    // ============================== PROBLEM ==============================
    public String problem() {
        System.out.println("Whats your problem: ");
        return scanner.nextLine();
    }

    // ============================== UPDATE STATUS  ==============================
    public void updateStatus() {
        // Check if there are available tickets
        if (ticketRepo.getAllTickets().isEmpty()) {
            System.out.println("No tickets have been created yet\n");
            return;
        }

        // Prompt user to get ticket ID
        int id = promptTicketID();

        //Use ID to updateStatusToComplete   
        service.updateStatusToComplete(id);
    }

    // ============================== ASSIGN TICKET ==============================
    public void assignTicket() {
        // If ticket repo is empty, return to start method
        if (ticketRepo.getAllTickets().isEmpty()) {
            System.out.print("No tickets to assign\n\n");
            return;
        }

        // Prompt user to get ticket ID
        int id = promptTicketID();

        // Retrieve ticket using ID
        Ticket ticket = ticketRepo.getTicketById(id);

        // Check if there is ticketID that matches one entered if not alert
        if (ticket == null) {
            System.out.println("No tickets found\n");
            return;
        }

        // Display all technicians
        tech.viewTechnicians();

        // Prompt for technician ID
        System.out.println("Enter Technician ID:");
        int techId = scanner.nextInt();
        scanner.nextLine();

        // Create a technician assigned to the task variable
        Technician technician = technicianRepo.getTechniciansById(techId);

        // If technician assigned is not found alert user
        if (technician == null) {
            System.out.println("Technician not found!\n");
            return;
        }

        // Assign ticket to technician
        ticket.setAssignedTo(technician.getTechnicianName());

        // Update technician status using ID
        technician.setTechnicianStatusToOccupied();

        System.out.println("Ticket assigned successfully!\n");
    }

    // ============================== TICKET ID  ==============================
    public int promptTicketID() {
        System.out.print("Ticket ID: ");
        int ID = scanner.nextInt();
        scanner.nextLine();
        return ID;
    }

    // ============================== CREATE TICKET  ==============================
    public void compileTicket(String name, int floor, String department, String division, String problemDesc)
    // Use the variables/ returns of the createTicke methods as parameters to compiling ticket/constructing ticket
    {
        service.createTicket(
                floor,
                name,
                division,
                department,
                problemDesc
        );
        System.out.println("Ticket created successfully.\n");
    }

    // ============================== START ==============================
    public void start() {
        while (true) {
            System.out.println("1 - Tickets menu");
            System.out.println("2 - Technicians menu");
            System.out.println("3 - Exit\n");

            int ans = scanner.nextInt();

            switch (ans) {
                case 1 -> ticketMenu();
                case 2 -> technicianMenu();
                case 3 -> Exit();
                default -> {
                    System.out.println("Invalid input, input again\n");
                    continue;
                }
            }
        }

    }

    // ============================== TECHNICIAN MENU ==============================
    public void technicianMenu()
    {
        while (true)
        {
            System.out.println("1 - Add technician");
            System.out.println("2 - Search for technician");
            System.out.println("3 - View unassigned technicians");
            System.out.println("4 - View all technicians");

            int ans = scanner.nextInt();
            scanner.nextLine();

            switch (ans) {
                case 1 -> addTechnician();
                case 2 -> searchForTechnician();
//                case 3 ->  for all technicians, print those with technician status set to "unassigned"
//                case 4 -> // for all technicians , print all
                default -> {
                    System.out.println("Invalid input, input again\n");
                    continue;
                }
            }
        }
    }

    // ============================== TECHNICIAN NAME ==============================
    public String addTechnicianName() {
        System.out.println("Enter the technicians name: ");
        return scanner.nextLine();
    }

    // ============================== TECHNICIAN CONTACT NR ==============================
    public String addTechnicianContactNr() {
        // The contact number variable structure, how it should be
        String contactNrRegex = "^0\\d{9}$";
        String contactNr;

        // A do while loop to prompt for contact number first then put it in a while loop incase it doesnt match the regex
        do {
            System.out.print("Enter contact number: ");
            contactNr = scanner.nextLine();

            // If contact number doesnt match the regex structure alert user that its invalid phone number
            if (!contactNr.matches(contactNrRegex)) {
                System.out.println("Invalid contact number. Please enter a 10-digit number starting with 0.");
            }

        }
        while (!contactNr.matches(contactNrRegex));

        System.out.println("Valid number entered: " + contactNr);

        return contactNr;
    }

    // ============================== ADD TECHNICIAN ==============================
    public void addTechnician() {
        // Save the return of the method as variable
        String techName = addTechnicianName();
        String contactNr = addTechnicianContactNr();

        String technicianStatus = "Unassigned";
    }

    // ============================== ADD TECHNICIAN ==============================
    public void Technician(String technicianName, String technicianStatus, String contactNr)
    // Use the variables/ returns of the addTechnician methods as parameters to compiling technician/constructing ticket
    {
        tech.createTechnician(
                technicianName,
                technicianStatus,
                contactNr
        );
        System.out.println("Technician added successfully.\n");
    }

    // ============================== Exit ==============================
    public void Exit() {
        System.out.println("Thanks for using our app\n");
        System.exit(0);
    }

    // ============================== SEARCH FOR TECHNICIAN ==============================
    public void searchForTechnician()
    {
        System.out.print("Search for technician: ");
        String search = scanner.nextLine().trim().toLowerCase();

        // Returns all the technicians in the Array
        List<Technician> techName = technicianRepo.getAllTechnicians()
             // Turn the Array list to stream
            .stream()
             // This filters the array list according to the "search" user input.
            .filter(t -> technician.getTechnicianName()
                    .toLowerCase()
                    .contains(search))
            // Returns the stream list back to array list
            .toList();

        // If no technicians found by the user search input, alert user
        if(techName.isEmpty())
        {
            System.out.println("No technicians found\n");
        }
        else
        {
            // Lamda for printing the technicians found by user search input
            techName.forEach(System.out::println);
        }
    }

    // ============================== DISPLAY UNASSIGNED TECHNICIAN ==============================
    public void displayUnassignedTechnician()
    {
        // Set the return of this code to the variable
        List<Technician> unassignedTechnicians =
                // Return access he Array list of technicians
                technicianRepo.getAllTechnicians()
                        // Convert the Array to a stream
                        .stream()
                        // Filter the Stream for "Unassigned" technicians, return them and convert back to an Array list
                        .filter(t -> t.getTechnicianStatus().equals("Unassigned")).toList();

        if (unassignedTechnicians.isEmpty())
        {
            System.out.println("No technicians found\n");
        }
        else
        {
            // Print every returned technician
            unassignedTechnicians.forEach(System.out::println);
        }
    }
}