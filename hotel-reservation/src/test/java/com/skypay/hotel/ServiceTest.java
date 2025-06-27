package com.skypay.hotel;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    @Test
    void testBookingAndBalances() throws Exception {
	Service s = new Service();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	// Création de chambres et utilisateurs
	s.setRoom(1, RoomType.STANDARD, 1000);
	s.setRoom(2, RoomType.SUITE, 2000);
	s.setUser(1, 2000);
	s.setUser(2, 10000);

	// Cas standard : réservation réussie
	s.bookRoom(1, 1, sdf.parse("10/07/2026"), sdf.parse("12/07/2026"));

	// Conflit de réservation (même chambre, période chevauchante)
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(1, 1, sdf.parse("11/07/2026"), sdf.parse("13/07/2026"))
	);

	// Solde insuffisant pour user 1 (reste 0)
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(1, 2, sdf.parse("13/07/2026"), sdf.parse("14/07/2026"))
	);

	// Utilisateur inexistant
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(999, 1, sdf.parse("15/07/2026"), sdf.parse("16/07/2026"))
	);

	// Chambre inexistante
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(1, 999, sdf.parse("15/07/2026"), sdf.parse("16/07/2026"))
	);

	// Dates invalides (checkIn après checkOut)
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(2, 2, sdf.parse("20/07/2026"), sdf.parse("15/07/2026"))
	);

	// Séjour trop court (0 nuit)
	assertThrows(IllegalArgumentException.class, () ->
		s.bookRoom(2, 2, sdf.parse("20/07/2026"), sdf.parse("20/07/2026"))
	);

	// Réservation valide pour un autre utilisateur/chambre
	s.bookRoom(2, 2, sdf.parse("13/07/2026"), sdf.parse("15/07/2026"));
	assertEquals(10000 - 2*2000, s.findUser(2).getBalance());
    }
}

