package com.skypay.hotel;

import java.util.Date;

public class Booking {
    private final int userId;
    private final int roomNumber;
    private final Date checkIn;
    private final Date checkOut;
    private final int priceAtBooking;
    private final RoomType roomTypeAtBooking;

    public Booking(int userId, int roomNumber, Date checkIn, Date checkOut, int price, RoomType type) {
	this.userId = userId;
	this.roomNumber = roomNumber;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
	this.priceAtBooking = price;
	this.roomTypeAtBooking = type;
    }

    // Getters...

    public int getUserId() { return userId; }
    public int getRoomNumber() { return roomNumber; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
    public int getPriceAtBooking() { return priceAtBooking; }
    public RoomType getRoomTypeAtBooking() { return roomTypeAtBooking; }
}
