package com.locking.example.optimistic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

	public enum SeatType {
		SEATER, SEMI_SLEEPER, SLEEPER;
	}

	public enum Status {
		FREE, PARTIALLY_BOOKED, BOOKED;
	}

	@Id
	@Column(name = "seat_id")
	private Integer seatId;

	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type")
	private SeatType seatType;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;

	@Version
	private int version;

	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", seatType=" + seatType + ", status=" + status + ", version=" + version
				+ "]";
	}

}
