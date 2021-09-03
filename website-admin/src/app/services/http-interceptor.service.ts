import { TokenStorageService } from 'src/app/services/token-storage.service';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TOKEN_KEY } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token: TokenStorageService = new TokenStorageService();
    const accessToken = token.getToken();
    const modified =req.clone({headers: req.headers.append('Authorization', `Bearer ${accessToken}`)});
    return next.handle(modified);
  }

}


