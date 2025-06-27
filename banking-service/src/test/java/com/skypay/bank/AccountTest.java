package com.skypay.bank;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    void testDepositWithdrawAndExceptions() {
	Account acc = new Account();

	// Dépôts valides
	acc.deposit(1000, LocalDate.of(2012, 1, 10));
	acc.deposit(2000, LocalDate.of(2012, 1, 13));
	assertDoesNotThrow(() -> acc.printStatement());

	// Retrait valide
	acc.withdraw(500, LocalDate.of(2012, 1, 14));

	// Retrait supérieur au solde
	assertThrows(IllegalArgumentException.class, () -> acc.withdraw(10000));

	// Montant négatif ou nul au dépôt
	assertThrows(IllegalArgumentException.class, () -> acc.deposit(0));
	assertThrows(IllegalArgumentException.class, () -> acc.deposit(-500));

	// Montant négatif ou nul au retrait
	assertThrows(IllegalArgumentException.class, () -> acc.withdraw(0));
	assertThrows(IllegalArgumentException.class, () -> acc.withdraw(-100));

	// Utilisation de la méthode statement (juste pour la couverture)
	acc.printStatement();
    }
}
