import {Routes} from '@angular/router';
import {MovieOverviewComponent} from "./movie-mgmt/movie-overview/movie-overview.component";
import {MovieDetailsComponent} from "./movie-mgmt/movie-details/movie-details.component";
import {TicketOrderComponent} from "./movie-mgmt/ticket-order/ticket-order.component";
import { TicketValidationComponent } from './movie-mgmt/ticket-validation/ticket-validation.component';

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
      },
      {
        path: 'screening/:screeningId',
        component: TicketOrderComponent
      },
      {
        path: 'ticket',
        component: TicketValidationComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: '/app/movies',
    pathMatch: 'full'
  }
];
