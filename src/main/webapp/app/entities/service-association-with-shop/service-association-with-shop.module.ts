import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IluteenusteSystemSharedModule } from 'app/shared/shared.module';
import { ServiceAssociationWithShopComponent } from './service-association-with-shop.component';
import { ServiceAssociationWithShopDetailComponent } from './service-association-with-shop-detail.component';
import { ServiceAssociationWithShopUpdateComponent } from './service-association-with-shop-update.component';
import { ServiceAssociationWithShopDeleteDialogComponent } from './service-association-with-shop-delete-dialog.component';
import { serviceAssociationWithShopRoute } from './service-association-with-shop.route';

@NgModule({
  imports: [IluteenusteSystemSharedModule, RouterModule.forChild(serviceAssociationWithShopRoute)],
  declarations: [
    ServiceAssociationWithShopComponent,
    ServiceAssociationWithShopDetailComponent,
    ServiceAssociationWithShopUpdateComponent,
    ServiceAssociationWithShopDeleteDialogComponent,
  ],
  entryComponents: [ServiceAssociationWithShopDeleteDialogComponent],
})
export class IluteenusteSystemServiceAssociationWithShopModule {}
