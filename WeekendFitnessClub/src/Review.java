
public class Review {

    private String comment;
    private int rating;
    private Customer customer;

    public Review(String comment, int rating, Customer customer) {
        this.comment = comment;
        this.rating = rating;
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
