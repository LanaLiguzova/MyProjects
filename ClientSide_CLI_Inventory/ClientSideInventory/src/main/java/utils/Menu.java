package utils;

public class Menu {

    //Main menu
    private final static String MAIN_MANU_TITLE = "*********** INVENTORY SYSTEM *************" +
            "\n************** Main Menu  ****************" ;
    private final static String MAIN_MANU_GET_ALL_BOOKS = "1. Get all books";
    private final static String MAIN_MANU_UPLOAD_BOOKS = "2. Upload books";
    private final static String MAIN_MANU_EXIT = "0. Exit";

    // Upload books menu
    private final static String BOOKS_MENU_TITLE = "*********** Book Menu  *************";
    private final static String BOOKS_MENU_UPLOAD_BY_HANDS = "1. Upload books by hands";
    private final static String BOOKS_MENU_UPLOAD_FROM_FILE = "2. Upload books from file";
    private final static String BOOKS_MENU_EXIT = "0. Return to main menu";

    //prompts
    private final static String PROMPT_CHOOSE_MENU_ITEM = "Choose menu item: ";
    private final static String PROMPT_ENTER_ENTITY = "Please enter data for " ;

    public void showMainMenu() {
        System.out.println(MAIN_MANU_TITLE);
        System.out.println(MAIN_MANU_GET_ALL_BOOKS);
        System.out.println(MAIN_MANU_UPLOAD_BOOKS);
        System.out.println(MAIN_MANU_EXIT);
    }

    public void showBooksMenu() {
        System.out.println(BOOKS_MENU_TITLE);
        System.out.println(BOOKS_MENU_UPLOAD_BY_HANDS);
        System.out.println(BOOKS_MENU_UPLOAD_FROM_FILE);
        System.out.println(BOOKS_MENU_EXIT);
    }

    public void promptChooseMenuItem() {
        System.out.println(PROMPT_CHOOSE_MENU_ITEM);
    }

    public void promptEnterEntity(String entityName) {
        System.out.println(PROMPT_ENTER_ENTITY + entityName + ": ");
    }






}
