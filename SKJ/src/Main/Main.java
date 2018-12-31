package Main;

import Host.Host;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String hostDir = "/home/"; //Torrent_ID_HOst
        int hostPort = 0;
        hostPort = userInput.nextInt();
        new Host(hostPort);


    }
}
