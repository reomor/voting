package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import reomor.voting.model.User;

public interface CrudUserRepository extends JpaRepository<User, Integer> {
}
