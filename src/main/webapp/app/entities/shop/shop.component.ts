import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShop } from 'app/shared/model/shop.model';
import { ShopService } from './shop.service';
import { ShopDeleteDialogComponent } from './shop-delete-dialog.component';
import { ReviewService } from '../review/review.service';
import { IRating } from 'app/shared/model/rating.model';

@Component({
  selector: 'jhi-shop',
  templateUrl: './shop.component.html',
})
export class ShopComponent implements OnInit, OnDestroy {
  shops?: IShop[];
  eventSubscriber?: Subscription;
  ratings: IRating[] = [];
  list: Array<[string, number]> = [
    ['Test 1', 0],
    ['Test 2', 0],
  ];

  constructor(
    protected shopService: ShopService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected reviewService: ReviewService
  ) {}

  loadAll(): void {
    this.shopService.query().subscribe((res: HttpResponse<IShop[]>) => {
      this.shops = res.body || [];
    });

    this.reviewService.getShopsRating().subscribe((res: HttpResponse<IRating[]>) => {
      this.ratings = res.body || [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShops();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShop): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShops(): void {
    this.eventSubscriber = this.eventManager.subscribe('shopListModification', () => this.loadAll());
  }

  delete(shop: IShop): void {
    const modalRef = this.modalService.open(ShopDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shop = shop;
  }
}
