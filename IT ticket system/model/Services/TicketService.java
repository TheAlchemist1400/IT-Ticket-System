package Services;

import Model.Ticket;
import Model.Technician;
import Repository.TicketRepository;
import Storage.TicketStorage;

public class TicketService
{
    // import class TicketRepository to use its methods, make it private so i cant make changes to it
    private TicketRepository repo;
    private TicketStorage storage;
    private int idCounter = 1;

    // Call Ticket repository after import
    public TicketService(TicketRepository repo) {this.repo = repo; this.storage = storage;}

    // ============================== CREATE TICKET ==============================
    public void createTicket(int floor, String clientName, String division, String department, String problemDesc) 
    {
        // Create ticket using the parameters
        Ticket ticket = new Ticket(idCounter++, floor, clientName, division, department, problemDesc);
        // Add the ticket to the repo
        repo.addTicket(ticket);
        // Save and update Ticket list data
        storage.saveTicket(repo.getAllTickets());
    }

    // ============================== VIEW TICKETS ==============================
    public void viewTickets() 
    {
        if (repo.getAllTickets().isEmpty())
        {
            System.out.println("No tickets have been created yet\n");
            return;
        }

        // for every ticket, print it out/ display it
        for (Ticket t : repo.getAllTickets()) 
        {
            System.out.println(t);
        }
    }

    // ============================== CHANGE STATUS TO COMPLETE ==============================
    public void updateStatusToComplete(int ticketId)
    {
        Ticket t = repo.getTicketById(ticketId);
        if (t != null)
        {
            t.setStatus("completed");
        }
        else
        {
            System.out.println("No ticket by this ID");
        }
    }
    // ============================== ASSIGN TICKET ==============================
    public void assignTicket(int ticketId, String technician)
    {
        Ticket t = repo.getTicketById(ticketId);

        if (t != null) 
        {
            t.setAssignedTo(technician);
        }
        else
        {
            System.out.println("No ticket by this ID");
        }
    }
}
