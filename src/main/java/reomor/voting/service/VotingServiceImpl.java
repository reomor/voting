package reomor.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reomor.voting.model.Vote;
import reomor.voting.repository.VoteRepository;

import java.time.LocalDateTime;

@Service
public class VotingServiceImpl implements VotingService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote make(LocalDateTime dateTime, int userId, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(LocalDateTime dateTime, int userId) {
        return false;
    }

    @Override
    public Vote get(LocalDateTime dateTime, int userId) {
        return null;
    }
}
