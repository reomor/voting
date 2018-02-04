package reomor.voting.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    Restaurant save(Restaurant restaurant);

    @Modifying
    @Transactional
    @Query("DELETE from Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    List<Restaurant> findAll(Sort sort);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getWithMenus(@Param("id") int restaurantId);
}
