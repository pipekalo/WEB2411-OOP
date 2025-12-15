package edu.chdtu.web2411.victoria.lab3.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.chdtu.web2411.victoria.lab3.models.HospitalState;

public class DataManager {
    private static final String FILE_NAME = "hospital_db_lab3.ser";

    public static void saveState(HospitalState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("[System] Data serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HospitalState loadState() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new HospitalState();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (HospitalState) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading file. Starting with empty database.");
            return new HospitalState();
        }
    }
}