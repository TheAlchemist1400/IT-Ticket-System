package Repository;

import Model.Ticket;
import java.util.ArrayList;

public class TicketRepository
{
    // Create an arraylist to store the tickets created
    private ArrayList<Ticket> tickets = new ArrayList<>();

    // Add ticket method
    public void addTicket(Ticket ticket)
    {
        tickets.add(ticket);
    }

    // Retrieve and Show all tickets
    public ArrayList<Ticket> getAllTickets()
    {
        return tickets;
    }

    // Search for ticket using ID
    public Ticket getTicketById(int ticketId)
    {
        for (Ticket t : tickets)
        {
            if (t.getTicketId() == ticketId) return t;
        }
        return null;
    }
}
