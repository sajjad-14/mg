import java.util.Scanner;

class CinemaHall {
    private int seatsAvailable;

    public CinemaHall(int seats) {
        this.seatsAvailable = seats;
    }

    // Synchronized method to book seats
    public synchronized boolean bookSeats(int numberOfSeats) {
        if (numberOfSeats <= seatsAvailable) {
            seatsAvailable -= numberOfSeats;
            System.out.println(Thread.currentThread().getName() + " booked " + numberOfSeats + " seats. Seats left: " + seatsAvailable);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + " could not book " + numberOfSeats + " seats. Only " + seatsAvailable + " seats left.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private CinemaHall hall;
    private int seatsToBook;

    public BookingThread(CinemaHall hall, int seatsToBook, String threadName) {
        super(threadName);
        this.hall = hall;
        this.seatsToBook = seatsToBook;
    }

    @Override
    public void run() {
        // Try to book seats
        hall.bookSeats(seatsToBook);
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the total number of seats in the cinema hall:");
        int totalSeats = scanner.nextInt();

        CinemaHall cinemaHall = new CinemaHall(totalSeats);

        System.out.println("Enter the number of booking threads:");
        int numThreads = scanner.nextInt();

        BookingThread[] threads = new BookingThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            System.out.println("Enter the number of seats to book for thread " + (i + 1) + ":");
            int seats = scanner.nextInt();

            threads[i] = new BookingThread(cinemaHall, seats, "Thread " + (i + 1));
        }

        // Start three threads at a time
        int index = 0;
        while (index < numThreads) {
            int count = 0;
            while (count < 3 && index < numThreads) {  // Only three threads at a time
                threads[index].start();
                index++;
                count++;
            }

            for (int j = index - count; j < index; j++) {
                try {
                    threads[j].join();  // Wait for the three threads to finish
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
