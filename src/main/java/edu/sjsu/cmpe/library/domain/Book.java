package edu.sjsu.cmpe.library.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.google.common.base.Preconditions.checkNotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {

	/**
	 * Private Properties
	 **/
	private static int bookKey;
	private int isbn;
	private String title;
	private Date publicationDate;
	private String language;
	private int numberOfPages;
	private BookStatus eBookStatus;
	private List<Author> authors;
	private List<Review> bookRating;
	
	@SuppressWarnings("serial")
	public static final Map<String, BookStatus> BookStatusMapper = Collections
            .unmodifiableMap(new HashMap<String, BookStatus>() {
                { 
                    put("available", BookStatus.Available);
                    put("checked-out", BookStatus.CheckedOut);
                    put("in-queue", BookStatus.InQueue);
                    put("lost", BookStatus.Lost);
                }
            });
	
	public Book() {
		this.isbn = ++bookKey;
	}
	 	
	/**
	 * Enum for Status
	 * 
	 * @author arunmalik
	 * 
	 */
	public enum BookStatus {
		Available, CheckedOut, InQueue, Lost
	}

	/**
	 * Properties
	 */

	/**
	 * ISBN get set
	 * 
	 * @return
	 */
	public int getIsbn() {
		return isbn;
	}
	

	/**
	 * Title Get Set
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title  = checkNotNull(title, "title is null");;
	}

	/**
	 * Publication Date Get-Set
	 * 
	 * @return publicationDate
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	@JsonProperty("publication-date")
	public String getPublicationDate() {
		return dateFormat.format(publicationDate);
	}
	public void setPublicationDate(String publicationDate) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.publicationDate = dateFormat.parse(publicationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	/**
	 * language Get Set
	 * 
	 * @return
	 */
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * numberOfPages Get Set
	 * 
	 * @return
	 */
	@JsonProperty("num-pages")
	public int getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	
	/**
	 * Book Status Get Set
	 * @return
	 */
	@JsonProperty("status")
	public BookStatus geteBookStatus() {
		return eBookStatus;
	}

	public void seteBookStatus(String eBookStatus) {
		this.eBookStatus = BookStatusMapper.get(eBookStatus);;
	}

	/**
	 * List Of Authors Get Set
	 * @return
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	
	/**
	 * Review Get Set
	 * @return
	 */
	@JsonProperty("reviews")
	public List<Review> getBookRating() {
		return bookRating;
	}

	public void setBookRaing(List<Review> bookRating) {
		this.bookRating = bookRating;
	}
	
}


