import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, ɵFormGroupValue, ɵTypedOrUntyped} from "@angular/forms";
import {DataSharingService} from "../services/data-sharing.service";
import {debounceTime, distinctUntilChanged, filter, finalize, switchMap, tap} from "rxjs";
import {MoviesService} from "../services/movies.service";
import {Movie} from "../models/Movie";

@Component({
  selector: 'app-admin-template',
  templateUrl: './admin-template.component.html',
  styleUrls: ['./admin-template.component.css']
})
export class AdminTemplateComponent implements OnInit {

  filteredMovies! : Movie[];
  isLoading = false;
  errorMsg! : string;
  minLengthTerm = 3;
  inputControl = new FormControl('');
  title! : string;
  searchForm! : FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private dataSharing: DataSharingService, private moviesService: MoviesService) {
  }

  ngOnInit(): void {
    this.dataSharing.currentTitle.subscribe(title => this.title = title);
    this.searchForm = this.fb.group(
      {
        title: this.fb.control("")
      }
    );
    this.inputControl.valueChanges
      .pipe(
        filter(res => {
          return res !== null && res.length >= this.minLengthTerm
        }),
        distinctUntilChanged(),
        debounceTime(1000),
        tap(() => {
          this.errorMsg = "";
          this.filteredMovies = [];
          this.isLoading = true;
        }),
      ).subscribe({
      next: value => {
        this.loadMovies(value!);
      }, error: err => {

      }
    });
  }


  onSelected(value: any) {
    console.log(value.title);
    //TODO normally this should go to the details page
    this.dataSharing.changeTitle(value.title);
    this.router.navigateByUrl('searchByTitle');
  }

  displayWith(value: any) {
    console.log('displayWith: '+value);
    return value?.title;
  }

  loadMovies(title: string) {
    this.moviesService.getMoviesByTitle(title).subscribe(
      {
        next: value => {
          this.filteredMovies = value;
          this.isLoading = false;
        }, error: err => {

        }
      }
    );
  }

  handleTitleSearch() {
    this.dataSharing.changeTitle(this.inputControl.value!);
    this.router.navigateByUrl('searchByTitle');
  }
}
