//Hotel Reservation System

import java.util.ArrayList;
import java.util.Scanner;

// Room class to manage room details
class Room {
    int roomNumber;
    String roomType; // e.g., Single, Double, Suite
    boolean isBooked;

    public Room(int roomNumber, String roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isBooked = false; // Initially all rooms are available
    }

    // Display room details
    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber + " | Type: " + roomType + 
                          " | Status: " + (isBooked ? "Booked" : "Available"));
    }
}

// Hotel class to manage reservations
class Hotel {
    ArrayList<Room> rooms = new ArrayList<>();

    // Initialize rooms in the hotel
    public Hotel() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Single"));
        rooms.add(new Room(201, "Double"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(301, "Suite"));
    }

    // Display available rooms
    public void displayAvailableRooms() {
        System.out.println("\n=== Available Rooms ===");
        for (Room room : rooms) {
            if (!room.isBooked) {
                room.displayRoomInfo();
            }
        }
    }

    // Book a room
    public void bookRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                room.isBooked = true;
                System.out.println("Room " + roomNumber + " has been successfully booked!");
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is either booked or doesn't exist.");
    }

    // Cancel a reservation
    public void cancelReservation(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isBooked) {
                room.isBooked = false;
                System.out.println("Reservation for room " + roomNumber + " has been successfully canceled.");
                return;
            }
        }
        System.out.println("Room " + roomNumber + " is either not booked or doesn't exist.");
    }

    // Display all rooms (for admin view)
    public void displayAllRooms() {
        System.out.println("\n=== Hotel Room Details ===");
        for (Room room : rooms) {
            room.displayRoomInfo();
        }
    }
}

// Main class to manage user interaction
public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View All Rooms (Admin)");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;

                case 2:
                    System.out.print("Enter room number to book: ");
                    int bookRoomNumber = scanner.nextInt();
                    hotel.bookRoom(bookRoomNumber);
                    break;

                case 3:
                    System.out.print("Enter room number to cancel reservation: ");
                    int cancelRoomNumber = scanner.nextInt();
                    hotel.cancelReservation(cancelRoomNumber);
                    break;

                case 4:
                    hotel.displayAllRooms();
                    break;

                case 5:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}