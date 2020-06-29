package webubb.model;

import webubb.domain.Image;
import webubb.domain.Review;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;


public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/photos_app", "root", "");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username + " " + password);
        try {
            rs = stmt.executeQuery("select * from users where username='" + username + "' and password='" + password + "'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<Image> getAllImages(){
        ArrayList<Image> images = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from images");
            while (rs.next()) {
                images.add(new Image(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("image"),
                        rs.getString("description")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }
    public ArrayList<Image> getMostPopularImages(int n_images){
        ArrayList<Image> images = new ArrayList<>();
        ResultSet rs;
        String query = "select i.id as image_id,i.image, i.description, rev.review as review\n" +
                "from images i inner join (\n" +
                "  select r.image_id, avg(r.stars) as review\n" +
                "  from reviews r\n" +
                "  group by r.image_id\n" +
                ")rev on i.id = rev.image_id\n" +
                "order by rev.review desc\n" +
                "LIMIT " + n_images;
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                images.add(new Image(
                        rs.getInt("image_id"),
                        rs.getString("image"),
                        rs.getString("description"),
                        rs.getFloat("review")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }


    public ArrayList<Image> getUserImages(int userId) {
        ArrayList<Image> images = new ArrayList<>();
        ResultSet rs;

        try {
            rs = stmt.executeQuery("select * from images i where i.user_id =" + userId);
            while (rs.next()) {
                images.add(new Image(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getString("image"),
                        rs.getString("description")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public ArrayList<Review> getUserReviews(int userId) {
        ArrayList<Review> reviews = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from reviews where user_id=" + userId);
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("image_id"),
                        rs.getInt("stars")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;

    }


    public Boolean addImage(Image image) throws SQLException {
        int r = 0;
        try {
            r = stmt.executeUpdate("insert into images(user_id, image, description) values (" + image.getUser_id() +",' " + image.getImage() + "','"+ image.getDescription() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r > 0;
    }


    public Boolean deleteImage(int image_id) throws SQLException {
        int r = 0;
        try {
            r = stmt.executeUpdate("delete from images where id = " + image_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r > 0;
    }

    public Boolean addReview(Review review) {
        int r = 0;
        try {
            r = stmt.executeUpdate("insert into reviews(user_id, image_id, stars) values (" + review.getUser_id() +",' " + review.getImage_id() + "','"+ review.getStars() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r > 0;

    }
}