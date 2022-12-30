package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
public class MovieRepository {

    HashMap<String, Movie> MovieMap = new HashMap<>();
    HashMap<String, Director> DirectorMap = new HashMap<>();
    HashMap<String, List<String>> DirectorMovieMap = new HashMap<>();

    public void addmovie(Movie movie) {
        MovieMap.put(movie.getName(), movie);

    }

    public void addDirector(Director director) {
        DirectorMap.put(director.getName(), director);
    }

    public void movieDirectorPair(String movie, String director) {
        if (MovieMap.containsKey(movie) && DirectorMap.containsKey(director)) {

            List<String> currentMoviesByDirector = new ArrayList<>();

            if (DirectorMovieMap.containsKey(director))
                currentMoviesByDirector = DirectorMovieMap.get(director);

            currentMoviesByDirector.add(movie);

            DirectorMovieMap.put(director, currentMoviesByDirector);

        }
    }

    public Movie findMovie(String movie) {
        return MovieMap.get(movie);
    }

   public Director  FindDirector(String director){
        return DirectorMap.get(director);
   }
   public List<String> findMoviefromDirector(String director){
        List<String> movies = new ArrayList<>();
       if(DirectorMap.containsKey(director))  movies = DirectorMovieMap.get(director);
    return movies;
   }
   public List<String> getAllMovies(){
       return new ArrayList<>( MovieMap.keySet());

    }
    public void deleteDirector(String director){

        List<String> movies = new ArrayList<String>();
        if(DirectorMovieMap.containsKey(director)){
            //1. Find the movie names by director from the pair
            movies = DirectorMovieMap.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for(String movie: movies){
                if(MovieMap.containsKey(movie)){
                    MovieMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            DirectorMovieMap.remove(director);
        }

        //4. Delete the director from directorDb.
        if(DirectorMap.containsKey(director)){
            DirectorMap.remove(director);
        }
    }

    public void deleteAllDirector(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        DirectorMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: DirectorMovieMap.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: DirectorMovieMap.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(MovieMap.containsKey(movie)){
                MovieMap.remove(movie);
            }
        }
        //clearing the pair.
        DirectorMovieMap = new HashMap<>();
    }
}





