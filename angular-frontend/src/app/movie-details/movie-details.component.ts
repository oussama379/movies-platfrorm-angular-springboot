import { Component, OnInit } from '@angular/core';
import {MoviesService} from "../services/movies.service";
import {ActivatedRoute} from "@angular/router";
import {Trailer} from "../models/Trailer";
import {Actor} from "../models/Actor";
import {Movie} from "../models/Movie";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  currentRate = 8;

  idMovie! : number;
  movie! : Movie;
  actors! : Array<Actor>;
  directors! : Array<Actor>;
  trailers! : Array<Trailer>;
  similarMovies! : Array<Movie>;
  constructor(private movieService : MoviesService, private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.idMovie = +this.route.snapshot.paramMap.get('idMovie')!;
    this.handleGetMovieById(this.idMovie);
  }

  handleGetMovieById(id : number){
    this.movieService.getMovieById(id).subscribe({
      next : value => {
        this.actors = value.cast_and_crew;
        this.directors = value.cast_and_crew;
        this.actors = this.actors.filter(actor => actor.known_for_department === "Acting");
        this.directors = this.directors.filter(actor => actor.known_for_department === "Directing");

        console.log(this.actors);
        console.log(this.directors);

        this.movie = value.movie_fields;
        console.log(this.movie);

        this.trailers = value.movie_trailers;
        this.trailers = this.trailers.filter(trailer => trailer.official === "true");
        console.log(this.trailers);

        this.similarMovies = value.similar_movies.slice(0, 10);
        console.log(this.similarMovies);
      }, error : err => {

      }
    })
  }

}
