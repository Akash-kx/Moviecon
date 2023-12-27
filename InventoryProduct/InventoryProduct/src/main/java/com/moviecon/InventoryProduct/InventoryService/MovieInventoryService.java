package com.moviecon.InventoryProduct.InventoryService;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moviecon.InventoryProduct.CrusRepository.MovieInventoryRespository;
import com.moviecon.InventoryProduct.Inventory.InventoryMovieInformation;

@Service
public class MovieInventoryService {
	
	@Autowired
	private MovieInventoryRespository InventoryRepository;
	
	private String MovieImageStoredFolder = "F:/MovieCon/MovieImages/";
	
	public boolean MovieExist( String MovieName ) {
		
		String ExistingMovieName = InventoryRepository.MovieExistOrNot(MovieName);
		
		if( ExistingMovieName != null ) {
			
			return true;
		}
		
		return false;
	}

	public String AddMovieInInventory(MultipartFile movieImage, String movieDescription, String movieName, String movieCategory) {
		
		if( MovieExist( movieName ) ) {
			
			return null;
		}
		
		else {
			
			String MovieImagePathDatabase = MovieImageStoredFolder + movieImage.getOriginalFilename();
			
			InventoryRepository
			.save(InventoryMovieInformation.builder()
					.MovieCategory(movieCategory)
					.MovieDescription(movieDescription)
					.MovieName(movieName)
					.MovieImagePath(MovieImagePathDatabase)
					.build());
			
			try {
				
				movieImage.transferTo(new File(MovieImagePathDatabase));
			}
			catch( Exception ex ) {
				
				System.out.println(ex);
			}
			
			return "File Is Been Added";
		}

	}

	public List<InventoryMovieInformation> GetAllMovieInInventory() {
		
		return InventoryRepository.findAllMovie();
	}

	public List<InventoryMovieInformation> GetSearchedMovie(String MovieName) {
		
		if(MovieName == null) {
			
			System.out.println("Empty");
		}
		
		MovieName = MovieName.replaceAll("[^a-zA-Z]", "");
		MovieName = MovieName.replaceAll("\\s", "");
		
		return InventoryRepository.findAllSearchedMovie(MovieName);
	}

}