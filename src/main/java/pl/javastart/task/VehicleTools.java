package pl.javastart.task;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class VehicleTools {

    private static final int EXIT = 0;
    private static final int ADD_TO_QUEUE = 1;
    private static final int TAKE_FOR_SERVICE = 2;
    private Scanner scanner = new Scanner(System.in);

    void run() throws IOException {
        String fileName = "vehicles.txt";
        Queue<Vehicle> vehicles = readFile(fileName);
        int option;
        do {
            option = printAndChooseOption();
            scanner.nextLine();
            if (option == EXIT) {
                writeToFile(fileName, vehicles);
            } else if (option == ADD_TO_QUEUE) {
                addVehicleToQueue(vehicles);
            } else if (option == TAKE_FOR_SERVICE) {
                takeToService(vehicles);
            }
        } while (option != EXIT);
    }

    private void takeToService(Queue<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("Nie ma żadnych pojazdów do przeglądu.");
        } else {
            Vehicle vehicle = vehicles.poll();
            System.out.println(vehicle);
        }
    }

    private int printAndChooseOption() {
        System.out.println("Wybierz opcję:");
        System.out.println(EXIT + " - Wyjdź z programu");
        System.out.println(ADD_TO_QUEUE + " - Dodaj pojazd");
        System.out.println(TAKE_FOR_SERVICE + " - Pobierz pojazd do przeglądu");
        return scanner.nextInt();
    }

    private void addVehicleToQueue(Queue<Vehicle> vehicles) {
        System.out.println("Typ pojazdu");
        String type = scanner.nextLine();
        System.out.println("Marka pojazdu");
        String brand = scanner.nextLine();
        System.out.println("Model pojazdu");
        String model = scanner.nextLine();
        System.out.println("Rocznik pojazdu");
        int year = scanner.nextInt();
        System.out.println("Przebieg pojazdu");
        int mileage = scanner.nextInt();
        scanner.nextLine();
        System.out.println("VIN pojazdu");
        String vin = scanner.nextLine();
        vehicles.add(new Vehicle(type, brand, model, year, mileage, vin));
    }

    private void writeToFile(String fileName, Queue<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            while (!vehicles.isEmpty()) {
                Vehicle vehicle = vehicles.poll();
                writer.write(vehicle.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Nie udało się odczytać pliku");
        }
    }

    private Queue<Vehicle> readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Queue<Vehicle> vehicles = new LinkedList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(",");
            String type = split[0];
            String brand = split[1];
            String model = split[2];
            int year = Integer.parseInt(split[3]);
            int mileage = Integer.parseInt(split[4]);
            String vin = split[5];
            vehicles.add(new Vehicle(type, brand, model, year, mileage, vin));
        }
        return vehicles;
    }
}


