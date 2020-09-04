package jlopez.CheckingAccount.infrastructure;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.CheckingAccount.domain.Credit;
import jlopez.CheckingAccount.domain.Debit;
import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.valueObjects.Description;
import jlopez.CheckingAccount.domain.valueObjects.OpeningDate;
import jlopez.Customer.domain.valueObjects.CustomerId;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebaseCheckingAccount implements CheckingAccounts {
    Firestore db;


    public FirebaseCheckingAccount() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("./ServiceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ddd-bank-kata.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);

        db = FirestoreClient.getFirestore();
    }

    @Override
    public void save(CheckingAccount account) {
        DocumentReference docRef = db.collection("CheckingAccount").document(account.getCheckingAccountId().getValue().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("CustomerId", account.getCustomerId().getValue().toString());
        data.put("OpeningDate", account.getOpeningDateAsString());
        data.put("State", account.getState().toString());

        data.put("Debits", debitsToArray(account.getDebits()));
        data.put("Credits", creditsToArray(account.getCredits()));
        ApiFuture<WriteResult> result = docRef.set(data);


        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private List<String> debitsToArray(List<Debit> debits) {
        List<String> debitsTransformed = new ArrayList<>();
        for (Debit debit: debits) {
            debitsTransformed.add(String.valueOf(debit.getAmount()) + ";" + debit.getDescription().getValue());
        }

        return  debitsTransformed;
    }

    private List<String> creditsToArray(List<Credit> credits) {
        List<String> creditsTransformed = new ArrayList<>();
        for (Credit credit: credits) {
            creditsTransformed.add(String.valueOf(credit.getAmount()) + ";" + credit.getDescription().getValue());
        }

        return  creditsTransformed;
    }

    @Override
    public CheckingAccount findById(CheckingAccountId id) {
        DocumentReference docRef = db.collection("CheckingAccount").document(id.getValue().toString());
        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = null;
        try {
             document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (document == null || !document.exists()) { return  null; }

        return createCheckingAccountFromFirebaseDocument(document);

    }

    private CheckingAccount createCheckingAccountFromFirebaseDocument(DocumentSnapshot document) {
        CheckingAccountId checkingAccountId = new CheckingAccountId(UUID.fromString(document.getId()));
        CustomerId customerId = new CustomerId(UUID.fromString(document.getString("CustomerId")));
        OpeningDate openingDate = new OpeningDate(LocalDate.parse(document.getString("OpeningDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<Debit> debits = arrayToDebits((List<String>)document.get("Debits"));
        List<Credit> credits = arrayToCredits((List<String>)document.get("Credits"));

        return new CheckingAccount(checkingAccountId, customerId, openingDate, debits, credits, CheckingAccount.State.OPEN);
    }

    private List<Debit> arrayToDebits(List<String> debits) {
        List<Debit> debitsTransformed = new ArrayList<>();
        for (String debit: debits) {
            String[] creditInfo = debit.split(";");
            debitsTransformed.add(new Debit(new Amount(Integer.parseInt(creditInfo[0])), new Description(creditInfo[1])));
        }

        return  debitsTransformed;
    }

    private List<Credit>  arrayToCredits(List<String> credits) {
        List<Credit> creditsTransformed = new ArrayList<>();
        for (String credit: credits) {
            String[] creditInfo = credit.split(";");
            creditsTransformed.add(new Credit(new Amount(Integer.parseInt(creditInfo[0])), new Description(creditInfo[1])));
        }

        return  creditsTransformed;
    }
}
