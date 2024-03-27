package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Shares;

@Repository
public interface SharesRepository extends JpaRepository<Shares, Integer>{

}
