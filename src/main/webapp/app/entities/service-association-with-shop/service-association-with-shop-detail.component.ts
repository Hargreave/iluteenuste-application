import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';

@Component({
  selector: 'jhi-service-association-with-shop-detail',
  templateUrl: './service-association-with-shop-detail.component.html',
})
export class ServiceAssociationWithShopDetailComponent implements OnInit {
  serviceAssociationWithShop: IServiceAssociationWithShop | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceAssociationWithShop }) => (this.serviceAssociationWithShop = serviceAssociationWithShop));
  }

  previousState(): void {
    window.history.back();
  }
}
