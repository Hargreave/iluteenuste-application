import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IluteenusteSystemSharedModule } from 'app/shared/shared.module';
import { BookingComponent } from './booking.component';
import { BookingDetailComponent } from './booking-detail.component';
import { BookingUpdateComponent } from './booking-update.component';
import { BookingDeleteDialogComponent } from './booking-delete-dialog.component';
import { bookingRoute } from './booking.route';
import { BookingCreateComponent } from './booking-create.component';
import { CustomerCalenderComponent } from './customer-calender/customer-calender.component';
import { CommonModule } from '@angular/common';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

@NgModule({
  imports: [
    CommonModule,
    IluteenusteSystemSharedModule,
    RouterModule.forChild(bookingRoute),
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory,
    }),
  ],
  declarations: [
    BookingComponent,
    BookingDetailComponent,
    BookingUpdateComponent,
    BookingDeleteDialogComponent,
    BookingCreateComponent,
    CustomerCalenderComponent,
  ],
  entryComponents: [BookingDeleteDialogComponent],
  exports: [CustomerCalenderComponent],
})
export class IluteenusteSystemBookingModule {}
