import utils.Menu;
import utils.MenuHandlers;

import java.util.Scanner;

public class ClientInventoryApp {

    static Scanner sc = new Scanner(System.in);
    private final Menu menu;
    private final MenuHandlers menuHandlers;

    public ClientInventoryApp() {
        menuHandlers = new MenuHandlers();
        menu = new Menu();
    }

    public static void main(String[] args) {
        ClientInventoryApp app = new ClientInventoryApp();
        app.run();
    }

    public void run() {
        menu.showMainMenu();


        boolean isExit = false;

        while (!isExit) {

            menu.promptChooseMenuItem();
            int userChoice = sc.nextInt();
            switch (userChoice) {
                case 1:
                    menuHandlers.handleGetAllBooks();
                    break;
                case 2:
                    menuHandlers.handleUploadBooks();
                    break;
                case 0:
                    System.out.println("Good buy!!!");
                    isExit = true;
                    break;
                default:
                    System.out.println("Incorrect data! Please repeat.");
            }

        }






    }


}
