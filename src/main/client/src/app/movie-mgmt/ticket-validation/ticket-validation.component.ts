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

  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
  }

  onSubmit() {
    console.log("trying to validate a ticket...")
    this.movieService.validateTicket(this.ticketNumber).subscribe( ticket => {
      if (ticket) {
        ticket.screening = this.movieService.convertDate(ticket.screening)
        this.currentTicket = ticket
      }
    })
  }

  ngOnInit() {
  }
}
