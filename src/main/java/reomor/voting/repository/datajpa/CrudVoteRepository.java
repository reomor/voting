package reomor.voting.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import reomor.voting.model.User;
import reomor.voting.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    @Transactional
    Vote save(Vote vote);

    @Modifying
    @Transactional
    @Query("DELETE from Vote v WHERE v.dateTime=:dateTime and v.user.id=:id")
    int delete(@Param("dateTime") LocalDateTime dateTime, @Param("id") int userId);

    @Modifying
    @Transactional
    @Query("DELETE from Vote v WHERE v.id=:id and v.user.id=:userid")
    int delete(@Param("id") int id, @Param("userid") int userId);

    @Query("SELECT v FROM Vote v WHERE v.dateTime=:dateTime AND v.user.id=:id")
    Vote get(@Param("dateTime") LocalDateTime dateTime, @Param("id") int userId);

    @SuppressWarnings("JpaInspection")
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.dateTime DESC")
    List<Vote> getAllByUser(@Param("userId") int userId);

    @SuppressWarnings("JpaInspection")
    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.dateTime >= :start AND v.dateTime <= :finish AND v.restaurant.id=:restaurantId")
    List<Vote> getBetween(@Param("start") LocalDateTime start, @Param("finish") LocalDateTime end, @Param("restaurantId") int restaurantId);
}
