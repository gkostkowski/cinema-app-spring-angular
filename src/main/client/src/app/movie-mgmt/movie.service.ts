import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {DomSanitizer} from "@angular/platform-browser";
import {Promise} from "../../../node/node_modules/npm/node_modules/npm-profile/node_modules/make-fetch-happen/node_modules/socks-proxy-agent/node_modules/agent-base/node_modules/es6-promisify/node_modules/es6-promise";


@Injectable()
export class MovieService {

  constructor(private httpClient: HttpClient,private sanitizer : DomSanitizer) {
  }

  findAll(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>('services/rest/movies');
  }

  findOne(movieId:number): Observable<Movie> {
    return this.httpClient.get<Movie>(`services/rest/movies/${movieId}`);
  }

  findScreenings(movieId): Observable<Screening[]> {
    return this.httpClient.get<Screening[]>(`services/rest/movie/${movieId}/screenings`);
  }

  findScreening(screeningId: number): Observable<Screening> {
    return this.httpClient.get<Screening>(`services/rest/screenings/${screeningId}`);
  }

  getMovieImage(movieId: number):Observable<Blob> {
    return this.httpClient.get(`services/rest/movies/img/${movieId}`,
      {
        responseType: "blob"
      });
  }

  saveTicket(screeningId: number, price: number): Observable<Ticket> {
    return this.httpClient.post<Ticket>(`services/rest/screenings/ticket/${screeningId}`, price);
  }

  makeShortDescription(movie: Movie) {
    let end = 300;
    if (movie.description.length < end) {
      end = movie.description.length;
    }
    movie.shortDescription = movie.description.substring(0,end) + "..."
  }

  setImageForMovie(movie: Movie, image: Blob) {
    var reader = new FileReader();
    reader.readAsDataURL(image);
    let onloadImage = new Promise((resolve,reject)=>{
      reader.onloadend = () => {
        let base64data = reader.result;
        resolve(base64data);
      };
    });

    onloadImage.then((base64data) => movie.image = this.sanitizer.bypassSecurityTrustUrl(""))
  }

  setScreenings(movie: Movie, screenings: Screening[]) {
    movie.screenings = screenings
    for (let i in screenings) {
      this.convertDate(screenings[i])
    }
  }

  private pad(num, size) {
    var s = "0" + num;
    return s.substr(s.length-size);
  }

  convertDate(screening: Screening) {
    let date = screening.screeningDate
      let mm = this.pad(date.monthOfYear, 2)
      let dd = this.pad(date.dayOfMonth,2)
      let hh = this.pad(date.hourOfDay, 2)
      let MM = this.pad(date.minuteOfHour, 2)
      let t: string = "";
      if (hh != "00") {
        t = ` ${hh}:${MM}`
      }
      screening.screeningDate =
        `${date.year}-${mm}-${dd}${t}`

      return screening
  }
}

export class Movie {
  id?: number;
  title: string;
  genre: MovieGenre;
  directing: string;
  description: string;
  shortDescription: string;
  productionYear: number;
  image: any;
  screenings: Screening[];
}

export class Screening {
  id?: number;
  screeningDate: any;
  movie: Movie;
  screeningRoom: ScreeningRoom;
  orderedTickets: any;
}

export class Seat {
  seatNumber: number;
  free: boolean;
}

export class ScreeningRoom {
  id?: number;
  seats: Seat[];
}

export class Ticket {
  ticketNumber: String;
  screening: Screening;
  seat: Seat;
  price: number;
}

enum MovieGenre {
  COMEDY = "comedy",
  DRAMA = "drama",
  THRILLER = "thriller",
  SCI_FI = "science-fiction",
  OTHER = "other"
}
