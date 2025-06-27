package com.skypay.hotel;

import java.util.*;

public class Service {
    private final List<Room> rooms = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType type, int pricePerNight) {
	Room r = findRoom(roomNumber);
	if (r != null) {
	    r.setType(type);
	    r.setPricePerNight(pricePerNight);
	} else {
	    rooms.add(new Room(roomNumber, type, pricePerNight));
	}
    }

    public void setUser(int userId, int balance) {
	User u = findUser(userId);
	if (u != null) {
	    u.setBalance(balance);
	} else {
	    users.add(new User(userId, balance));
	}
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
	User user = findUser(userId);
	Room room = findRoom(roomNumber);
	if (user == null || room == null)
	    throw new IllegalArgumentException("Utilisateur ou chambre introuvable.");

	if (!checkIn.before(checkOut))
	    throw new IllegalArgumentException("Dates invalides.");

	int nights = (int)((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
	if (nights < 1) throw new IllegalArgumentException("Séjour trop court.");

	if (isRoomBooked(roomNumber, checkIn, checkOut))
	    throw new IllegalArgumentException("Chambre déjà réservée pour cette période.");

	int total = room.getPricePerNight() * nights;
	if (user.getBalance() < total)
	    throw new IllegalArgumentException("Solde insuffisant.");

	user.setBalance(user.getBalance() - total);
	bookings.add(new Booking(userId, roomNumber, checkIn, checkOut, room.getPricePerNight(), room.getType()));
    }

    public void printAll() {
	System.out.println("--- Chambres ---");
	ListIterator<Room> rit = rooms.listIterator(rooms.size());
	while (rit.hasPrevious()) {
	    Room r = rit.previous();
	    System.out.printf("Room %d, %s, %d/nuit\n", r.getNumber(), r.getType(), r.getPricePerNight());
	}
	System.out.println("--- Réservations ---");
	ListIterator<Booking> bit = bookings.listIterator(bookings.size());
	while (bit.hasPrevious()) {
	    Booking b = bit.previous();
	    System.out.printf("User %d, Room %d, %s -> %s, %d/nuit, Type: %s\n",
		    b.getUserId(), b.getRoomNumber(),
		    b.getCheckIn(), b.getCheckOut(),
		    b.getPriceAtBooking(), b.getRoomTypeAtBooking());
	}
    }

    public void printAllUsers() {
	System.out.println("--- Utilisateurs ---");
	ListIterator<User> uit = users.listIterator(users.size());
	while (uit.hasPrevious()) {
	    User u = uit.previous();
	    System.out.printf("User %d, solde: %d\n", u.getId(), u.getBalance());
	}
    }

    private Room findRoom(int number) {
	return rooms.stream().filter(r -> r.getNumber() == number).findFirst().orElse(null);
    }

    User findUser(int id) {
	return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    private boolean isRoomBooked(int roomNumber, Date checkIn, Date checkOut) {
	for (Booking b : bookings) {
	    if (b.getRoomNumber() == roomNumber &&
		    !(b.getCheckOut().before(checkIn) || b.getCheckIn().after(checkOut))) {
		return true;
	    }
	}
	return false;
    }
}
