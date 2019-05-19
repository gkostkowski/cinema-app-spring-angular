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

  constructor(private movieService: MovieService, private route: ActivatedRoute, private router: Router) {
    this.currentScreening = new Screening()
   }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      if (params['screeningId']) {
        const screeningId: number = +params['screeningId'];

        this.movieService.findScreening(screeningId).subscribe( screening => {
          if (screening) {
            this.currentScreening = screening;
          } else {
            this.router.navigate(['/app/movies']);
          }
        })
      }
    })
  }

}
