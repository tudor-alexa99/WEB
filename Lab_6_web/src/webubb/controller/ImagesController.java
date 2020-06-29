package webubb.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Image;
import webubb.model.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImagesController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if((action != null) && action.equals("getAllUser")){
            int userId = Integer.parseInt(request.getParameter("userId"));

            response.setContentType("application/json");
            DBManager dbManager = new DBManager();

            ArrayList<Image> images = dbManager.getUserImages(userId);
            JSONArray jsonImages = new JSONArray();

            for (Image image : images) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", image.getId());
                jsonObject.put("user_id", image.getUser_id());
                jsonObject.put("image", image.getImage());
                jsonObject.put("description", image.getDescription());
                jsonImages.add(jsonObject);
            }

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonImages.toJSONString());
            out.flush();
        }

        if((action != null) && action.equals("getAll")){

            response.setContentType("application/json");
            DBManager dbManager = new DBManager();

            ArrayList<Image> images = dbManager.getAllImages();
            JSONArray jsonImages = new JSONArray();

            for (Image image : images) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", image.getId());
                jsonObject.put("user_id", image.getUser_id());
                jsonObject.put("image", image.getImage());
                jsonObject.put("description", image.getDescription());
                jsonImages.add(jsonObject);
            }

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonImages.toJSONString());
            out.flush();
        }

        if((action != null) && action.equals("add")) {
            Image image = new Image(
                    0,
                    Integer.parseInt(request.getParameter("userId")),
                    request.getParameter("image"),
                    request.getParameter("description")
            );

            DBManager dbManager = new DBManager();
            Boolean result = null;
            try {
                result = dbManager.addImage(image);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());

            if (result) {
                out.println("Image added successfully.");
            } else {
                out.println("Error adding a new image!");
            }
            out.flush();
        }
        if((action != null) && action.equals("deleteImage")){
            response.setContentType("application/json");
            DBManager dbManager = new DBManager();

            Boolean result = null;
            try {
                result = dbManager.deleteImage(Integer.parseInt(request.getParameter("image_id")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());

            if (result) {
                out.println("Image added successfully.");
            } else {
                out.println("Error adding a new image!");
            }
            out.flush();

        }
        if((action != null) && action.equals("getMostPopular")){
            response.setContentType("application/json");
            DBManager dbManager = new DBManager();

            ArrayList<Image> images = dbManager.getMostPopularImages(Integer.parseInt(request.getParameter("n_images")));
            JSONArray jsonImages = new JSONArray();

            for (Image image : images) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", image.getId());
                jsonObject.put("image", image.getImage());
                jsonObject.put("description", image.getDescription());
                jsonObject.put("review", image.getReviews());
                jsonImages.add(jsonObject);
            }

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonImages.toJSONString());
            out.flush();
        }
    }

}
