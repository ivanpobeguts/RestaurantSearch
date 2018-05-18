package com.pobeguts.RestaurantSearch.repository;

import com.pobeguts.RestaurantSearch.model.VoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface VoteHistoryRepository extends JpaRepository<VoteHistory, Integer> {

    @Override
    @Transactional
    VoteHistory save(@Param("voteHistory") VoteHistory voteHistory);

    @Query("SELECT m FROM VoteHistory m WHERE m.user_id=:user_id and m.date_time BETWEEN :startDate AND :endDate order by m.date_time desc")
    List<VoteHistory> getForDate(@Param("user_id") int user_id, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT m FROM VoteHistory m")
    List<VoteHistory> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM VoteHistory m WHERE m.id=:id")
    int delete(@Param("id") int id);
}
