import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IluteenusteSystemSharedModule } from 'app/shared/shared.module';
import { ShopComponent } from './shop.component';
import { ShopDetailComponent } from './shop-detail.component';
import { ShopUpdateComponent } from './shop-update.component';
import { ShopDeleteDialogComponent } from './shop-delete-dialog.component';
import { shopRoute } from './shop.route';
import { ShopCreateComponent } from './shop-create.component';

@NgModule({
  imports: [IluteenusteSystemSharedModule, RouterModule.forChild(shopRoute)],
  declarations: [ShopComponent, ShopDetailComponent, ShopUpdateComponent, ShopDeleteDialogComponent, ShopCreateComponent],
  entryComponents: [ShopDeleteDialogComponent],
})
export class IluteenusteSystemShopModule {}
