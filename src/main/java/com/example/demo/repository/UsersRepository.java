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

//    // ユーザーデータを更新
//    @Query(name = "updateUser")
//    public Users updataUser(@Param("userid")String userId,
//    						@Param("password")String passWord,
//    						@Param("name")String name,
//    						@Param("sex")String sex,
//    						@Param("mail")String mail,
//    						@Param("age")String age,
//    						@Param("text")String text,
//    						@Param("uniqueWord")String uniqueWord,
//    						@Param("updated")LocalDateTime updataTime);
    
    
}
