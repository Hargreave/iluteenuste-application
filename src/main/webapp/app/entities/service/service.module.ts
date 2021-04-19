import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IluteenusteSystemSharedModule } from 'app/shared/shared.module';
import { ServiceComponent } from './service.component';
import { ServiceDetailComponent } from './service-detail.component';
import { ServiceUpdateComponent } from './service-update.component';
import { ServiceDeleteDialogComponent } from './service-delete-dialog.component';
import { serviceRoute } from './service.route';

@NgModule({
  imports: [IluteenusteSystemSharedModule, RouterModule.forChild(serviceRoute)],
  declarations: [ServiceComponent, ServiceDetailComponent, ServiceUpdateComponent, ServiceDeleteDialogComponent],
  entryComponents: [ServiceDeleteDialogComponent],
})
export class IluteenusteSystemServiceModule {}
