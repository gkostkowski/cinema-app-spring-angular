import { Component, OnInit } from '@angular/core';
import { Screening, MovieService, Ticket } from '../movie.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-ticket-validation',
  templateUrl: './ticket-validation.component.html',
  styleUrls: ['./ticket-validation.component.scss']
})
export class TicketValidationComponent implements OnInit {
  public currentTicket: Ticket
  public ticketNumber: String
  public valid: boolean
  public invalidNumber: String

  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
  }

  onSubmit() {
    console.log("trying to validate a ticket...")
    this.valid = false
    this.invalidNumber = null
    this.movieService.validateTicket(this.ticketNumber).subscribe( ticket => {
      if (ticket) {
        this.movieService.findScreening(ticket.screening.entityId).subscribe(screening => {
          ticket.screening = this.movieService.convertDate(screening)
          ticket.screening = screening
          this.currentTicket = ticket
          this.valid = true
        })
      }
    },
      error => {
      console.log("Cannot found ticket with given number.")
        this.valid = false
        this.invalidNumber = this.ticketNumber
      })
  }

  ngOnInit() {
  }
}
