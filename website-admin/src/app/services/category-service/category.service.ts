import { PageableModel } from './../../model/base_response.model';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { BASE_URL } from 'src/app/helper/Commonfeature';
import { BaseResponse } from 'src/app/model/base_response.model';
import { Category } from 'src/app/model/category.model';
// const API_URL_CATEGORY = '/getdetailweb/api/v1/category/';
const API_URL_CATEGORY = '/api/v1/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient) { }

  /**
   * Get All category of new Category status
   * @param status Status
   */
  public getAllCategoryByStatus(status: number): Observable<Category[]> {
    // return this.helper.doGet(BASE_URL+)
    return null;
  }

  /**
   * Get Category by display//
   * @param display
   */
  public getAllCategoryByDisplay(display: number): Observable<any[]> {
    return this.http.get<any[]>(
      BASE_URL + API_URL_CATEGORY + 'display/' + display + '/quantity'
    );
  }

  /**
   * Get All Category
   */
  public getAllCategory(
    page: number
  ): Observable<BaseResponse<PageableModel<Category[]>>> {
    const httpParams = new HttpParams().set('page', page);
    return this.http.get<BaseResponse<PageableModel<Category[]>>>(
      BASE_URL + API_URL_CATEGORY + '/all',
      {
        params: httpParams,
      }
    );
  }

  /**
   * Get Quantity News Of Category
   */
  public getQuantityNewsOfCategory(): Observable<any[]> {
    return this.http.get<any[]>(BASE_URL + API_URL_CATEGORY);
  }

  /**
   * Create new category
   * @param body request body
   * @param successCallback success call back
   * @param failedCallback failed call back
   */
  public createNewCategory(
    body: object
  ): Observable<BaseResponse<any>> {
    const bodyString = JSON.stringify(body);
    const url = BASE_URL + API_URL_CATEGORY;
    return this.http.post(url, bodyString, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  /**
   * Update category by id
   * @param id id
   * @param body body
   * @param successCallback success call back
   * @param failedCallback failed call back
   */
  public updateCategoryById(body: object): Observable<BaseResponse<any>> {
    const url = BASE_URL + API_URL_CATEGORY;
    const bodyString = JSON.stringify(body);
    return this.http.put(url, bodyString, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  public deleteCategoryById(id: number): Observable<BaseResponse<any>> {
    const httpParams = new HttpParams().set('id', id);
    const url = BASE_URL + API_URL_CATEGORY;
    return this.http.delete(url, {
      params: httpParams,
    });
  }
}
