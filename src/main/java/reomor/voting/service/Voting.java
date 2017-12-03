package reomor.voting.service;

public interface Voting {
    void getAllRestaurantWithMenus();
    void vote(int userId, int restaurantId);
}
