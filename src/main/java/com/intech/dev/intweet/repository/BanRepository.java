package com.intech.dev.intweet.repository;

import com.intech.dev.intweet.entity.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BanRepository extends JpaRepository<Ban, Long> {
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Ban b WHERE b.user.id = :userId")
    boolean isUserBanned(@Param("userId") Integer userId);
}
