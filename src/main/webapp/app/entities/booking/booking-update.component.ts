import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBooking, Booking } from 'app/shared/model/booking.model';
import { BookingService } from './booking.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { IService } from 'app/shared/model/service.model';
import { ServiceService } from 'app/entities/service/service.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop/shop.service';

type SelectableEntity = IClient | IService | IShop;

@Component({
  selector: 'jhi-booking-update',
  templateUrl: './booking-update.component.html',
})
export class BookingUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];
  services: IService[] = [];
  shops: IShop[] = [];
  createdDateDp: any;
  serviceStartDp: any;
  serviceEndDp: any;
  acceptedDateDp: any;
  acceptedByDp: any;

  editForm = this.fb.group({
    id: [],
    createdDate: [null, [Validators.required]],
    status: [null, [Validators.required]],
    serviceStart: [null, [Validators.required]],
    serviceEnd: [null, [Validators.required]],
    acceptedDate: [],
    acceptedBy: [],
    additionalDesc: [],
    client: [],
    service: [],
    shop: [],
  });

  constructor(
    protected bookingService: BookingService,
    protected clientService: ClientService,
    protected serviceService: ServiceService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ booking }) => {
      this.updateForm(booking);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.serviceService.query().subscribe((res: HttpResponse<IService[]>) => (this.services = res.body || []));

      this.shopService.query().subscribe((res: HttpResponse<IShop[]>) => (this.shops = res.body || []));
    });
  }

  updateForm(booking: IBooking): void {
    this.editForm.patchValue({
      id: booking.id,
      createdDate: booking.createdDate,
      status: booking.status,
      serviceStart: booking.serviceStart,
      serviceEnd: booking.serviceEnd,
      acceptedDate: booking.acceptedDate,
      acceptedBy: booking.acceptedBy,
      additionalDesc: booking.additionalDesc,
      client: booking.client,
      service: booking.service,
      shop: booking.shop,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const booking = this.createFromForm();
    if (booking.id !== undefined) {
      this.subscribeToSaveResponse(this.bookingService.update(booking));
    } else {
      this.subscribeToSaveResponse(this.bookingService.create(booking));
    }
  }

  private createFromForm(): IBooking {
    return {
      ...new Booking(),
      id: this.editForm.get(['id'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      status: this.editForm.get(['status'])!.value,
      serviceStart: this.editForm.get(['serviceStart'])!.value,
      serviceEnd: this.editForm.get(['serviceEnd'])!.value,
      acceptedDate: this.editForm.get(['acceptedDate'])!.value,
      acceptedBy: this.editForm.get(['acceptedBy'])!.value,
      additionalDesc: this.editForm.get(['additionalDesc'])!.value,
      client: this.editForm.get(['client'])!.value,
      service: this.editForm.get(['service'])!.value,
      shop: this.editForm.get(['shop'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBooking>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
