package com.moviecon.InventoryProduct.CrusRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviecon.InventoryProduct.Inventory.InventoryMovieInformation;

@Repository
public interface MovieInventoryRespository extends CrudRepository<InventoryMovieInformation, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM inventory_movie_information")
	public List<InventoryMovieInformation> findAllMovie();
	
	@Query(nativeQuery = true, value = "SELECT movie_name FROM inventory_movie_information WHERE movie_name = :MovieName")
	public String MovieExistOrNot(@Param("MovieName") String MovieName);
	
	@Query(nativeQuery= true, value= "SELECT * FROM inventory_movie_information WHERE movie_name REGEXP :MovieName")
	public List<InventoryMovieInformation> findAllSearchedMovie(@Param("MovieName") String MovieName);

}
