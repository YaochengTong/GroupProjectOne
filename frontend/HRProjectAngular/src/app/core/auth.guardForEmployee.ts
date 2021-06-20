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
    if (!localStorage.getItem('isLogged')) {
      this.router.navigate(['/login']);
      return false;
    }
    let retrievedObject: any = localStorage.getItem('user');
    let user = JSON.parse(retrievedObject);
    if (user.roleName !== 'Employee') {
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
