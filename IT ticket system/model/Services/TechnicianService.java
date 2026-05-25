package Services;

import Model.Technician;
import Model.Ticket;
import Storage.TechnicianStorage;
import Repository.TechnicianRepository;

public class TechnicianService
{
    private TechnicianRepository tech;
    private TechnicianStorage storage;
    private int techIdCounter = 1;


    // Call tech after import 
    public TechnicianService(TechnicianRepository tech)
    {
        this.tech = tech;
    }

    // ============================== CREATE TECHNICIAN ==============================
    public void createTechnician(String technicianName, String technicianStatus, String contactNr)
    {
        // create technician using these parameters
        Technician technician = new Technician(techIdCounter++, technicianName, contactNr);

        // Add texhnician to the technician repository
        tech.addTechnician(technician);

        // Save and update Technician list data
        storage.saveTechnician(tech.getAllTechnicians());
    }

    // ============================== VIEW TECHNICIAN ==============================
    public void viewTechnicians()
    {
        // for every technician, in the tech arrays list print it out 
        for(Technician t : tech.getAllTechnicians())
        {
            System.out.println(t); 
        }
    }

    // ============================== UPDATE TECHNICIAN STATUS ==============================
    // Change status to updateStatusToOccupied
    public void updateStatusToOccupied(int technicianId)
    {
        Technician t = tech.getTechniciansById(technicianId);
        if(t != null)
        {
            t.setTechnicianStatusToOccupied();
        }
    }
}
