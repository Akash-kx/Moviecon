package com.moviecon.InventoryProduct.InventoryController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.moviecon.InventoryProduct.Inventory.InventoryMovieInformation;
import com.moviecon.InventoryProduct.InventoryService.MovieInventoryService;

import jakarta.validation.constraints.NotBlank;

@RestController
@Validated
public class MovieInventoryController {
	
	@Autowired
	private MovieInventoryService InventoryService;
	
	@PostMapping("/AddMovieInInventory")
	public String AddProductInInventory( @RequestParam("MovieImage") MultipartFile MovieImage
			, @RequestParam("MovieDescription") @NotBlank String MovieDescription
			, @RequestParam("MovieName") @NotBlank String MovieName 
			, @RequestParam("MovieCategory") @NotBlank String MovieCategory ) {
		
		if( MovieImage.getSize() <= 0 ) {
			
			throw new MultipartException("Image is not present");
		}

		String AddMovieStatus = InventoryService.AddMovieInInventory(MovieImage, MovieDescription, MovieName, MovieCategory);
		
		return AddMovieStatus != null ? "Movie Detail Added" : "Move Already Exist";
	}
	
	@GetMapping("/ShowAllMovieInInventory")
	public List<InventoryMovieInformation> ShowAllProductInInventory() {
		
		List<InventoryMovieInformation> AllDataInventory = InventoryService.GetAllMovieInInventory();
		
		return AllDataInventory.size() == 0 ? new ArrayList<>() : AllDataInventory;
	}
	
	@GetMapping("/ShowSearchedMovie/{MovieName}")
	public List<InventoryMovieInformation> ShowAllSearchedProduct( @PathVariable @NotBlank String MovieName ) {
		
		List<InventoryMovieInformation> AllSearchedMovie = InventoryService.GetSearchedMovie(MovieName);
		
		return AllSearchedMovie.size() == 0 ? new ArrayList<>() : AllSearchedMovie;
	}

}