package Storage;

import Model.Ticket;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class TicketStorage
{
    // create GSON object
    private final Gson gson = new Gson();
    // File path variable
    String FILE_PATH = "Storage/tickets.json";

    // ============================== SAVE TICKETS =============================
    public void saveTicket(List<Ticket> tickets)
    {
        try
        {
            // Create a Storage folder incase it doesn't exist
            new File("Storage").mkdirs();

            // Create a file tickets.json
            FileWriter writer = new FileWriter(FILE_PATH);

            // Convert tickets to GSON to avoid typing it manually
            String json = gson.toJson(tickets);

            // Write the variable "json" inside it which is tickets turned into gson
            writer.write(json);

            // Close file
            writer.close();

            // Return success alert when steps executed
            System.out.println("Tickets saved successfully.");
        }
        catch (IOException e)
        {
            System.out.println("Error saving tickets: " + e.getMessage());
        }
    }

    // ============================== LOAD TICKETS =============================
    public ArrayList <Ticket> loadTickets()
    {
        try
        {
            File file = new File(FILE_PATH);

            // If file doesn't exist, alert and return empty list
            if(!file.exists())
            {
                System.out.println("File doesnt exist");
                return new ArrayList<>();
            }

            // Create Reader object
            FileReader reader = new FileReader(file);

            Type listType = new TypeToken<ArrayList<Ticket>>(){}.getType();

            ArrayList<Ticket> tickets = gson.fromJson(reader, listType);

            reader.close();
            // Return tickets if array list Ticket not empty, if empty create new one
            return (tickets != null) ? tickets : new ArrayList<>();
        }
        catch (Exception e)
        {
            // Return New array list in case of error
            System.out.println("Error loading tickets: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
