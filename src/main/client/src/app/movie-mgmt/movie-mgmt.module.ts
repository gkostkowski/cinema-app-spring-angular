import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import { MovieOverviewComponent } from './movie-overview/movie-overview.component';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { TicketOrderComponent } from './ticket-order/ticket-order.component';

@NgModule({
  declarations: [MovieOverviewComponent, MovieDetailsComponent, TicketOrderComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ]
})
export class MovieMgmtModule { }
