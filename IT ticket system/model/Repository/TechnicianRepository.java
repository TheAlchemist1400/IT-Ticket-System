package Repository;

import Model.Technician;
import Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TechnicianRepository 
{
    // ============================== CREATE TECHNICIAN ARRAYLIST ==============================
    // Create an arraylist to store the technicians created
    private ArrayList<Technician> technicians = new ArrayList<>();

    // ============================== ADD TO ARRAY LIST ==============================
    // Add technician method
    public void addTechnician(Technician technician)
    {
        technicians.add(technician);
    }

    // ============================== PRINT ALL TECHNICIAN ==============================
    // Retrieve and Show all technician
    public ArrayList<Technician> getAllTechnicians()
    {
        return technicians;
    }

    // ============================== SEARCH FOR TECHNICIAN ==============================
    // Search for technician using ID
    public Technician getTechniciansById(int technicianId)
    {
        for (Technician t : technicians)
        {
            if (t.getTechnicianID() == technicianId) return t;
        }
        return null;
    }
}
