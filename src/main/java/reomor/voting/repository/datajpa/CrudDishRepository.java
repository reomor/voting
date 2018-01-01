package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import reomor.voting.model.Dish;

public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
}
