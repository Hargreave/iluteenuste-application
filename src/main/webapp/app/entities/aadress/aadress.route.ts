import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAadress, Aadress } from 'app/shared/model/aadress.model';
import { AadressService } from './aadress.service';
import { AadressComponent } from './aadress.component';
import { AadressDetailComponent } from './aadress-detail.component';
import { AadressUpdateComponent } from './aadress-update.component';

@Injectable({ providedIn: 'root' })
export class AadressResolve implements Resolve<IAadress> {
  constructor(private service: AadressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAadress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((aadress: HttpResponse<Aadress>) => {
          if (aadress.body) {
            return of(aadress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Aadress());
  }
}

export const aadressRoute: Routes = [
  {
    path: '',
    component: AadressComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.aadress.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AadressDetailComponent,
    resolve: {
      aadress: AadressResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.aadress.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AadressUpdateComponent,
    resolve: {
      aadress: AadressResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.aadress.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AadressUpdateComponent,
    resolve: {
      aadress: AadressResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.aadress.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
