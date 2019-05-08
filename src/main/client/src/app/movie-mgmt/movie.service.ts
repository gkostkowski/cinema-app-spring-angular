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
    return this.httpClient.get<Movie>('services/rest/movies/'+movieId);
  }

  getMovieImage(movieId: number):Observable<Blob> {
    return this.httpClient.get('services/rest/movies/img/'+movieId,
      {
        responseType: "blob"
      });
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
    onloadImage.then((base64data) => movie.image = this.sanitizer.bypassSecurityTrustUrl(base64data))

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
}


enum MovieGenre {
  COMEDY = "comedy",
  DRAMA = "drama",
  THRILLER = "thriller",
  SCI_FI = "science-fiction",
  OTHER = "other"
}
