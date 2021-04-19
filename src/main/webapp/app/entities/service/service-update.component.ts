import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IService, Service } from 'app/shared/model/service.model';
import { ServiceService } from './service.service';

@Component({
  selector: 'jhi-service-update',
  templateUrl: './service-update.component.html',
})
export class ServiceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    sex: [null, [Validators.required]],
    forChild: [null, [Validators.required]],
  });

  constructor(protected serviceService: ServiceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ service }) => {
      this.updateForm(service);
    });
  }

  updateForm(service: IService): void {
    this.editForm.patchValue({
      id: service.id,
      name: service.name,
      sex: service.sex,
      forChild: service.forChild,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const service = this.createFromForm();
    if (service.id !== undefined) {
      this.subscribeToSaveResponse(this.serviceService.update(service));
    } else {
      this.subscribeToSaveResponse(this.serviceService.create(service));
    }
  }

  private createFromForm(): IService {
    return {
      ...new Service(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      forChild: this.editForm.get(['forChild'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IService>>): void {
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
