package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	
	private static int reviewKey;
	private int id;
	private Rating ratingValue;
	private String comments;
	
	public Review() {
		this.id = ++reviewKey;
	}
	
	public  enum Rating {
		OneStar, TwoStar, ThreeStar, FourStar, FiveStar
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("rating")
	public Rating getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(Rating ratingValue) {
		this.ratingValue = ratingValue;
	}

	@JsonProperty("comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
