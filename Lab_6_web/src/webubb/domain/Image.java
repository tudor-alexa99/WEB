package webubb.domain;


public class Image {
    private int id;
    private int user_id;
    private String image;
    private String description;
    private float reviews;

    public Image(int id, int user_id, String image, String description) {
        this.id = id;
        this.user_id = user_id;
        this.image = image;
        this.description = description;
    }
    public Image(int id, String image, String description, float reviews) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.reviews = reviews;
    }
    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public float getReviews() {
        return reviews;
    }
}
