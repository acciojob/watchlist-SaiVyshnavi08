package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

   @Autowired
    MovieService movieService;

   @PostMapping("/add-movie")
    public ResponseEntity<String> addmovie (@RequestBody() Movie movie){
       movieService.addmovie(movie);
       return new ResponseEntity<>("New Movie Added Succesfully" , HttpStatus.OK);
   }

   @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
      movieService.adddirector(director);
      return new ResponseEntity<>("New Director Added Succesfully" , HttpStatus.CREATED);
    }

    @PutMapping("/add -movie-director-pair")
    public ResponseEntity<String> moviedirectorPair (@RequestParam("movie") String movie , @RequestParam("director") String director){
       movieService.MovieDirectorPair(movie ,director);
       return new ResponseEntity<>("Paired Succesfully" , HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie movie = movieService.findMovie(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
       Director director = movieService.getDirector(name);
       return new ResponseEntity<>(director , HttpStatus.CREATED);
    }

    @GetMapping("get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getmoviesByDirectorName (@PathVariable String name){
       List<String>  movies = movieService.getMovieList(name);
       return new ResponseEntity<>(movies , HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> allmovies(){
     List<String> moviesList = movieService.getAllMovies();
     return new ResponseEntity<>(moviesList ,HttpStatus.ACCEPTED );
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(director + " removed successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted successfully", HttpStatus.CREATED);
    }

}
