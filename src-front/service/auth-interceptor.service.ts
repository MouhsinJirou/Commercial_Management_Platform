import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./AuthService.service";
import {exhaustMap, take} from "rxjs/operators";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor{
  constructor(public authService:AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return this.authService.user.pipe(take(1),exhaustMap(user=>{
      if(!user){return next.handle(req)}
      const modifiedRequet=req.clone({headers:new HttpHeaders({'Authorization':user.token})});
      return next.handle(modifiedRequet);

    }));
  }


}
