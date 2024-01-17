package org.example.ejb;

import lombok.RequiredArgsConstructor;
import org.example.entities.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@RequiredArgsConstructor
public class BookService {
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());


    @PersistenceContext(name = "PersistenceUnit")
    private EntityManager entityManager;



    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }


    public List<Book> getAllBooks() {
        LOGGER.info("entityManager: " + entityManager);
        Query query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = query.getResultList();
        return  books;
    }

    public Book getBookById(Long bookId) {
        return entityManager.find(Book.class, bookId);
    }

    public void addBook(Book book) {
        Book book1 = new Book();
        book1.setAuthor(book.getAuthor());
        book1.setTitle(book.getTitle());
        book1.setPrice(book.getPrice());
        entityManager.persist(book1);
    }

    public void updateBook(Book updatedBook) {
        entityManager.merge(updatedBook);
    }

    public String deleteBook(Long bookId) {
        Book book = entityManager.find(Book.class, bookId);
        if (book != null) {
            entityManager.remove(book);
            return "book with id:" + bookId + " is deleted";
        }
        else {
            return "no such book exists";
        }
    }
}

