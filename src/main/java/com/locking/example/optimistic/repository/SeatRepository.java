package com.locking.example.optimistic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.locking.example.optimistic.model.Bus;
import com.locking.example.optimistic.model.Seat;
import com.locking.example.optimistic.model.Seat.Status;

import jakarta.persistence.LockModeType;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
	List<Seat> findAllBySeatIdInAndBusAndStatus(Collection<Integer> seatIds, Bus bus, Status status);

}
