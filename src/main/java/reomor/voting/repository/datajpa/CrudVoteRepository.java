package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import reomor.voting.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
}
