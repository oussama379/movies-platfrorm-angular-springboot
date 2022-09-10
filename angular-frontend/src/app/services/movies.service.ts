import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Movie} from "../models/Movie";

@Injectable({
  providedIn: 'root'
})
export class MoviesService {

  constructor(private http : HttpClient) { }


  getTopRated() : Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(environment.backendUrl+"/tmdb/topRated");
  }

  getPopular() : Observable<any> {
    return this.http.get<Array<Movie>>(environment.backendUrl+"/tmdb/popular");
  }
  getUpcoming() : Observable<any> {
    return this.http.get<Array<Movie>>(environment.backendUrl+"/tmdb/upcoming");
  }
  getTrending() : Observable<any> {
    return this.http.get<Array<Movie>>(environment.backendUrl+"/tmdb/trending");
  }

  getMoviesByTitle(movieTitle : string) : Observable<Movie[]> {
    return this.http.get<Movie[]>(environment.backendUrl+"/tmdb/search/"+movieTitle);
  }
  getMovieById(id : number) : Observable<any> {
    return this.http.get<any>(environment.backendUrl+"/tmdb/"+id);
  }


}
