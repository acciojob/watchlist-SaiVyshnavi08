package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addmovie (Movie movie){
        movieRepository.addmovie(movie);

    }

    public void adddirector (Director director){
        movieRepository.addDirector(director);
    }

    public void MovieDirectorPair(String movie, String director){
        movieRepository.movieDirectorPair(movie , director);
    }

    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }

    public Director getDirector(String director){
        return movieRepository.FindDirector(director);
    }

    public List<String> getMovieList(String director){
        return movieRepository.findMoviefromDirector(director);
    }

    public List<String> getAllMovies(){
        return movieRepository.getAllMovies();
    }
    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }
}
