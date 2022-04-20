package com.ninjaone.backendinterviewproject.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ninjaone.backendinterviewproject.model.Services;

@Repository
public interface ServicesRepository extends CrudRepository<Services, String> {}
