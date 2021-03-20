import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShop, Shop } from 'app/shared/model/shop.model';
import { ShopService } from './shop.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

@Component({
  selector: 'jhi-shop-update',
  templateUrl: './shop-update.component.html',
})
export class ShopUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    aadress: [null, [Validators.required]],
    xCoordinate: [null, [Validators.required]],
    yCoordinate: [null, [Validators.required]],
    clients: [],
  });

  constructor(
    protected shopService: ShopService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shop }) => {
      this.updateForm(shop);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(shop: IShop): void {
    this.editForm.patchValue({
      id: shop.id,
      name: shop.name,
      aadress: shop.aadress,
      xCoordinate: shop.xCoordinate,
      yCoordinate: shop.yCoordinate,
      clients: shop.clients,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shop = this.createFromForm();
    if (shop.id !== undefined) {
      this.subscribeToSaveResponse(this.shopService.update(shop));
    } else {
      this.subscribeToSaveResponse(this.shopService.create(shop));
    }
  }

  private createFromForm(): IShop {
    return {
      ...new Shop(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      aadress: this.editForm.get(['aadress'])!.value,
      xCoordinate: this.editForm.get(['xCoordinate'])!.value,
      yCoordinate: this.editForm.get(['yCoordinate'])!.value,
      clients: this.editForm.get(['clients'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShop>>): void {
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

  trackById(index: number, item: IClient): any {
    return item.id;
  }

  getSelected(selectedVals: IClient[], option: IClient): IClient {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
