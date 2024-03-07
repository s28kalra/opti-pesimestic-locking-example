package com.locking.example.pessimestic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {

	private WalletService walletService;

	@GetMapping("/{walletId}/{amount}")
	public Wallet updateBalance(@PathVariable Integer walletId, @PathVariable Integer amount) {
		return walletService.updateBalance(walletId, amount);
	}

}
