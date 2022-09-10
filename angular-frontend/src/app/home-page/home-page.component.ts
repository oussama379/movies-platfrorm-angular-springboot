import { Component, OnInit } from '@angular/core';
import {Movie} from "../models/Movie";
import {MoviesService} from "../services/movies.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  topRated! : Movie[];
  popular! : Movie[];
  upcoming! : Movie[];
  trending! : Movie[];
  errorMsg! : string;
  color : string = '#000000';


  constructor(private movieService : MoviesService, private router : Router) {
  }

  ngOnInit(): void {
    this.handleGetTopRated();
    this.handleGetPopular();
    this.handleGetUpcoming();
    this.handleGetTrending();
  }

  handleGetTopRated(){
    this.movieService.getTopRated().subscribe(
      {
        next : value => {
          this.topRated = value;
          this.topRated = this.topRated.slice(0, 4);
          console.log(this.topRated);
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }
  handleGetPopular(){
    this.movieService.getPopular().subscribe(
      {
        next : value => {
          this.popular = value;
          this.popular = this.popular.slice(0, 4);
          console.log(this.popular);

        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }
  handleGetUpcoming(){
    this.movieService.getUpcoming().subscribe(
      {
        next : value => {
          this.upcoming = value;
          this.upcoming = this.upcoming.slice(0, 4);
          console.log(this.upcoming);
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }

  handleGetTrending(){
    this.movieService.getTrending().subscribe(
      {
        next : value => {
          this.trending = value;
          this.trending = this.trending.slice(0, 4);
          console.log(this.trending);
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }

  handleNavigateToMovieDetails(id: number) {
    this.router.navigate(['movieDetails', id]);
  }
}
