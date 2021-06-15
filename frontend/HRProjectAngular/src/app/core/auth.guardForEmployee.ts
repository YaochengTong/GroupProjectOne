import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthGuardForEmployee implements CanActivate {
  constructor(private router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    if (localStorage.getItem('isLogged')) {
      // let retrievedObject:any = localStorage.getItem('user');
      // let user = JSON.parse(retrievedObject);
      // if(user.roleName )
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
