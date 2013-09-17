package edu.sjsu.cmpe.library.api.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Book.BookStatus;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BooksDto;
import edu.sjsu.cmpe.library.dto.LinkDto;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/books")
public class BookResource {

//	@SuppressWarnings("serial")
//	private static final Map<String, BookStatus> BookStatusMapper = Collections
//            .unmodifiableMap(new HashMap<String, BookStatus>() {
//                { 
//                    put("available", BookStatus.Available);
//                    put("checked-out", BookStatus.CheckedOut);
//                    put("in-queue", BookStatus.InQueue);
//                    put("lost", BookStatus.Lost);
//                }
//            });

    private BookStatus updatedBookStatus;
	
    public BookResource() {
	// do nothing
    }
    
    /**
     * This function List all the books with links and action can be performed
     * @return List of all books
     */
    @GET
    @Timed(name = "list-book")
    public BooksDto getBooks() {

	BooksDto booksListResponse = new BooksDto();
	
	
	for(Iterator<Book> i = BooksDto.getBooks().iterator(); i.hasNext(); ) {
		  Book iBook = i.next();
		  booksListResponse.addLink(new LinkDto("view-book", "/books/" + iBook.getIsbn(),
					"GET"));
		  booksListResponse.addLink(new LinkDto("update-book",
					"/books/" + iBook.getIsbn(), "PUT"));
		  booksListResponse.addLink(new LinkDto("delete-book",
					"/books/" + iBook.getIsbn(), "DELETE"));
		}
	
	
	return booksListResponse;
    }
    
//    @POST
//    @Path("/test")
//    public Response testMtheod(Book test){
//    	
//    	return Response.status(201).entity(test).build();
//    	
//    }
    
   
    /**
     * This function will create and Add Book Entry to List of Books
     * @param book
     */
    @POST
    @Timed(name="create-book")
    public Response createBook(Book bookToCreate) {
    	BooksDto bookCreatedResponse = new BooksDto();
    	Book bookSavedResponse = BooksDto.addBookToStorage(bookToCreate);
    	
    	if(bookSavedResponse.getIsbn() >0)
    	{
    		bookCreatedResponse.addLink(new LinkDto("view-book", "/books/" + bookSavedResponse.getIsbn(),
						"GET"));
    		bookCreatedResponse.addLink(new LinkDto("update-book",
						"/books/" + bookSavedResponse.getIsbn(), "PUT"));
    		bookCreatedResponse.addLink(new LinkDto("delete-book",
					"/books/" + bookSavedResponse.getIsbn(), "DELETE"));
    		bookCreatedResponse.addLink(new LinkDto("create-reviews",
					"/books/" + bookSavedResponse.getIsbn() + "/reviews/", "POST"));
    	}
    	
		return Response.status(201).entity(bookCreatedResponse).build();
    }
    
    /**
     * This function is responsible to get a single book based on ISBN
     * @param isbn
     * @return
     */
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public Book getBookByIsbn(@PathParam("isbn") int isbn) {
    	return BooksDto.getBookByISBN(isbn);
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public void deleteBookByIsbn(@PathParam("isbn") int isbn){
    	BooksDto.deleteBookByISBN(isbn);
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateookByIsbn(@PathParam("isbn") int isbn,@QueryParam("status") String status){
    	
//    	updatedBookStatus = Book.BookStatusMapper.get(status);
    	Book updatedBook = BooksDto.updateBookByISBN(isbn, status);
    	
    	BooksDto bookUpdatedResponse = new BooksDto();
    	
    	bookUpdatedResponse.addLink(new LinkDto("view-book", "/books/" + updatedBook.getIsbn(),
				"GET"));
    	bookUpdatedResponse.addLink(new LinkDto("update-book",
					"/books/" + updatedBook.getIsbn(), "PUT"));
    	bookUpdatedResponse.addLink(new LinkDto("delete-book",
				"/books/" + updatedBook.getIsbn(), "DELETE"));
    	bookUpdatedResponse.addLink(new LinkDto("create-reviews",
				"/books/" + updatedBook.getIsbn() + "/reviews/", "POST"));
    	
    	if(updatedBook.getBookRating().size()>0) {
    	bookUpdatedResponse.addLink(new LinkDto("create-reviews",
				"/books/" + updatedBook.getIsbn() + "/reviews/", "GET"));
    	}
    	
    	return Response.status(200).entity(bookUpdatedResponse).build();
    }
    
    @GET
    @Path("/{isbn}/reviews")
    @Timed(name = "view-book")
    public List<Review> getBookReviews(@PathParam("isbn") int isbn) {
    	return BooksDto.getReviews(isbn);
    }
    
    @GET
    @Path("/{isbn}/reviews/{id}")
    @Timed(name = "view-book")
    public Review getBookReviewsByIsbn(@PathParam("isbn") int isbn, @PathParam("id") int reviewId) {
    	return BooksDto.getReviewsByISBN(isbn, reviewId);   	
    }
    
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-book")
    public List<Author> getBookAuthors(@PathParam("isbn") int isbn) {
    	return BooksDto.getAuthors(isbn);
    }
    
    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-book")
    public Author getBookAuthorsByIsbn(@PathParam("isbn") int isbn, @PathParam("id") int authId) {
    	return BooksDto.getAuthorsByISBN(isbn, authId);   	
    }
    
}

