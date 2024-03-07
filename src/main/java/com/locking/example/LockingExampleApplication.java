package com.locking.example;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.locking.example.optimistic.model.Bus;
import com.locking.example.optimistic.model.Seat;
import com.locking.example.optimistic.model.Seat.SeatType;
import com.locking.example.optimistic.model.Seat.Status;
import com.locking.example.optimistic.repository.BusRepository;
import com.locking.example.pessimestic.Wallet;
import com.locking.example.pessimestic.WalletRepository;

@SpringBootApplication
public class LockingExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LockingExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addSampleData();
	}

	@Autowired
	private WalletRepository walletRepository;

	@Autowired
	private BusRepository busRepository;

	private void addSampleData() {
		var wallets = Arrays.asList(new Wallet(1, 100.0), new Wallet(2, 200), new Wallet(1000, 400));
		walletRepository.saveAll(wallets);

		Seat seat1 = new Seat(101, SeatType.SEATER, Status.FREE, 1, null);
		Seat seat2 = new Seat(102, SeatType.SEATER, Status.FREE, 1, null);
		Bus bus = new Bus(1, "delhi", "manali", 0, null);
		bus.addSeat(seat1);
		bus.addSeat(seat2);
		bus.setAvailableSeats(bus.getSeats().size());

		busRepository.save(bus);
	}

}
