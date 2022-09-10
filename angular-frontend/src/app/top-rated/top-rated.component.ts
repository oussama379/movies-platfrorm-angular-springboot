import { Component, OnInit } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MoviesService} from "../services/movies.service";
import {Movie} from "../models/Movie";

@Component({
  selector: 'app-top-rated',
  templateUrl: './top-rated.component.html',
  styleUrls: ['./top-rated.component.css']
})
export class TopRatedComponent implements OnInit {

  topRated! : Array<Movie>;
  errorMsg! : string;

  constructor(private movieService :MoviesService) {
  }

  ngOnInit(): void {
    this.handleGetTopRated();
  }

  handleGetTopRated(){
   this.movieService.getTopRated().subscribe(
     {
       next : value => {
         this.topRated = value;
       },
       error : err => {
         this.errorMsg = err.message;
       }
     }
   );
  }

}
