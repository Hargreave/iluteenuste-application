import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from './service-association-with-shop.service';

@Component({
  templateUrl: './service-association-with-shop-delete-dialog.component.html',
})
export class ServiceAssociationWithShopDeleteDialogComponent {
  serviceAssociationWithShop?: IServiceAssociationWithShop;

  constructor(
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.serviceAssociationWithShopService.delete(id).subscribe(() => {
      this.eventManager.broadcast('serviceAssociationWithShopListModification');
      this.activeModal.close();
    });
  }
}
