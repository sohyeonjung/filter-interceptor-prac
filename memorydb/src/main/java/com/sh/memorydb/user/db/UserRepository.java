package com.sh.memorydb.user.db;

import com.sh.memorydb.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //select * from user where score > [??]
    List<UserEntity> findAllByScoreGreaterThanEqual(int score);

    //select * from user where score >=?? AND score <=??
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);


    //jpql 사용
    @Query("select u from user u where u.score >=?1 AND u.score <=?2")
    List<UserEntity> score(int min, int max);

    //native query 사용
//    @Query(
//            value = "select * from user as u where u.score >=?1 AND u.score <=?2",
//            nativeQuery = true
//    )
//    List<UserEntity> score2(int min, int max);


    //named parameter 이용
    @Query(
            value = "select * from user as u where u.score >= :min AND u.score <=:max",
            nativeQuery = true
    )
    List<UserEntity> score2(
            @Param(value = "min") int min,
            @Param(value = "max") int max);



}
