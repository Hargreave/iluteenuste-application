import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAadress } from 'app/shared/model/aadress.model';
import { AadressService } from './aadress.service';

@Component({
  templateUrl: './aadress-delete-dialog.component.html',
})
export class AadressDeleteDialogComponent {
  aadress?: IAadress;

  constructor(protected aadressService: AadressService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.aadressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('aadressListModification');
      this.activeModal.close();
    });
  }
}
