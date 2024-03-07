package com.locking.example.pessimestic;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.jpa.SpecHints;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WalletService {

	private WalletRepository walletRepository;
	private EntityManager entityManager;

	@Transactional
	public Wallet updateBalance(Integer walletId, Integer amount) {
		System.out.println("update Balance started");

		Wallet wallet = findWithLockById(walletId);
		wallet.setBalance(wallet.getBalance() + amount);
		wallet = walletRepository.save(wallet);

		return wallet;
	}

	@Transactional(value = TxType.REQUIRED)
	private Wallet findWithLockById(Integer walletId) {
		Map<String, Object> properties = new HashMap<>();
		properties.put(SpecHints.HINT_SPEC_LOCK_TIMEOUT, 50 * 1000L);
		try {
			return entityManager.find(Wallet.class, walletId, LockModeType.PESSIMISTIC_WRITE, properties);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
