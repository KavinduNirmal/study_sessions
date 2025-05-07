package lk.sliit.practice.oop_project_study_session.utils;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class FirebaseShutdownListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            Firestore db = FirebaseInitializer.getDb();
            if (db != null) {
                db.close();
            }
            FirebaseApp.getInstance().delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
