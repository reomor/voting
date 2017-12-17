package reomor.voting.service;

import reomor.voting.to.RestaurantWithMenu;

import java.util.List;

public interface VotingService {
    List<RestaurantWithMenu> getAllRestaurantWithMenus();
    void vote(int userId, int restaurantId);
}
