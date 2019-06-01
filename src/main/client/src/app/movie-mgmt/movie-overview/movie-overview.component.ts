import {Component, OnInit} from '@angular/core';
import {Movie, MovieService} from "../movie.service";
import {switchMap} from "rxjs/operators";

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
    this.movieService.findAll().pipe(
      switchMap(movies => {
        this.currentMovies = movies;
        for (let movieId in this.currentMovies) {
          this.movieService.makeShortDescription(this.currentMovies[movieId])
          this.movieService.makeGenresStr(this.currentMovies[movieId])
        }
        return movies;
      })
    ).subscribe(
      movies => {
        for (let movieId in this.currentMovies) {
          this.movieService.getMovieImage(this.currentMovies[movieId].entityId).subscribe(
            // image => {console.log(image);this.currentMovies[movieId].image = image; }
            image => {
              this.movieService.setImageForMovie(this.currentMovies[movieId], image);
              console.log("From nested sub");
              console.log(this.currentMovies[movieId].entityId);
            }
          );
          this.movieService.findScreenings(this.currentMovies[movieId].entityId).subscribe(
            screenings => this.movieService.setScreenings(this.currentMovies[movieId], screenings)
          )
        }
      }
    )

  }

}
