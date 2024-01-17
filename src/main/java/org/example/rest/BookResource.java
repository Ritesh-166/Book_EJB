package org.example.rest;

import lombok.RequiredArgsConstructor;
import org.example.ejb.BookService;
import org.example.entities.Book;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/books")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless public class BookResource {

    @EJB
    private BookService bookService;

    @GET
    @Path("/hello")
    public Response hello(){
        return Response.ok("welcome!!").build();
    }

    @GET
    @Path("/world/{name}/")
    public Response sayHello(@PathParam("name") String name) {
        String message = bookService.sayHello(name);
        return Response.ok(message).build();
    }


    @GET
    @Path("/getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @POST
    public String addBook(Book book) {
        bookService.addBook(book);
        return "new book added";
    }

    @PUT
    @Path("/{id}")
    public String updateBook(@PathParam("id") Long bookId, Book updatedBook) {
        updatedBook.setId(bookId);
        bookService.updateBook(updatedBook);
        return "book info updated";
    }

    @DELETE
    @Path("/{id}")
    public String deleteBook(@PathParam("id") Long bookId) {
       return bookService.deleteBook(bookId);

    }
}

