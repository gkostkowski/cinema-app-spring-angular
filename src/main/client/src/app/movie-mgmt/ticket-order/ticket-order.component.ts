import { Component, OnInit } from '@angular/core';
import { Screening, MovieService, Ticket } from '../movie.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-ticket-order',
  templateUrl: './ticket-order.component.html',
  styleUrls: ['./ticket-order.component.scss']
})
export class TicketOrderComponent implements OnInit {
  public currentScreening: Screening
  public freePlacesCount: number
  public currentTicket: Ticket
  public boughtTickets: Ticket[]
  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
    this.currentScreening = new Screening()
    this.boughtTickets = []
   }

   countFreeSeats() {
     // return this.currentScreening.screeningRoom.seats.filter(seat => seat.free).length
     // return this.currentScreening.screeningRoom.seats.length - this.currentScreening.orderedTickets.length
     var allPlaces = this.currentScreening.screeningRoom.places
     var reservedPlaces = this.currentScreening.tickets.length + this.boughtTickets.length
     return allPlaces - reservedPlaces
   }

   public buyTicket(price: number) {
     console.log("trying to save ticket...")
     this.movieService.saveTicket(this.currentScreening.entityId, price).subscribe( ticket => {
       if (ticket) {
         console.log("New ticket")
         console.log(ticket)
         console.log("Current screening")
         console.log(this.currentScreening)
         // this.currentScreening = this.movieService.convertDate(this.currentScreening);
         // this.currentScreening = this.movieService.convertDate(ticket.screening);
         this.freePlacesCount = this.countFreeSeats()
         this.currentTicket = ticket
         this.boughtTickets.push(ticket)
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
            this.movieService.findScreeningRoomForScreening(this.currentScreening.entityId).subscribe(
              screeningRoom => {
                this.currentScreening.screeningRoom = screeningRoom;
                this.freePlacesCount = this.countFreeSeats()
              }
            )

          } else {
            this.router.navigate(['/app/movies']);
          }
        })
      }
    })
  }
}
