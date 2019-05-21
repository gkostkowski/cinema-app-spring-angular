import { Component, OnInit } from '@angular/core';
import { Screening, MovieService } from '../movie.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-ticket-order',
  templateUrl: './ticket-order.component.html',
  styleUrls: ['./ticket-order.component.scss']
})
export class TicketOrderComponent implements OnInit {
  public currentScreening: Screening
  public freePlacesCount: number

  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
    this.currentScreening = new Screening()
   }

   countFreeSeats() {
     return this.currentScreening.screeningRoom.seats.filter(seat => seat.free).length
   }

   public buyTicket(price: number) {
     console.log("trying to save ticket...")
     this.movieService.saveTicket(this.currentScreening.id, price).subscribe( ticket => {
       if (ticket) {
         this.currentScreening = this.movieService.convertDate(ticket.screening);
         this.freePlacesCount = this.countFreeSeats()
       }
     })
   }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      if (params['screeningId']) {
        const screeningId: number = +params['screeningId'];

        this.movieService.findScreening(screeningId).subscribe( screening => {
          if (screening) {
            this.currentScreening = this.movieService.convertDate(screening);
            this.freePlacesCount = this.countFreeSeats()
          } else {
            this.router.navigate(['/app/movies']);
          }
        })
      }
    })
  }
}
