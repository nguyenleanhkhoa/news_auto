import { PageableModel } from './../../model/base_response.model';
import { BaseResponse } from 'src/app/model/base_response.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/helper/Commonfeature';
import { Quantity } from 'src/app/model/quantity.model';
import { WebSource } from 'src/app/model/WebSource.model';
// const API_URL_WEBSOURCE = "/getdetailweb/api/v1/source/";
const API_URL_WEBSOURCE = '/api/v1/website';
@Injectable({
  providedIn: 'root',
})
export class WebsourceService {
  /**
   * constructor
   * @param http http
   */
  constructor(private http: HttpClient) { }

  /**
   * Get All name of website
   */
  public getAllWebsite(
    page: number
  ): Observable<BaseResponse<PageableModel<WebSource[]>>> {
    const httpParams = new HttpParams().set('page', page);

    return this.http.get(BASE_URL + API_URL_WEBSOURCE + '/all', {
      params: httpParams,
    });
  }

  /**
   * Get WebSource Id by Url
   * @param url url
   */
  public getWebsourceIdByUrlName(urlName: string): Observable<string> {
    return this.http.get<string>(
      BASE_URL + API_URL_WEBSOURCE + 'website/' + urlName
    );
  }

  /**
   * Get Name of Web By id
   * @param id id
   */
  public getAllWebsourceById(id: number): Observable<WebSource[]> {
    return this.http.get<WebSource[]>(
      BASE_URL + API_URL_WEBSOURCE + 'id/' + id
    );
  }

  /**
   * Get all website by url
   * @param url url
   */
  public getAllWebsourceByUrl(url: string): Observable<WebSource[]> {
    return this.http.get<WebSource[]>(BASE_URL + API_URL_WEBSOURCE + url);
  }

  /**
   * Get all website by name of website
   * @param name name of website
   */
  public getAllWebsourceByName(name: string): Observable<WebSource[]> {
    return this.http.get<WebSource[]>(
      BASE_URL + API_URL_WEBSOURCE + 'name/' + name
    );
  }

  /**
   * Get News is display and not display of website
   */
  public getNewsDisplayAndNotDisplayOfWebsite(): Observable<Quantity[]> {
    return this.http.get<Quantity[]>(
      BASE_URL + API_URL_WEBSOURCE + 'quantity/news'
    );
  }

  /**
   * Create New WebSource
   * @param body body
   * @param successCallback success call back
   * @param failCallBack failed call back1
   */
  public createNewWebSource(
    body: object,
    successCallback: any,
    failCallBack: any
  ): any {
    const bodyString = JSON.stringify(body);
    this.http
      .post(BASE_URL + API_URL_WEBSOURCE + 'create', bodyString, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      })
      .subscribe(successCallback, failCallBack);
  }

  /**
   * Search website by name or url
   * @param body body
   * @param successCallback success call back
   * @param failCallBack failed call back
   */
  public searchByNameOrUrl(
    body: string,
    successCallback?: any,
    failCallBack?: any
  ): any {
    this.http
      .post(BASE_URL + API_URL_WEBSOURCE + 'search', body, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      })
      .subscribe(successCallback, failCallBack);
  }

  /**
   * Update website by website id
   * @param id id
   * @param body body
   * @param successCallback success call back
   * @param failedCallback  failed call back
   */
  public updateWebSourceByWebSiteId(id: number, body: object): Observable<BaseResponse<any>> {
    const url = BASE_URL + API_URL_WEBSOURCE;
    const httpParams = new HttpParams().set('id', id);
    const bodyString = JSON.stringify(body);
    return this.http.put(url, bodyString, {
      params: httpParams,
    });
  }

  /**
   * Delete website id by id
   * @param id id
   * @param successCallback success call back
   */
  public deleteWebSourceIdById(
    id: number,
    successCallback?: any,
    failedCallback?: any
  ) {
    const url = BASE_URL + API_URL_WEBSOURCE + 'delete/id/' + id;
    this.http
      .delete(url, {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      })
      .subscribe(successCallback, failedCallback);
  }
}
