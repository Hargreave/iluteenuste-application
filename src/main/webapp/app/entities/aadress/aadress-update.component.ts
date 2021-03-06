import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAadress, Aadress } from 'app/shared/model/aadress.model';
import { AadressService } from './aadress.service';

@Component({
  selector: 'jhi-aadress-update',
  templateUrl: './aadress-update.component.html',
})
export class AadressUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    fullAadress: [null, [Validators.required, Validators.minLength(10)]],
    zipCode: [null, [Validators.required, Validators.maxLength(20)]],
    xCoordinate: [null, [Validators.required]],
    yCoordinate: [null, [Validators.required]],
    country: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(3)]],
  });

  constructor(protected aadressService: AadressService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ aadress }) => {
      this.updateForm(aadress);
    });
  }

  updateForm(aadress: IAadress): void {
    this.editForm.patchValue({
      id: aadress.id,
      fullAadress: aadress.fullAadress,
      zipCode: aadress.zipCode,
      xCoordinate: aadress.xCoordinate,
      yCoordinate: aadress.yCoordinate,
      country: aadress.country,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const aadress = this.createFromForm();
    if (aadress.id !== undefined) {
      this.subscribeToSaveResponse(this.aadressService.update(aadress));
    } else {
      this.subscribeToSaveResponse(this.aadressService.create(aadress));
    }
  }

  private createFromForm(): IAadress {
    return {
      ...new Aadress(),
      id: this.editForm.get(['id'])!.value,
      fullAadress: this.editForm.get(['fullAadress'])!.value,
      zipCode: this.editForm.get(['zipCode'])!.value,
      xCoordinate: this.editForm.get(['xCoordinate'])!.value,
      yCoordinate: this.editForm.get(['yCoordinate'])!.value,
      country: this.editForm.get(['country'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAadress>>): void {
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
}
