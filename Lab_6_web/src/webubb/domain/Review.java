package webubb.domain;

public class Review {
    private int id;
    private int user_id;
    private int image_id;
    private int stars;

    public Review(int id, int user_id, int image_id, int stars) {
        this.id = id;
        this.user_id = user_id;
        this.image_id = image_id;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getImage_id() {
        return image_id;
    }

    public int getStars() {
        return stars;
    }
}
