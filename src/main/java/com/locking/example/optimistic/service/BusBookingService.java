package com.locking.example.optimistic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locking.example.optimistic.model.Bus;
import com.locking.example.optimistic.model.Seat;
import com.locking.example.optimistic.model.Seat.Status;
import com.locking.example.optimistic.repository.BusRepository;
import com.locking.example.optimistic.repository.SeatRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusBookingService {

	private BusRepository busRepository;
	private SeatRepository seatRepository;

	@Transactional
	public void bookBusSeats(int busId, List<Integer> seatIds) {

		Bus bus = busRepository.findById(busId).get();
		List<Seat> seats = seatRepository.findAllBySeatIdInAndBusAndStatus(seatIds, bus, Status.FREE);
		if (bus.getAvailableSeats() >= seatIds.size()) {
			seats.forEach(s -> s.setStatus(Status.BOOKED));
			bus.setAvailableSeats(bus.getAvailableSeats() - seats.size());
			busRepository.save(bus);
		}

	}

}
