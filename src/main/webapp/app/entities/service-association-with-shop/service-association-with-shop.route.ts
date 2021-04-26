import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IServiceAssociationWithShop, ServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from './service-association-with-shop.service';
import { ServiceAssociationWithShopComponent } from './service-association-with-shop.component';
import { ServiceAssociationWithShopDetailComponent } from './service-association-with-shop-detail.component';
import { ServiceAssociationWithShopUpdateComponent } from './service-association-with-shop-update.component';
import { ServiceAssociationWithShopCreateComponent } from './service-association-with-shop-create.component';

@Injectable({ providedIn: 'root' })
export class ServiceAssociationWithShopResolve implements Resolve<IServiceAssociationWithShop> {
  constructor(private service: ServiceAssociationWithShopService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServiceAssociationWithShop> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((serviceAssociationWithShop: HttpResponse<ServiceAssociationWithShop>) => {
          if (serviceAssociationWithShop.body) {
            return of(serviceAssociationWithShop.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ServiceAssociationWithShop());
  }
}

export const serviceAssociationWithShopRoute: Routes = [
  {
    path: '',
    component: ServiceAssociationWithShopComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/all',
    component: ServiceAssociationWithShopDetailComponent,
    resolve: {
      serviceAssociationWithShop: ServiceAssociationWithShopResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':shopId/new',
    component: ServiceAssociationWithShopUpdateComponent,
    resolve: {
      serviceAssociationWithShop: ServiceAssociationWithShopResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServiceAssociationWithShopUpdateComponent,
    resolve: {
      serviceAssociationWithShop: ServiceAssociationWithShopResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':shopId/view',
    component: ServiceAssociationWithShopCreateComponent,
    resolve: {
      serviceAssociationWithShop: ServiceAssociationWithShopResolve,
    },
    data: {
      authorities: [Authority.OWNER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':shopId/view/:id',
    component: ServiceAssociationWithShopUpdateComponent,
    resolve: {
      serviceAssociationWithShop: ServiceAssociationWithShopResolve,
    },
    data: {
      authorities: [Authority.OWNER],
      pageTitle: 'iluteenusteSystemApp.serviceAssociationWithShop.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
