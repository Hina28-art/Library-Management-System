import java.util.*;;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String title) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            book.setAvailable(false);
            System.out.println("You have borrowed: " + book.getTitle());
        } else {
            System.out.println("Book not found or unavailable.");
        }
    }

    public void returnBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            book.setAvailable(true);
            System.out.println("You have returned: " + book.getTitle());
        } else {
            System.out.println("Book not found.");
        }
    }

    public void searchBook(String title) {
        Book book = findBook(title);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding some initial books
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("Pride and Prejudice", "Jane Austen"));

        int choice;
        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a Book");
            System.out.println("5. Remove a Book");
            System.out.println("6. Search for a Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book you want to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowTitle);
                    break;
                case 3:
                    System.out.print("Enter the title of the book you want to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 4:
                    System.out.print("Enter the title of the book: ");
                    String addTitle = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String addAuthor = scanner.nextLine();
                    library.addBook(new Book(addTitle, addAuthor));
                    break;
                case 5:
                    System.out.print("Enter the title of the book you want to remove: ");
                    String removeTitle = scanner.nextLine();
                    library.removeBook(removeTitle);
                    break;
                case 6:
                    System.out.print("Enter the title of the book you want to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchBook(searchTitle);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
