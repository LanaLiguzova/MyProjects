package utils;

import model.Book;
import webclientservice.AuthorClientService;
import webclientservice.BookClientService;
import webclientservice.CategoryClientService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuHandlers {

    Scanner sc = new Scanner(System.in);
    Menu menu = new Menu();
    BookClientService bookClientService = new BookClientService();
    AuthorClientService authorClientService = new AuthorClientService();
    CategoryClientService categoryClientService = new CategoryClientService();


    public void handleUserChoiceMainMenu(int userChoice) {

    }

    public void handleGetAllBooks() {
        System.out.println("------ Information about all books in the store ----------");

       List<Book> listOfBooks = bookClientService.getAllBooks().collectList().block();

        for (Book book:listOfBooks) {
            System.out.println(book.getId() + " - " + book.getTitle() + ", by " + book.getAuthor().getName() + ", quant. " + book.getStock() );
        }


    }

    public void handleUploadBooks() {

        menu.showBooksMenu();

        boolean isExit = false;

        while (!isExit) {
            menu.promptChooseMenuItem();
            int userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    handleUploadBooksByHand();
                    break;
                case 2:
                    handleUploadBooksFromFile();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    menu.showMainMenu();
                    isExit = true;

            }
        }

    }

    public void handleUploadBooksByHand() {
        menu.promptEnterEntity("book");
        /*  this.id = id;
        this.title = title;
        this.category = category;
        this.author = author;
        this.description = description;
        this.price = price;
        this.stock = stock;*/
        System.out.print("Book title: ");
        sc.nextLine();
        String bookTitle = sc.nextLine();
        System.out.print("\nCategory (code): ");
        int categoryCode = sc.nextInt();
        System.out.print("\nAuthor code:");
        int authorCode = sc.nextInt();
        System.out.print("\nDescription: ");
        sc.nextLine();
        String bookDescription = sc.nextLine();
        System.out.print("\nPrice: ");
        BigDecimal bookPrice = sc.nextBigDecimal();
        System.out.print("\nQuantity: ");
        int bookStock = sc.nextInt();

        Book book = new Book();
        book.setTitle(bookTitle);
        book.setCategory(categoryClientService.getCategoryById(categoryCode).block());
        book.setAuthor(authorClientService.getAuthorById(authorCode).block());
        book.setDescription(bookDescription);
        book.setPrice(bookPrice);
        book.setStock(bookStock);

        Book createdBook = bookClientService.saveBook(book).block();

        if (createdBook == null) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Book was created!");
        }

    }


    public void handleUploadBooksFromFile() {
        System.out.println("From file");
    }

}
