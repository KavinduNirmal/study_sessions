package lk.sliit.practice.oop_project_study_session.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseInitializer {
    private static Firestore db;
    private static FirebaseApp firebaseApp;

    public static void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAcc = new FileInputStream(FileConfig.INSTANCE.getFirebase());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAcc))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        }
    }

    public static Firestore getDb() {
        return db;
    }

    public static FirebaseApp getFirebaseApp() {
        return firebaseApp;
    }
}