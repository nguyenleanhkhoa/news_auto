import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/helper/Commonfeature';
import { News } from 'src/app/model/news.model';
const API_URL_NEWS = '/getdetailweb/api/v1/news/';

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  setData(data: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private http: HttpClient) {}

  /**
   * Get News By Id
   * @param id id
   */
  public getNewsById(id: number): Observable<News[]> {
    return this.http.get<News[]>(BASE_URL + API_URL_NEWS + 'id/' + id);
  }

  /**
   * Get All News
   */
  public getAllNewsByStatus(status: number): Observable<News[]> {
    return this.http.get<News[]>(BASE_URL + API_URL_NEWS + 'status/' + status);
  }

  public getAllNewsByWebsiteHasStatus(status: number,page: number): Observable<any[]> {
    return this.http.get<any[]>(BASE_URL + API_URL_NEWS + 'status/' + status + '/page/' + page);
  }

  /**
   * Get News By Title
   * @param title title
   */
  public getNewsByTitle(title: string): Observable<News[]> {
    return this.http.get<News[]>(BASE_URL + API_URL_NEWS + 'title/' + title);
  }

  /**
   * Get News By category
   * @param category category
   */
  public getNewsByCategory(category: string): Observable<News[]> {
    return this.http.get<News[]>(
      BASE_URL + API_URL_NEWS + 'category/' + category
    );
  }

  /**
   * Get News Approved By category
   * @param category category
   */
  public getNewsAprrovedByCategory(
    category: string, page: number
    ): Observable<any[]> {
    return this.http.get<any[]>(
      BASE_URL + API_URL_NEWS + 'display/category/' + category + '/page/' + page
    );
  }

  /**
   * Get News Not Approved By category
   * @param category category
   */
  public getNewsNotApprovedByCategory(category: string, page: number): Observable<News[]> {
    return this.http.get<News[]>(
      BASE_URL + API_URL_NEWS + 'notdisplay/category/' + category + '/page/' + page
    );
  }

  /**
   * Update news by id
   * @param id id
   * @param body body
   * @param successCallback success call back
   */
  public updateNewsById(id: number, body: object, successCallback: any) {
    const url = BASE_URL + API_URL_NEWS + 'update/id/' + id;
    const bodyString = JSON.stringify(body);
    this.http
      .put(url, bodyString, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
          // Authorization: sessionStorage.getItem('Token')
        })
      })
      .subscribe(successCallback);
  }
}
