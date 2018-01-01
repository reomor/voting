package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import reomor.voting.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
