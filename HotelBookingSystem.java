import java.util.Scanner;

public class HotelBookingSystem {

    static Scanner sc = new Scanner(System.in);

    // Room numbers
    static int[] roomNumbers = {101, 102, 103, 201, 202, 203, 301, 302};

    // true = available, false = booked
    static boolean[] roomAvailable = {
        true, true, true,
        true, true, true,
        true, true
    };

    // Guest details
    static String guestName;
    static String phoneNumber;

    // Booking details
    static int selectedRoom;
    static String roomCategory;
    static double roomPrice;
    static int days;

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        int choice;
        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("   HOTEL ROOM BOOKING SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Guest Registration");
            System.out.println("2. Display Available Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. Calculate Bill");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    registerGuest();
                    break;

                case 2:
                    displayRooms();
                    break;

                case 3:
                    bookRoom();
                    break;

                case 4:
                    calculateBill();
                    break;

                case 5:
                    checkout();
                    break;

                case 6:
                    System.out.println("Thank you for using the Hotel Booking System!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }

    // ---------------- GUEST REGISTRATION ----------------
    static void registerGuest() {

        System.out.println("\n--- Guest Registration ---");

        System.out.print("Enter Guest Name: ");
        guestName = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        phoneNumber = sc.nextLine();

        System.out.println("Guest registered successfully!");
    }

    // ---------------- DISPLAY ROOMS ----------------
    static void displayRooms() {

        System.out.println("\n--- Room Availability ---");

        for (int i = 0; i < roomNumbers.length; i++) {

            System.out.print("Room " + roomNumbers[i] + " : ");

            if (roomAvailable[i]) {
                System.out.println("Available");
            } else {
                System.out.println("Booked");
            }
        }
    }

    // ---------------- ROOM BOOKING ----------------
    static void bookRoom() {

        System.out.println("\n--- Room Booking ---");

        System.out.println("1. Standard Room - Rs. 2000/day");
        System.out.println("2. Deluxe Room   - Rs. 3500/day");
        System.out.println("3. Suite Room    - Rs. 5000/day");

        System.out.print("Select Room Category: ");
        int categoryChoice = sc.nextInt();

        switch (categoryChoice) {

            case 1:
                roomCategory = "Standard";
                roomPrice = 2000;
                break;

            case 2:
                roomCategory = "Deluxe";
                roomPrice = 3500;
                break;

            case 3:
                roomCategory = "Suite";
                roomPrice = 5000;
                break;

            default:
                System.out.println("Invalid room category!");
                return;
        }

        displayRooms();

        System.out.print("\nEnter Room Number: ");
        selectedRoom = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < roomNumbers.length; i++) {

            if (roomNumbers[i] == selectedRoom) {

                found = true;

                if (roomAvailable[i]) {

                    roomAvailable[i] = false;

                    System.out.print("Enter number of days: ");
                    days = sc.nextInt();

                    System.out.println("\nRoom booked successfully!");
                    System.out.println("Room Number: " + selectedRoom);
                    System.out.println("Category: " + roomCategory);

                } else {
                    System.out.println("Room is already booked!");
                }

                break;
            }
        }

        if (!found) {
            System.out.println("Room number does not exist!");
        }
    }

    // ---------------- BILL CALCULATION ----------------
    static void calculateBill() {

        if (selectedRoom == 0) {
            System.out.println("Please book a room first!");
            return;
        }

        double total = roomPrice * days;
        double discount = 0;

        // Stay-based discount
        if (days >= 7) {
            discount = total * 0.15;
        } else if (days >= 4) {
            discount = total * 0.10;
        }

        double finalAmount = total - discount;

        System.out.println("\n==============================");
        System.out.println("          HOTEL BILL");
        System.out.println("==============================");

        System.out.println("Guest Name    : " + guestName);
        System.out.println("Phone Number  : " + phoneNumber);
        System.out.println("Room Number   : " + selectedRoom);
        System.out.println("Room Category : " + roomCategory);
        System.out.println("Number of Days: " + days);
        System.out.println("Price Per Day : Rs. " + roomPrice);

        System.out.println("------------------------------");

        System.out.println("Total Amount  : Rs. " + total);
        System.out.println("Discount      : Rs. " + discount);
        System.out.println("Final Amount  : Rs. " + finalAmount);

        System.out.println("==============================");
    }

    // ---------------- CHECKOUT ----------------
    static void checkout() {

        if (selectedRoom == 0) {
            System.out.println("No active booking found!");
            return;
        }

        calculateBill();

        for (int i = 0; i < roomNumbers.length; i++) {

            if (roomNumbers[i] == selectedRoom) {
                roomAvailable[i] = true;
                break;
            }
        }

        System.out.println("\nCheckout successful!");
        System.out.println("Room " + selectedRoom + " is now available.");

        // Reset booking
        guestName = null;
        phoneNumber = null;
        selectedRoom = 0;
        roomCategory = null;
        roomPrice = 0;
        days = 0;
    }
}