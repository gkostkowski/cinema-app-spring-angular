import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpResponse} from '@angular/common/http';


@Injectable()
export class MovieService {

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>('services/rest/movies');
  }
}

export class Movie {
  id?: number;
  title: string;
  genre: MovieGenre;
  directing: string;
  productionYear: number;
}

enum MovieGenre {
  COMEDY = "comedy",
  DRAMA = "drama",
  THRILLER = "thriller",
  SCI_FI = "science-fiction",
  OTHER = "other"
}
