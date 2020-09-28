import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {AuthService} from "./AuthService.service";
import {map, take} from "rxjs/operators";

@Injectable({providedIn:'root'})
export class RoleGuard implements CanActivate{

  constructor(public authService:AuthService,public router:Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.authService.user.pipe(take(1),map(user=>{
      const isAuth=!!user;
      if(!isAuth || !user.isAdmin){
        return this.router.createUrlTree(['/accueil']);
        return false
      }
      return true
    }))
  }

}
