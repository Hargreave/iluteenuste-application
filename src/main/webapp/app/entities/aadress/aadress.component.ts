import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAadress } from 'app/shared/model/aadress.model';
import { AadressService } from './aadress.service';
import { AadressDeleteDialogComponent } from './aadress-delete-dialog.component';

@Component({
  selector: 'jhi-aadress',
  templateUrl: './aadress.component.html',
})
export class AadressComponent implements OnInit, OnDestroy {
  aadresses?: IAadress[];
  eventSubscriber?: Subscription;

  constructor(protected aadressService: AadressService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.aadressService.query().subscribe((res: HttpResponse<IAadress[]>) => (this.aadresses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAadresses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAadress): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAadresses(): void {
    this.eventSubscriber = this.eventManager.subscribe('aadressListModification', () => this.loadAll());
  }

  delete(aadress: IAadress): void {
    const modalRef = this.modalService.open(AadressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.aadress = aadress;
  }
}
