import { HttpClient } from '@angular/common/http';
import { Content } from '@angular/compiler/src/render3/r3_ast';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/helper/Commonfeature';
const API_URL_CONTENT = "/getdetailweb/api/v1/content/";

@Injectable({
  providedIn: 'root'
})
export class ContentService {

  constructor(private http: HttpClient) {}

  /**
   * Get Content by id
   * @param id id
   */
  public getContentOfNewsById(id: number): Observable<Content[]> {
    return this.http.get<Content[]>(BASE_URL + API_URL_CONTENT + "id/" + id);
  }
}
