package Storage;

import Model.Technician;
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

public class TechnicianStorage
{
    // create GSON object
    private final Gson gson = new Gson();
    // File path variable
    String FILE_PATH = "Storage/technicians.json";

    // ============================== SAVE TECHNICIAN =============================
    public void saveTechnician(List<Technician> technicians)
    {
        try
        {
            // Create a Storage folder incase it doesn't exist
            new File("Storage").mkdirs();

            // Create a file technicians.json
            FileWriter writer = new FileWriter(FILE_PATH);

            // Convert technicians to GSON to avoid typing it manually
            String json = gson.toJson(technicians);

            // Write the variable "json" inside it which is tickets turned into gson
            writer.write(json);

            // Close file
            writer.close();

            // Return success alert when steps executed
            System.out.println("Technician saved successfully.");
        }
        catch (Exception e)
        {
            System.out.println("Error saving technician: " + e.getMessage());
        }
    }

    // ============================== LOAD TICKETS =============================
    public ArrayList <Technician> loadTechnicians()
    {
        try
        {
            File file = new File(FILE_PATH);

            // If fle doesnt exist, alert and return empty list
            if(!file.exists())
            {
                System.out.println("File doesnt exist");
                return new ArrayList<>();
            }

            // Create reader object
            FileReader reader = new FileReader(file);

            Type listType = new TypeToken <ArrayList<Technician>>(){}.getType();

            ArrayList<Technician> technicians = gson.fromJson(reader, listType);

            reader.close();

            //Return technicians if array list not empty, if empty create new one
            return (technicians != null) ? technicians : new ArrayList<>();
        }
        catch (Exception e)
        {
            // Return New array list in case of error
            System.out.println("Error loading technicians: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
