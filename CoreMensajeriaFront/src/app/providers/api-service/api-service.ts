import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
let api = "http://localhost:8080/coreMensajeria_war_exploded/";

@Injectable()
export class ApiServiceProvider {

  constructor(public http: HttpClient) {}

  postData(credentials, url) {
    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    return new Promise((resolve, reject) => {
      this.http.post(api + url, JSON.stringify(credentials), {headers: headers})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }

  getData(url) {
    return new Promise((resolve, reject) => {
      this.http.get(api + url)
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
  putData(credentials, url) {
    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    return new Promise((resolve, reject) => {
      this.http.put(api + url, JSON.stringify(credentials), {headers: headers})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
  deleteData(url) {
    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    return new Promise((resolve, reject) => {
      this.http.delete(api + url, {headers: headers})
        .subscribe(res => {
          resolve(res);
        }, (err) => {
          reject(err);
        });
    });
  }
}
