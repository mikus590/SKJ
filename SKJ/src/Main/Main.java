package Main;

import Host.Host;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        int hostPort = 0;
        System.out.println("Podaj ID Hosta");
        hostPort = userInput.nextInt();
        new Host(hostPort);
        System.out.println("Host o ID " + hostPort + " został utworzony");
    }
}
