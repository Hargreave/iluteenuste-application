import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from '../service-association-with-shop/service-association-with-shop.service';

@Component({
  selector: 'jhi-booking-create',
  templateUrl: './booking-create.component.html',
  styleUrls: ['./booking-create.component.scss'],
})
export class BookingCreateComponent implements OnInit {
  shopid!: number;
  shopServices?: IServiceAssociationWithShop[];
  selectedShopService?: IServiceAssociationWithShop;
  serviceType!: string;
  maleButton = false;
  femaleButton = false;
  categoryChosen = false;

  createForm = this.fb.group({});

  constructor(
    protected activatedRoute: ActivatedRoute,
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(res => {
      const values = Object.keys(res).map(key => res[key]);
      this.shopid = values[0];
    });

    this.loadAll();
  }

  loadAll(): void {
    this.serviceAssociationWithShopService.findByShop(this.shopid).subscribe(res => {
      if (this.serviceType) {
        this.shopServices =
          res.body?.filter(p => {
            return p.service?.sex === this.serviceType;
          }) || [];
        this.categoryChosen = true;
        // eslint-disable-next-line no-console
        console.log(this.shopServices);
      } else {
        this.shopServices = res.body || [];
        // eslint-disable-next-line no-console
        console.log(this.shopServices);
      }
    });
  }

  onClick(value: string): void {
    if (value === 'MALE') {
      this.maleButton = true;
      this.femaleButton = false;
    } else {
      this.femaleButton = true;
      this.maleButton = false;
    }
    this.serviceType = value;
    this.loadAll();
  }

  save(): void {
    // eslint-disable-next-line no-console
    console.log('saving');
  }

  onSelect(value: IServiceAssociationWithShop): void {
    this.selectedShopService = value;
    // eslint-disable-next-line no-console
    console.log('selecting: ', this.selectedShopService);
  }
}
