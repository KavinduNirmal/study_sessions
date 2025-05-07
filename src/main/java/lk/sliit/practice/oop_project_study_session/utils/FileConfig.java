package lk.sliit.practice.oop_project_study_session.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum FileConfig {
    INSTANCE;

    private String firebase;

    FileConfig() {
        Properties prop = new Properties();
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            prop.load(input);
            System.out.println(input.read());
            firebase = prop.getProperty("firebase.private");

            validatePaths();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void validatePaths() {
        if (firebase == null || firebase.trim().isEmpty()) {
            throw new RuntimeException("firebase not set");
        }
    }

    public String getFirebase() {
        return firebase;
    }
}
