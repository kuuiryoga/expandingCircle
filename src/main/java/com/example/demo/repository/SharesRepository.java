package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Shares;
import com.example.demo.model.Users;

@Repository
public interface SharesRepository extends JpaRepository<Shares, Integer>{
	
	//クエリーパーツ
	public static final String _sharesMainQuery
	= "SELECT * FROM shares s";
	
	public static final String _technologyQuery
	= _sharesMainQuery + " WHERE s.name LIKE %:name% "
						+ "OR s.title LIKE %:title% "
						+ "OR s.text LIKE %:text%";
	
	//技術共有で呼び出す
	@Query( value = _technologyQuery,nativeQuery = true)
	public List<Users> findByTechnologySearch(
			@Param("name")String name
			,@Param("title")String titel
			,@Param("text")String text
		);
}
