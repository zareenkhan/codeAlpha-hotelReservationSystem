
class Booking {
    private String bookingId;
    private String roomNumber;
    private String customerName;
    private double amountPaid;

    public Booking(String bookingId, String roomNumber, String customerName, double amountPaid) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.amountPaid = amountPaid;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Room Number: " + roomNumber + ", Customer Name: " + customerName + ", Amount Paid: $" + amountPaid;
    }
}