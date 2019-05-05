import { Component, OnInit } from '@angular/core';
import {Movie, MovieService} from "../movie.service";

@Component({
  selector: 'app-movie-overview',
  templateUrl: './movie-overview.component.html',
  styleUrls: ['./movie-overview.component.scss']
})
export class MovieOverviewComponent implements OnInit {
  currentMovies: Movie[];

  constructor(private movieService: MovieService) {
  }

  ngOnInit(): void {
    this.movieService.findAll().subscribe(
      movies => this.currentMovies = movies
    );
  }

}
