package jlopez.CheckingAccount.infrastructure;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseAppManager {
    private static FirebaseAppManager instance = null;

    private FirebaseAppManager() {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("./ServiceAccountKey.json");
        } catch (FileNotFoundException e) {
            throw new FirebaseServiceAccountNotFound();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://ddd-bank-kata.firebaseio.com")
                    .build();
        } catch (IOException e) {
            throw new FirebaseGoogleCredentialsNotFound();
        }
        FirebaseApp.initializeApp(options);
    }

    public static Firestore getFirestore()
    {
        if (instance == null) {
            instance = new FirebaseAppManager();
        }
        return FirestoreClient.getFirestore();
    }

    public class FirebaseServiceAccountNotFound extends RuntimeException {
    }

    public class FirebaseGoogleCredentialsNotFound extends RuntimeException {
    }
}
