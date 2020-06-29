package webubb.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Review;
import webubb.model.DBManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewsController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        
        if((action != null) && action.equals("getAll")){
            int userId = Integer.parseInt(request.getParameter("userId"));

            response.setContentType("application/json");
            DBManager dbmanager = new DBManager();
            ArrayList<Review> reviews = dbmanager.getUserReviews(userId);
            JSONArray jsonReviews = new JSONArray();
            for (int i = 0; i < reviews.size(); i++) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", reviews.get(i).getId());
                jObj.put("user_id", reviews.get(i).getUser_id());
                jObj.put("image_id", reviews.get(i).getImage_id());
                jObj.put("stars", reviews.get(i).getStars());
                jsonReviews.add(jObj);
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonReviews.toJSONString());
            out.flush();
        }
        if((action != null) && action.equals("addReview")){
            Review review = new Review(
                    0,
                    Integer.parseInt(request.getParameter("user_id")),
                    Integer.parseInt(request.getParameter("image_id")),
                    Integer.parseInt(request.getParameter("stars"))
            );
            DBManager dbManager = new DBManager();
            Boolean result = null;
            result = dbManager.addReview(review);
            PrintWriter out = new PrintWriter(response.getOutputStream());

            if (result) {
                out.println("Review added successfully.");
            } else {
                out.println("Error adding a new review!");
            }
            out.flush();

        }
    }
        
}           
