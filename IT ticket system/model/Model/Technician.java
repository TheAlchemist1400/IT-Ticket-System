package Model;

public class Technician
{
    /*
    - Chris - 
    - Bafo - 
    - Kamo - 
    - Tshepiso - 
    - Stuga - 
    - Majova - 
    - Reitumetse - 
    - X - 
    - Manando - 
    - Amando -
    */

    private int technicianId;
    private String technicianName;
    private String contactNr;
    private String technicianStatus;

    public Technician(int technicianId, String technicianName, String contactNr)
    {
        this.technicianId = technicianId;
        this.technicianName = technicianName;
        this.technicianStatus =  "unassigned";
        this.contactNr = contactNr;
    }

    @Override
    // Displaying Technician
    public String toString()
    {
        return """
            =============================== TECHNICIAN %d ===============================
            Name: %s
            Status: %s  
            Contact: %s
            =============================================================================
            """.formatted(technicianId, technicianName, technicianStatus, contactNr);
    }


    // Getter for technician Id 
    public int getTechnicianID()
    {
        return technicianId;
    }

    // Getter for Technician Name
    public String getTechnicianName()
    {
        return technicianName;
    }

    // Getter for Technician status
    public String getTechnicianStatus()
    {
        return technicianStatus;
    }

    // Getter for Technician contactNr
    public String getTechnicianContactNr()
    {
        return contactNr;
    }

    // Setter for Technician Name
    public void setTechnicianName(String technicianName) {this.technicianName = technicianName;}

    // Setter for Technician status
    public void setTechnicianStatusToOccupied()
    {
        this.technicianStatus = "Assigned";
    }

    // Setter for Technician status to unassigned
    public void setTechnicianStatusToUnoccupied()
    {
        this.technicianStatus = "Unassigned";
    }

    // Setter for Technician contactNrT
    public void setTechnicianContactNr(String contactNr)
    {
        this.contactNr = contactNr;
    }    
}
