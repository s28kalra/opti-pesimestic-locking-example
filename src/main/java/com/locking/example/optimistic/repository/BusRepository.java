package com.locking.example.optimistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locking.example.optimistic.model.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer>{

}
