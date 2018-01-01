package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import reomor.voting.model.Menu;

public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
}
