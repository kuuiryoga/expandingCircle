package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Shares;
import com.example.demo.repository.SharesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SharesService {

	@Autowired
	SharesRepository repository;
	
	public List<Shares> findAll() {
		return repository.findAll();
	}
	
}
