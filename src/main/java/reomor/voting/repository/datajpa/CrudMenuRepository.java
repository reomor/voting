package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.Menu;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
    @Override
    Menu save(Menu menu);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id and m.date=:date")
    int delete(@Param("id") int id, @Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=:id and m.restaurant.id=:restaurantId")
    Menu getMenuByIdAndRestaurant(@Param("id") int menuId, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.date=:date AND m.restaurant.id=:id")
    Menu getMenuByRestaurantAndDate(@Param("id") int restaurantId, @Param("date") LocalDate date);

    @SuppressWarnings("JpaInspection")
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:id")
    List<Menu> getAllMenusByRestaurant(@Param("id") int restaurantId);

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @SuppressWarnings("JpaInspection")
    @Query("SELECT m FROM Menu m WHERE m.date=:date ORDER BY m.restaurant.name ASC")
    List<Menu> getAllMenusByDate(@Param("date") LocalDate date);
}
