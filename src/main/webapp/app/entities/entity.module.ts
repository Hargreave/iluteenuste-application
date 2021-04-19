import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'client',
        loadChildren: () => import('./client/client.module').then(m => m.IluteenusteSystemClientModule),
      },
      {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.IluteenusteSystemShopModule),
      },
      {
        path: 'aadress',
        loadChildren: () => import('./aadress/aadress.module').then(m => m.IluteenusteSystemAadressModule),
      },
      {
        path: 'review',
        loadChildren: () => import('./review/review.module').then(m => m.IluteenusteSystemReviewModule),
      },
      {
        path: 'service',
        loadChildren: () => import('./service/service.module').then(m => m.IluteenusteSystemServiceModule),
      },
      {
        path: 'service-association-with-shop',
        loadChildren: () =>
          import('./service-association-with-shop/service-association-with-shop.module').then(
            m => m.IluteenusteSystemServiceAssociationWithShopModule
          ),
      },
      {
        path: 'booking',
        loadChildren: () => import('./booking/booking.module').then(m => m.IluteenusteSystemBookingModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class IluteenusteSystemEntityModule {}
