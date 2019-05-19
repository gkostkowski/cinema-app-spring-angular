import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Movie, MovieService} from "../movie.service";

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.scss']
})
export class MovieDetailsComponent implements OnInit {
  public currentMovie: Movie;

  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
    this.currentMovie = new Movie();
  }


  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      if (params['movieId']) {
        const movieId: number = +params['movieId'];

        // this.movieService.findOne(movieId).pipe(
        //   switchMap(movie => {
        //     this.currentMovie = movie;
        //     // if (movie) {
        //     //   this.currentMovie = movie;
        //     //   this.movieService.makeShortDescription(this.currentMovie)
        //     // } else {
        //     //   this.router.navigate(['/app/movies']);
        //     //
        //     // }
        //     return movie;
        //   })
        // ).subscribe(
        //   movie => {
        //
        //       this.movieService.getMovieImage(this.currentMovie.id).subscribe(
        //         // image => {console.log(image);this.currentMovies[movieId].image = image; }
        //         image => {
        //           this.movieService.setImageForMovie(this.currentMovie, image);
        //           console.log("From nested sub");
        //           console.log(this.currentMovie.id);
        //         }
        //       );
        //
        //   }
        // )

        this.movieService.findOne(movieId).subscribe( movie => {
          if (movie) {
            this.currentMovie = movie;
          } else {
            this.router.navigate(['/app/movies']);
          }
        });
      }
    });



  }
}
