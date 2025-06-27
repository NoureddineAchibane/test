package com.skypay.bank;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
	Account acc = new Account();
	acc.deposit(1000, LocalDate.of(2012, 1, 10));
	acc.deposit(2000, LocalDate.of(2012, 1, 13));
	acc.withdraw(500, LocalDate.of(2012, 1, 14));
	acc.printStatement();
    }
}
