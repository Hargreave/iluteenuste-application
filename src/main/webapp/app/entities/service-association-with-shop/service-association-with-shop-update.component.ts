import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServiceAssociationWithShop, ServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from './service-association-with-shop.service';
import { IService } from 'app/shared/model/service.model';
import { ServiceService } from 'app/entities/service/service.service';
import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from 'app/entities/shop/shop.service';

type SelectableEntity = IService | IShop;

@Component({
  selector: 'jhi-service-association-with-shop-update',
  templateUrl: './service-association-with-shop-update.component.html',
})
export class ServiceAssociationWithShopUpdateComponent implements OnInit {
  isSaving = false;
  services: IService[] = [];
  shops: IShop[] = [];

  editForm = this.fb.group({
    id: [],
    price: [null, [Validators.required]],
    time: [null, [Validators.required]],
    service: [],
    shop: [],
  });

  constructor(
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    protected serviceService: ServiceService,
    protected shopService: ShopService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceAssociationWithShop }) => {
      this.updateForm(serviceAssociationWithShop);

      this.serviceService.query().subscribe((res: HttpResponse<IService[]>) => (this.services = res.body || []));

      this.shopService.query().subscribe((res: HttpResponse<IShop[]>) => (this.shops = res.body || []));
    });
  }

  updateForm(serviceAssociationWithShop: IServiceAssociationWithShop): void {
    this.editForm.patchValue({
      id: serviceAssociationWithShop.id,
      price: serviceAssociationWithShop.price,
      time: serviceAssociationWithShop.time,
      service: serviceAssociationWithShop.service,
      shop: serviceAssociationWithShop.shop,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const serviceAssociationWithShop = this.createFromForm();
    if (serviceAssociationWithShop.id !== undefined) {
      this.subscribeToSaveResponse(this.serviceAssociationWithShopService.update(serviceAssociationWithShop));
    } else {
      this.subscribeToSaveResponse(this.serviceAssociationWithShopService.create(serviceAssociationWithShop));
    }
  }

  private createFromForm(): IServiceAssociationWithShop {
    return {
      ...new ServiceAssociationWithShop(),
      id: this.editForm.get(['id'])!.value,
      price: this.editForm.get(['price'])!.value,
      time: this.editForm.get(['time'])!.value,
      service: this.editForm.get(['service'])!.value,
      shop: this.editForm.get(['shop'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServiceAssociationWithShop>>): void {
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
