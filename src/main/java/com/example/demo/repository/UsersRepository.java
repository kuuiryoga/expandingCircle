package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.id.enhanced.TableStructure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public List<Users> findAll();
	
	
	//クエリーパーツ
	public static final String _usersMainQuery
	= "SELECT * FROM users u";
	
	public static final String _sharesMainQuery
	= "SELECT * FROM shares s";
	
	public static final String _intercepterQuery
	= _usersMainQuery + " WHERE u.name LIKE %:name%";

	public static final String _interoductionCategoryQuery
	= "select * from users u1 "
			+ " where u1.id = ("
			+ " select id from (select id, name, unnest(regexp_split_to_array(unique_word, ',')) as data"
			+ " from users) as c"
			+ " where c.data in (:uniqueWord)"
			+ " AND c.name LIKE %:name% "
			+ " group by c.id)";
	
	public static final String _technologyQuery
	= _sharesMainQuery + " WHERE s.name LIKE %:name% "
						+ "OR s.title LIKE %:title% "
						+ "OR s.text LIKE %:text%";
	
	
	//自己紹介で呼び出す
	@Query( value = _intercepterQuery,nativeQuery = true)
	public List<Users> findByIntercepterSearch(@Param("name")String name);
	
	//技術共有で呼び出す
	@Query( value = _technologyQuery,nativeQuery = true)
	public List<Users> findByTechnologySearch(
			@Param("name")String name
			,@Param("title")String titel
			,@Param("text")String text
		);
	
	// 自己紹介カテゴリー検索
	@Query( value = _interoductionCategoryQuery, nativeQuery = true )
	public List<Users> findByIntroductionCategory(
			@Param("uniqueWord")List<String> uniqueWord
			,@Param("name")String name
		);
	
	
	// ログインユーザー取得
    @Query(name = "findLoginUser")
    public List<Users> findLoginUser(@Param("userid") String userId, @Param("password") String password );
    
    // ユーザーデータを更新
    @Modifying
    @Query(name = "updateUser")
    public void updataUser(@Param("id")Integer id,
    						@Param("userid")String userId,
    						@Param("password")String passWord,
    						@Param("name")String name,
    						@Param("sex")Integer sex,
    						@Param("mail")String mail,
    						@Param("age")Integer age,
    						@Param("text")String text,
    						@Param("uniqueWord")String uniqueWord,
    						@Param("updated")LocalDateTime updated);
    
}
