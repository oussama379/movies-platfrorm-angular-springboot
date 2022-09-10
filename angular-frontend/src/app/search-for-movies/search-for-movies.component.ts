import {Component, Input, OnInit} from '@angular/core';
import {Movie} from "../models/Movie";
import {MoviesService} from "../services/movies.service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {DataSharingService} from "../services/data-sharing.service";

@Component({
  selector: 'app-search-for-movies',
  templateUrl: './search-for-movies.component.html',
  styleUrls: ['./search-for-movies.component.css']
})
export class SearchForMoviesComponent implements OnInit {

  moviesByTitles! : Movie[];
  title! : string | null;
  errorMsg! : string;
  color : string = '#000000';

  constructor(private movieService : MoviesService, private route : ActivatedRoute, private dataSharing : DataSharingService) {
    this.handleGetMoviesByTitle();
  }

  ngOnInit(){
    this.dataSharing.currentTitle.subscribe(title => {
      this.title = title;
      console.log(this.title);
      this.handleGetMoviesByTitle();
    });
  }

  handleGetMoviesByTitle(){
    if (this.title != null) {
      this.movieService.getMoviesByTitle(this.title).subscribe(
        {
          next: value => {
            this.moviesByTitles = value;
            //this.moviesByTitles = this.moviesByTitles.slice(0, 4);
            console.log(this.moviesByTitles);
          },
          error: err => {
            this.errorMsg = err.message;
          }
        }
      );
    }
  }


}
