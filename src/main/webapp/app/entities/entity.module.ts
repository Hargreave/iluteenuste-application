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
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class IluteenusteSystemEntityModule {}
