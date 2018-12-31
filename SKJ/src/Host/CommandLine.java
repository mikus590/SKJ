package Host;

import java.util.Scanner;

public class CommandLine implements Runnable {

    private Host host;

    public CommandLine(Host host) {
        this.host = host;
    }

    public void command(String command) {
        String[] commandArgs = command.split("\\s+");
        System.out.println(commandArgs[0] + " " + commandArgs[1]);
        switch (commandArgs[0]){
            case "connect":
                host.establishConn(Integer.parseInt(commandArgs[1]));
                break;
                default:
                    System.out.println("Unknown command");
                    break;
        }
    }

    @Override
    public void run() {
        Scanner userInput = new Scanner(System.in);
        while(true) {
            command(userInput.nextLine());
        }
    }
}
