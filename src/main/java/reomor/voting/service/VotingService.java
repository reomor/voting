package reomor.voting.service;

import reomor.voting.to.RestaurantWithMenu;

import java.util.Date;
import java.util.List;

public interface VotingService {


    void vote(int userId, int restaurantId);
}
