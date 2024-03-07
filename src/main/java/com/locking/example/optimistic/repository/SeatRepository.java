package com.locking.example.optimistic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locking.example.optimistic.model.Bus;
import com.locking.example.optimistic.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	List<Seat> findAllBySeatIdInAndBus(Collection<Integer> seatIds, Bus bus);

}
