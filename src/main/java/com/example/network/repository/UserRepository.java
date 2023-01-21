package com.example.network.repository;


import com.example.network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);

    @Query(value = "SELECT u from User u WHERE (u.id <> :userId ) ")
    List<User> findAllOtherUsers(@Param("userId") Long userId);





}
