package com.moviecon.InventoryProduct.Inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Builder;

@Entity
@Builder
public class InventoryMovieInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String movieId;
	
	@Lob
	private String MovieDescription;
	
	private String MovieName;
	
	private String MovieImagePath;
	
	private String MovieCategory;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieDescription() {
		return MovieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		MovieDescription = movieDescription;
	}

	public String getMovieName() {
		return MovieName;
	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public String getMovieImagePath() {
		return MovieImagePath;
	}

	public void setMovieImagePath(String movieImagePath) {
		MovieImagePath = movieImagePath;
	}

	public String getMovieCategory() {
		return MovieCategory;
	}

	public void setMovieCategory(String movieCategory) {
		MovieCategory = movieCategory;
	}
	
	public InventoryMovieInformation(String movieId, String movieDescription, String movieName, String movieImagePath,
			String movieCategory) {
		super();
		this.movieId = movieId;
		MovieDescription = movieDescription;
		MovieName = movieName;
		MovieImagePath = movieImagePath;
		MovieCategory = movieCategory;
	}

	public InventoryMovieInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InventoryMovieInformation [movieId=" + movieId + ", MovieDescription=" + MovieDescription
				+ ", MovieName=" + MovieName + ", MovieImagePath=" + MovieImagePath + ", MovieCategory=" + MovieCategory
				+ "]";
	}

}
