import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchForMoviesComponent } from './search-for-movies.component';

describe('SearchForMoviesComponent', () => {
  let component: SearchForMoviesComponent;
  let fixture: ComponentFixture<SearchForMoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchForMoviesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchForMoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
