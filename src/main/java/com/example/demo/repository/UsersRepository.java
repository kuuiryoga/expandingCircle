package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	// ログインユーザー取得
    @Query(name = "findLoginUser")
    public List<Users> findLoginUser(@Param("userid") String userId, @Param("password") String password );

}
