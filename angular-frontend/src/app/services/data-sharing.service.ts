import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataSharingService {

  private movieTitleSource = new BehaviorSubject<string>("");
  currentTitle = this.movieTitleSource.asObservable();
  constructor() { }

 changeTitle(title : string){
    this.movieTitleSource.next(title);
 }
}
