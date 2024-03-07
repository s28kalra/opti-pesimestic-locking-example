package com.locking.example.pessimestic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

	@Id
	@Column(name = "wallet_id")
	private int walletId;
	
	@Column(name = "balance")
	private double balance;
	
}
