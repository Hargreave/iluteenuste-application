import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { Aadress, IAadress } from 'app/shared/model/aadress.model';
import { IClient } from 'app/shared/model/client.model';
import { IShop, Shop } from 'app/shared/model/shop.model';
import { Observable } from 'rxjs';
import { AadressService } from '../aadress/aadress.service';
import { ClientService } from '../client/client.service';
import { ShopService } from './shop.service';

@Component({
  selector: 'jhi-shop-create',
  templateUrl: './shop-create.component.html',
  styleUrls: ['./shop-create.component.scss'],
})
export class ShopCreateComponent implements OnInit {
  shopid = undefined;
  login: string | undefined = '';
  client: IClient[] = [];
  clientDataRecieved = false;
  country = 'EST';
  isSaving = false;

  createForm = this.fb.group({
    name: [null, [Validators.required]],
    description: [null, [Validators.required]],
    imgAadress: [null, [Validators.required]],
    fullAadress: [null, [Validators.required, Validators.minLength(10)]],
    zipCode: [null, [Validators.required, Validators.maxLength(20), Validators.minLength(4)]],
    xCord: [null, [Validators.required]],
    yCord: [null, [Validators.required]],
  });

  constructor(
    protected shopService: ShopService,
    protected aadressService: AadressService,
    protected accountService: AccountService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shop }) => {
      this.shopid = shop.id;
      this.updateForm(shop);
    });

    this.accountService.identity().subscribe(res => {
      if (res?.id > -1) {
        this.clientService.findByUserId(res?.id).subscribe(resInner => {
          if (resInner.body != null) {
            this.client.push(resInner.body);
            this.clientDataRecieved = true;
          }
        });
      }
      this.login = res?.login;
    });
  }

  protected updateForm(shop: IShop): void {
    this.createForm.patchValue({
      name: shop?.name,
      description: shop?.description,
      imgAadress: shop?.imgAadress,
      fullAadress: shop.aadress?.fullAadress,
      zipCode: shop.aadress?.zipCode,
      xCord: shop.aadress?.xCoordinate,
      yCord: shop.aadress?.yCoordinate,
    });
  }

  private createShopFromForm(): IShop {
    return {
      ...new Shop(),
      id: this.shopid,
      name: this.createForm.get(['name'])!.value,
      description: this.createForm.get(['description'])!.value,
      imgAadress: this.createForm.get(['imgAadress'])!.value,
      createdBy: this.login,
      createdDate: new Date(),
      modifiedBy: this.login,
      modifiedDate: new Date(),
      aadress: this.createAadressFromForm(),
      clients: this.client,
    };
  }

  private createAadressFromForm(): IAadress {
    return {
      ...new Aadress(),
      fullAadress: this.createForm.get(['fullAadress'])!.value,
      zipCode: this.createForm.get(['zipCode'])!.value,
      xCoordinate: this.createForm.get(['xCord'])!.value,
      yCoordinate: this.createForm.get(['yCord'])!.value,
      country: this.country,
    };
  }

  save(): void {
    this.isSaving = true;
    const shop = this.createShopFromForm();
    if (shop.id !== undefined) {
      this.subscribeToSaveResponse(this.shopService.update(shop));
    } else {
      this.subscribeToSaveResponse(this.shopService.create(shop));
    }
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

  previousState(): void {
    window.history.back();
  }
}
