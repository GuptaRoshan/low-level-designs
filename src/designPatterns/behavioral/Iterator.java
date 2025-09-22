package designPatterns.behavioral;


import java.util.ArrayList;
import java.util.List;

class Book {
    String title;

    public Book(String title) {
        this.title = title;
    }
}


class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    // Custom iterator to loop through books manually
    public LibraryIterator iterator() {
        return new LibraryIterator();
    }

    // Custom Iterator Class
    class LibraryIterator {
        private int position = 0;

        // Check if there are more books to iterate
        public boolean hasNext() {
            return position < books.size();
        }

        // Get the next book in the collection
        public Book next() {
            if (hasNext()) {
                return books.get(position++);
            } else {
                return null;
            }
        }
    }
}


public class Iterator {

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("The Hobbit"));
        library.addBook(new Book("1984"));
        library.addBook(new Book("Clean Code"));

        // Get the custom iterator
        Library.LibraryIterator iterator = library.iterator();

        // Use the iterator to print all books
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book.title);
        }
    }
}
