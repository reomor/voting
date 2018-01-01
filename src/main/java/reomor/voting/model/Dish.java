package reomor.voting.model;

public class Dish extends BaseEntity {
    private String description;
    private Integer price;

    public Dish(String description, Integer price) {
        this.description = description;
        this.price = price;
    }
}
