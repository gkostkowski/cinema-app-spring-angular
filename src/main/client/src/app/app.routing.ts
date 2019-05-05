import {Routes} from '@angular/router';
import {MovieOverviewComponent} from "./movie-mgmt/movie-overview/movie-overview.component";
import {MovieDetailsComponent} from "./movie-mgmt/movie-details/movie-details.component";

export const APP_ROUTES: Routes = [
  {
    path: 'app',
    children: [
      {
        path: 'movies',
        component: MovieOverviewComponent
      },
      {
        path: 'movie',
        component: MovieDetailsComponent
      },
      {
        path: 'movie/:movieId',
        component: MovieDetailsComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: '/app/movies',
    pathMatch: 'full'
  }
];
