/*

Things needed in our system's ticket class:

- Ticket ID
- Name 
- Floor 
- Division ( teacher , call center etc.)
- Timestamp
- Problem 
- Was it attended
- was it solved and by who who

----------------------------------------------------
- create a time stamp 

*/
package Model;

public class Ticket
{
    // Variables
    private int ticketId;
    private String clientName;
    private int floor;
    private String status;
    private String assignedTo;
    private String department; // which section you need help in network, computer, laptop, camera, phone.
    private String problemDesc;
    private String division; // who's logging 

    // import class Technician
    private Technician tech;

    // Ticket service constructor
    public Ticket(Technician tech) 
    {
        this.tech = tech;
    }

    // Ticket constructor, construct using the variables inside
    public Ticket(int ticketId, int floor, String clientName, String division, String department, String problemDesc)
    {
        this.ticketId = ticketId;
        this.status = "Open";
        this.floor =  floor;
        this.clientName = clientName;
        this.division = division;
        this.department = department;
        this.problemDesc = problemDesc;
        this.assignedTo = "Unassigned";
    }

    @Override
    // print ticket to screen
    public String toString()
    {
        return """
              =============================== TICKET %s ===============================
              Client name: %s
              Floor: %s
              Department: %s
              Division: %s
              Problem: %s
              Assigned To: %s
              ================================================================================
              """.formatted(ticketId, clientName, floor, department, division, problemDesc, assignedTo);
    }

    // Getter for TicketId
    public int getTicketId()
    {
        return ticketId;
    }

    // Setter for status
    public void setStatus(String status)
    {
        this.status = status;
    }

    // Setter for assignedTo
    public void setAssignedTo(String assignedTo)
    {
        this.assignedTo = assignedTo;
    }
}
