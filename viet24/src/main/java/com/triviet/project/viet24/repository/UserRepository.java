package com.triviet.project.viet24.repository;

import com.triviet.project.viet24.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("Select user from User user where user.username like ?1")
    User findByUsername(String username);

    @Query("Select user from User user where user.email like ?1 and user.password like ?2")
    User loadUser(String email, String password);

//    @Query("Select user from User user where user")
//    Boolean existsByUserName(String userName);
//
//    Boolean existByEmail(String email);
}