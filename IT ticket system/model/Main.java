import Operations.Operation;
import Repository.TechnicianRepository;
import Repository.TicketRepository;
import Services.TechnicianService;
import Services.TicketService;

public class Main
{
    public static void main(String [] args)
    {
        TicketRepository ticketRepo = new TicketRepository();
        TechnicianRepository technicianRepo = new TechnicianRepository();

        TicketService ticketService = new TicketService(ticketRepo);
        TechnicianService techService = new TechnicianService(technicianRepo);

        Operation op = new Operation(
                ticketService,
                techService,
                ticketRepo,
                technicianRepo
        );

        System.out.println("\b\b===================== Welcome to the IT TICKET SYSTEM =====================\n");
        op.start();
    }
}