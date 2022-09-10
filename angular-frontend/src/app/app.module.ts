import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import {AdminTemplateComponent} from "./admin-template/admin-template.component";
import {RouterModule, Routes} from "@angular/router";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MessageBoxComponent } from './message-box/message-box.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatButton, MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import { TopRatedComponent } from './top-rated/top-rated.component';
import {MatCardModule} from "@angular/material/card";
import {MatGridListModule} from "@angular/material/grid-list";
import { HomePageComponent } from './home-page/home-page.component';
import { SearchForMoviesComponent } from './search-for-movies/search-for-movies.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import {MatListModule} from "@angular/material/list";

const routes: Routes = [
  {path : "users", component : UsersComponent},
  {path : "topRated", component : TopRatedComponent},
  {path : "searchByTitle", component : SearchForMoviesComponent},
  {path : "movieDetails/:idMovie", component : MovieDetailsComponent},
  {path : "", component : HomePageComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    AdminTemplateComponent,
    MessageBoxComponent,
    TopRatedComponent,
    HomePageComponent,
    SearchForMoviesComponent,
    MovieDetailsComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        NgbModule,
        MatPaginatorModule,
        RouterModule.forRoot(routes),
        BrowserAnimationsModule,
        MatDialogModule,
        MatCardModule,
        MatGridListModule,
        MatFormFieldModule,
        MatAutocompleteModule,
        MatInputModule,
        MatListModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
