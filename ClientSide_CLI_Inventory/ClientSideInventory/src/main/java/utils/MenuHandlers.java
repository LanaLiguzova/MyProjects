package utils;

import model.Book;
import webclientservice.AuthorClientService;
import webclientservice.BookClientService;
import webclientservice.CategoryClientService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuHandlers {

    Scanner sc = new Scanner(System.in);
    Menu menu = new Menu();
    BookClientService bookClientService = new BookClientService();
    AuthorClientService authorClientService = new AuthorClientService();
    CategoryClientService categoryClientService = new CategoryClientService();

    public void handleGetAllBooks() {
        System.out.println("------ Information about all books in the store ----------");

       List<Book> listOfBooks = bookClientService.getAllBooks().collectList().block();

        for (Book book:listOfBooks) {
            System.out.println(book.getId() + " - " + book.getTitle() + ", by " + book.getAuthor().getName() + ", quant. " + book.getStock() );
        }

        System.out.println("----------------------------------");
        System.out.println("Do you want to download this list? Y/N: ");
        String userAnswer = sc.nextLine();
        if (userAnswer.trim().equalsIgnoreCase("Y")) {
            int numberOfPosition = downloadListOfBooks(listOfBooks);
            System.out.println(numberOfPosition != 0 ? "Books were download. Number of books is " + numberOfPosition : "Something went wrong!");
        }

    }

    public int downloadListOfBooks(List<Book> listOfBooks) {
        int numberOfPosition = 0;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
        String pathToFile = "C:/Swetik/Development/MyProjects/BookStore/FilesToUpload/";
        String fileName = "books_report_" + dateFormat.format(currentDate) + ".txt";
        String fileToDownload = pathToFile + fileName;


        try (PrintWriter dataOutput = new PrintWriter(fileToDownload)) {
            for (Book book:listOfBooks) {
                dataOutput.println(book.getId() + " - " + book.getTitle() + ", by " + book.getAuthor() + ", $" + book.getPrice() + ", stock " + book.getStock() + " pc. ");
                numberOfPosition++;
            }
            dataOutput.println();
            dataOutput.println("Total positions: " + numberOfPosition);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        return numberOfPosition;
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

        Book book = createBookModel(bookTitle, categoryCode, authorCode, bookDescription, bookPrice, bookStock);

        Book createdBook = bookClientService.saveBook(book).block();

        if (createdBook == null) {
            System.out.println("Something went wrong!");
        } else {
            System.out.println("Book was created!");
        }

    }


    public void handleUploadBooksFromFile() {

        List<Book> bookList = new ArrayList<>();

        System.out.println("------- Upload from file");
        System.out.println("Please chose the path to the file: ");
        sc.nextLine();
        String pathToFile = sc.nextLine();
//        path = C:/Swetik/Development/MyProjects/BookStore/FilesToUpload/uploadApr2025.txt
        File fileToUpload = new File(pathToFile);
        try (Scanner fileInput = new Scanner(fileToUpload)) {

            while (fileInput.hasNextLine()) {
                String[] fileLine = fileInput.nextLine().split(";");
                String bookTitle = fileLine[0].trim();
                int categoryCode = Integer.parseInt(fileLine[1].trim());
                int authorCode =  Integer.parseInt(fileLine[2].trim());
                String bookDescription =  fileLine[3].trim();
                BigDecimal bookPrice = new BigDecimal(fileLine[4].trim());
                int bookStock = Integer.parseInt(fileLine[5].trim());

                Book book = createBookModel(bookTitle, categoryCode, authorCode, bookDescription, bookPrice, bookStock);
                bookList.add(book);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        bookClientService.saveAllBooks(bookList).block();

    }

    public Book createBookModel(String title, int categoryId, int authorId, String description, BigDecimal price, int quantity) {
        Book book = new Book();
        book.setTitle(title);
        book.setCategory(categoryClientService.getCategoryById(categoryId).block());
        book.setAuthor(authorClientService.getAuthorById(authorId).block());
        book.setDescription(description);
        book.setPrice(price);
        book.setStock(quantity);
        return book;
    }

}
