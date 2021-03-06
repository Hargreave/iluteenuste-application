import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IShop, Shop } from 'app/shared/model/shop.model';
import { ShopService } from './shop.service';
import { IAadress } from 'app/shared/model/aadress.model';
import { AadressService } from 'app/entities/aadress/aadress.service';
import { Client, IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { AccountService } from 'app/core/auth/account.service';
type SelectableEntity = IAadress | IClient;

@Component({
  selector: 'jhi-shop-update',
  templateUrl: './shop-update.component.html',
})
export class ShopUpdateComponent implements OnInit {
  isSaving = false;
  aadresses: IAadress[] = [];
  clients: IClient[] = [];
  client!: IClient;
  createdDateDp: any;
  modifiedDateDp: any;
  accountId: any;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    description: [],
    imgAadress: [],
    createdBy: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    modifiedBy: [],
    modifiedDate: [],
    aadress: [],
    clients: [],
  });

  constructor(
    protected accountService: AccountService,
    protected shopService: ShopService,
    protected aadressService: AadressService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shop }) => {
      this.updateForm(shop);

      this.aadressService
        .query({ filter: 'shop-is-null' })
        .pipe(
          map((res: HttpResponse<IAadress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAadress[]) => {
          if (!shop.aadress || !shop.aadress.id) {
            this.aadresses = resBody;
          } else {
            this.aadressService
              .find(shop.aadress.id)
              .pipe(
                map((subRes: HttpResponse<IAadress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAadress[]) => (this.aadresses = concatRes));
          }
        });

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.accountService.identity().subscribe(res => {
        this.accountId = res?.id;
      });

      this.clientService.findByUserId(this.accountId).subscribe((res: HttpResponse<IClient>) => {
        this.client = res.body || new Client();
      });
    });
  }

  updateForm(shop: IShop): void {
    this.editForm.patchValue({
      id: shop.id,
      name: shop.name,
      description: shop.description,
      imgAadress: shop.imgAadress,
      createdBy: shop.createdBy,
      createdDate: shop.createdDate,
      modifiedBy: shop.modifiedBy,
      modifiedDate: shop.modifiedDate,
      aadress: shop.aadress,
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
      description: this.editForm.get(['description'])!.value,
      imgAadress: this.editForm.get(['imgAadress'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      modifiedBy: this.editForm.get(['modifiedBy'])!.value,
      modifiedDate: this.editForm.get(['modifiedDate'])!.value,
      aadress: this.editForm.get(['aadress'])!.value,
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

  trackById(index: number, item: SelectableEntity): any {
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
