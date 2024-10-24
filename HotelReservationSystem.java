import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class HotelReservationSystem {
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Booking> bookings = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeRooms();

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> searchAvailableRooms();
                case 2 -> makeReservation();
                case 3 -> viewBookings();
                case 4 -> {
                    System.out.println("Exiting the system. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Initialize rooms with different categories and prices
    private static void initializeRooms() {
        rooms.add(new Room("101", "Single", 100.00));
        rooms.add(new Room("102", "Single", 100.00));
        rooms.add(new Room("201", "Double", 150.00));
        rooms.add(new Room("202", "Double", 150.00));
        rooms.add(new Room("301", "Suite", 300.00));
    }

    // Display the main menu options
    private static void displayMenu() {
        System.out.println("\n--- Hotel Reservation System ---");
        System.out.println("1. Search for Available Rooms");
        System.out.println("2. Make a Reservation");
        System.out.println("3. View Booking Details");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Search for available rooms by type
    private static void searchAvailableRooms() {
        System.out.print("Enter room type (Single, Double, Suite): ");
        String type = scanner.nextLine();

        System.out.println("\n--- Available Rooms ---");
        boolean found = false;
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(type) && room.isAvailable()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms of type: " + type);
        }
    }

    // Reservation for an available room
    private static void makeReservation() {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter room number to book: ");
        String roomNumber = scanner.nextLine();

        Room roomToBook = null;
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber) && room.isAvailable()) {
                roomToBook = room;
                break;
            }
        }

        if (roomToBook == null) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.print("Enter payment amount ($" + roomToBook.getPrice() + "): ");
        double payment = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (payment < roomToBook.getPrice()) {
            System.out.println("Insufficient payment. Booking failed.");
            return;
        }

        // Process booking
        String bookingId = UUID.randomUUID().toString();
        roomToBook.setAvailable(false);
        bookings.add(new Booking(bookingId, roomNumber, customerName, payment));
        System.out.println("Booking successful! Your booking ID is " + bookingId);
    }

    // View all bookings
    private static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            System.out.println("\n--- Booking Details ---");
            for (Booking booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}