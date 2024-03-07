package com.locking.example.optimistic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bus")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bus {

	@Id
	@Column(name = "bus_id")
	private Integer busId;

	@Column(name = "source")
	private String source;

	@Column(name = "destination")
	private String destination;

	@Column(name = "available_seats")
	private int availableSeats;

	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
	private List<Seat> seats;

	public void addSeat(Seat seat) {
		if (this.seats == null)
			this.seats = new ArrayList<>();
		this.seats.add(seat);
		seat.setBus(this);
	}

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", source=" + source + ", destination=" + destination + ", availableSeats="
				+ availableSeats + "]";
	}

}
