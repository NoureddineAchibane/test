package com.skypay.bank;

import java.time.LocalDate;
import java.util.*;

public class Account implements AccountService {
    private static class Transaction {
	final LocalDate date;
	final int amount;
	final int balance;

	Transaction(LocalDate date, int amount, int balance) {
	    this.date = date;
	    this.amount = amount;
	    this.balance = balance;
	}
    }

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    @Override
    public void deposit(int amount) {
	if (amount <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
	balance += amount;
	transactions.add(new Transaction(LocalDate.now(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
	if (amount <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
	if (amount > balance) throw new IllegalArgumentException("Solde insuffisant.");
	balance -= amount;
	transactions.add(new Transaction(LocalDate.now(), -amount, balance));
    }

    @Override
    public void printStatement() {
	System.out.println("Date       || Montant || Solde");
	ListIterator<Transaction> it = transactions.listIterator(transactions.size());
	while (it.hasPrevious()) {
	    Transaction t = it.previous();
	    System.out.printf("%s || %d || %d\n", t.date, t.amount, t.balance);
	}
    }

    // Pour les tests : injection de date
    void deposit(int amount, LocalDate date) {
	if (amount <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
	balance += amount;
	transactions.add(new Transaction(date, amount, balance));
    }

    void withdraw(int amount, LocalDate date) {
	if (amount <= 0) throw new IllegalArgumentException("Le montant doit être positif.");
	if (amount > balance) throw new IllegalArgumentException("Solde insuffisant.");
	balance -= amount;
	transactions.add(new Transaction(date, -amount, balance));
    }
}
