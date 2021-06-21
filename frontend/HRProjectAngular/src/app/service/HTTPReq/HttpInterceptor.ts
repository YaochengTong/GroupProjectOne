import { Router } from '@angular/router';
import { finalize, tap } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpResponse } from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let status: string;

    // extend server response observable with logging
    return next.handle(req).pipe(
      tap(
        // Succeeds when there is a response; ignore other events
        (evt => {
            if (evt instanceof HttpResponse) {
                console.log(evt.headers?.get('loginRequired'))
                if(evt.headers?.get('loginRequired') == 'yes')
                    this.router.navigate(['/login']);
            }
        }),
        // Operation failed; error is an HttpErrorResponse
        error => (status = 'failed')
      ),
      // Log when response observable either completes or errors
      finalize(() => {

      })
    );
  }
}