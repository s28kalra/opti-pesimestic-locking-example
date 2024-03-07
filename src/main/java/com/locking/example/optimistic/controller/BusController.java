package com.locking.example.optimistic.controller;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locking.example.optimistic.service.BusBookingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bus")
@AllArgsConstructor
public class BusController {

	private BusBookingService busBookingService;

	@GetMapping("/{busId}/{seatIds}")
	public void bookBus(@PathVariable Integer busId, @PathVariable List<Integer> seatIds) {
		try {
			busBookingService.bookBusSeats(busId, seatIds);
		} catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
			e.printStackTrace();
		}

	}

}
