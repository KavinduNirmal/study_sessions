package lk.sliit.practice.oop_project_study_session;

import java.io.*;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.sliit.practice.oop_project_study_session.utils.FirebaseInitializer;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    Firestore db;

    public void init() {
        try {
            FirebaseInitializer.init();
            db = FirebaseInitializer.getDb();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CollectionReference users = db.collection("users");
        Query query = users.whereEqualTo("id", 1);
        ApiFuture<QuerySnapshot> future = query.get();
        DocumentSnapshot document;
        try {
            document = future.get().getDocuments().get(0);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>" + document.getData() + "</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}