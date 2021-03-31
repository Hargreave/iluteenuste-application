import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IluteenusteSystemSharedModule } from 'app/shared/shared.module';
import { AadressComponent } from './aadress.component';
import { AadressDetailComponent } from './aadress-detail.component';
import { AadressUpdateComponent } from './aadress-update.component';
import { AadressDeleteDialogComponent } from './aadress-delete-dialog.component';
import { aadressRoute } from './aadress.route';

@NgModule({
  imports: [IluteenusteSystemSharedModule, RouterModule.forChild(aadressRoute)],
  declarations: [AadressComponent, AadressDetailComponent, AadressUpdateComponent, AadressDeleteDialogComponent],
  entryComponents: [AadressDeleteDialogComponent],
})
export class IluteenusteSystemAadressModule {}
