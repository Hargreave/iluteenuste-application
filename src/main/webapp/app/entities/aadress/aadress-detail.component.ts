import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAadress } from 'app/shared/model/aadress.model';

@Component({
  selector: 'jhi-aadress-detail',
  templateUrl: './aadress-detail.component.html',
})
export class AadressDetailComponent implements OnInit {
  aadress: IAadress | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aadress }) => (this.aadress = aadress));
  }

  previousState(): void {
    window.history.back();
  }
}
