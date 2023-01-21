package com.example.network.repository;

import com.example.network.entity.Friends;
import com.example.network.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRepository extends CrudRepository<Friends, Long> {

    @Modifying
    @Transactional
    @Query("DELETE  FROM Friends u WHERE (u.user =:user and u.userFriend =:userFriend) " +
            "or (u.userFriend =:user and u.user =:userFriend)")
    void deleteByUserAndAndUserFriend(@Param("user") User user, @Param("userFriend") User userFriend);

    @Query("SELECT f.userFriend from Friends f WHERE (f.user.id = :userId ) ")
    List<User> findFriendsUsers(@Param("userId") Long userId);


}
