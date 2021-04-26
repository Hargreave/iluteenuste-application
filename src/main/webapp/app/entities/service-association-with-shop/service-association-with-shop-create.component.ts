import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ServiceAssociationWithShopService } from './service-association-with-shop.service';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ServiceAssociationWithShopDeleteDialogComponent } from './service-association-with-shop-delete-dialog.component';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

@Component({
  selector: 'jhi-service-association-with-shop-create',
  templateUrl: './service-association-with-shop-create.component.html',
  styleUrls: ['./service-association-with-shop-create.component.scss'],
})
export class ServiceAssociationWithShopCreateComponent implements OnInit {
  shopid!: number;
  serviceAssociationWithShops?: IServiceAssociationWithShop[];
  eventSubscriber?: Subscription;

  createForm = this.fb.group({
    price: [null, [Validators.required]],
    time: [null, [Validators.required]],
    service: [null, [Validators.required]],
  });

  constructor(
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    protected activatedRoute: ActivatedRoute,
    protected modalService: NgbModal,
    protected eventManager: JhiEventManager,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServiceAssociationWithShops();
  }

  loadAll(): void {
    this.activatedRoute.params.subscribe(res => {
      const values = Object.keys(res).map(key => res[key]);
      this.shopid = values[0];
      // eslint-disable-next-line no-console
      console.log(this.shopid);
    });

    this.serviceAssociationWithShopService.findByShop(this.shopid).subscribe((res: HttpResponse<IServiceAssociationWithShop[]>) => {
      this.serviceAssociationWithShops = res.body || [];
    });
  }

  delete(serviceAssociationWithShop: IServiceAssociationWithShop): void {
    const modalRef = this.modalService.open(ServiceAssociationWithShopDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.serviceAssociationWithShop = serviceAssociationWithShop;
  }

  registerChangeInServiceAssociationWithShops(): void {
    this.eventSubscriber = this.eventManager.subscribe('serviceAssociationWithShopListModification', () => this.loadAll());
  }

  trackId(index: number, item: IServiceAssociationWithShop): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  previousState(): void {
    window.history.back();
  }
}
