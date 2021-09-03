import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/app/helper/Commonfeature';
import { WebFormat } from 'src/app/model/webformat.model';
const API_URL_WEBFORMAT = "/getdetailweb/api/v1/webformat/";

@Injectable({
  providedIn: 'root'
})
export class WebformatService {

  constructor(private http: HttpClient) {}
  public getAllWebFormatByWebsiteId(id: number): Observable<WebFormat[]> {
    return this.http.get<WebFormat[]>(
      BASE_URL + API_URL_WEBFORMAT + "websiteId=" + id
    );
  }
  public getAllWebFormatByWebsiteIdAndCategoryId(
    idwebiste: number,
    idCategory: number
  ): Observable<WebFormat[]> {
    return this.http.get<WebFormat[]>(
      BASE_URL +
        API_URL_WEBFORMAT +
        "websiteId=" +
        idwebiste +
        "/categoryId=" +
        idCategory
    );
  }

  /**
   * Create new WebFormat By URL
   * @param url url
   * @param body body
   * @param successCallback success call back
   * @param failedCallBack  failed call back
   */
  public createNewWebFormatByUrl(
    url: string,
    body: object,
    successCallback?: any,
    failedCallBack?: any
  ): any {
    const bodyString = JSON.stringify(body);
    return this.http
      .post<any>(BASE_URL + API_URL_WEBFORMAT + "url=" + url, bodyString, {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
          // Authorization: sessionStorage.getItem('Token')
        })
      })
      .subscribe(successCallback, failedCallBack);
  }

  /**
   * Create new WebFormat by WebSource ID
   * @param id id
   * @param body body
   * @param successCallback sucess call back
   * @param failedCallBack failed call back
   */
  public createNewWebFormatByWebsourceIDAndCategoryId(
    id: number,
    categoryId: number,
    body: object,
    successCallback?: any,
    failedCallBack?: any
  ): any {
    const bodyString = JSON.stringify(body);
    return this.http
      .post<any>(
        API_URL_WEBFORMAT + "websiteId=" + id + "&category=" + categoryId,
        bodyString,
        {
          headers: new HttpHeaders({
            "Content-Type": "application/json"
            // Authorization: sessionStorage.getItem('Token')
          })
        }
      )
      .subscribe(successCallback, failedCallBack);
  }

  /**
   * Update WebFormat By WebSource ID
   * @param id id
   * @param body body
   * @param successCallback success call back
   */
  public updateWebFormatByWebSiteId(
    id: number,
    body: object,
    successCallback: any
  ) {
    const url = BASE_URL + API_URL_WEBFORMAT + "websiteId=" + id;
    const bodyString = JSON.stringify(body);
    this.http
      .put(url, bodyString, {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
          // Authorization: sessionStorage.getItem('Token')
        })
      })
      .subscribe(successCallback);
  }

  public updateWebFormatByWebSiteIdAndCategoryId(
    idWebsite: number,
    idCategory: number,
    body: object,
    successCallback: any
  ) {
    const url =
      BASE_URL +
      API_URL_WEBFORMAT +
      "update/webId=" +
      idWebsite +
      "/categoryId=" +
      idCategory;
    const bodyString = JSON.stringify(body);
    this.http
      .put(url, bodyString, {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
          // Authorization: sessionStorage.getItem('Token')
        })
      })
      .subscribe(successCallback);
  }

  public deleteWebformatByWebsourceId(id: number, successCallback: any) {
    const url = BASE_URL + API_URL_WEBFORMAT + "websiteId=" + id;
    this.http
      .delete(url, {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
          // Authorization: sessionStorage.getItem('Token')
        })
      })
      .subscribe(successCallback);
  }

}
