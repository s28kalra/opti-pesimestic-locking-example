package com.locking.example.optimistic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locking.example.optimistic.model.Bus;
import com.locking.example.optimistic.model.Seat.Status;
import com.locking.example.optimistic.repository.BusRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusBookingService {

	private EntityManager entityManager;
	private BusRepository busRepository;

	@Transactional
	public void bookBusSeats(int busId, List<Integer> seatIds) {

		Bus bus = entityManager.find(Bus.class, busId, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (bus.getAvailableSeats() >= seatIds.size()) {
			var seats = bus.getSeats().stream()
					.filter(x -> Status.FREE.equals(x.getStatus()) && seatIds.contains(x.getSeatId())).toList();
			seats.forEach(s -> s.setStatus(Status.BOOKED));
			bus.setAvailableSeats(bus.getAvailableSeats() - seats.size());
			busRepository.save(bus);
		}

	}

}
