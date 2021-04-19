import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from './service-association-with-shop.service';
import { ServiceAssociationWithShopDeleteDialogComponent } from './service-association-with-shop-delete-dialog.component';

@Component({
  selector: 'jhi-service-association-with-shop',
  templateUrl: './service-association-with-shop.component.html',
})
export class ServiceAssociationWithShopComponent implements OnInit, OnDestroy {
  serviceAssociationWithShops?: IServiceAssociationWithShop[];
  eventSubscriber?: Subscription;

  constructor(
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.serviceAssociationWithShopService
      .query()
      .subscribe((res: HttpResponse<IServiceAssociationWithShop[]>) => (this.serviceAssociationWithShops = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServiceAssociationWithShops();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IServiceAssociationWithShop): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInServiceAssociationWithShops(): void {
    this.eventSubscriber = this.eventManager.subscribe('serviceAssociationWithShopListModification', () => this.loadAll());
  }

  delete(serviceAssociationWithShop: IServiceAssociationWithShop): void {
    const modalRef = this.modalService.open(ServiceAssociationWithShopDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.serviceAssociationWithShop = serviceAssociationWithShop;
  }
}
