package pl.javastart.task;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        VehicleTools tools = new VehicleTools();
        try {
            tools.run();
        } catch (IOException e) {
            System.err.println("Błąd odczytu lub zapisu pliku.");
        }
    }
}
