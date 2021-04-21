import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IReview, Review } from 'app/shared/model/review.model';
import { ReviewService } from './review.service';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { ServiceAssociationWithShopService } from 'app/entities/service-association-with-shop/service-association-with-shop.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

type SelectableEntity = IServiceAssociationWithShop | IClient;

@Component({
  selector: 'jhi-review-update',
  templateUrl: './review-update.component.html',
})
export class ReviewUpdateComponent implements OnInit {
  isSaving = false;
  serviceassociationwithshops: IServiceAssociationWithShop[] = [];
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    comment: [null, [Validators.required, Validators.minLength(5)]],
    rating: [null, [Validators.min(0), Validators.max(5)]],
    serviceAssociationWithShop: [],
    client: [],
  });

  constructor(
    protected reviewService: ReviewService,
    protected serviceAssociationWithShopService: ServiceAssociationWithShopService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ review }) => {
      this.updateForm(review);

      this.serviceAssociationWithShopService
        .query()
        .subscribe((res: HttpResponse<IServiceAssociationWithShop[]>) => (this.serviceassociationwithshops = res.body || []));

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(review: IReview): void {
    this.editForm.patchValue({
      id: review.id,
      comment: review.comment,
      rating: review.rating,
      serviceAssociationWithShop: review.serviceAssociationWithShop,
      client: review.client,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const review = this.createFromForm();
    if (review.id !== undefined) {
      this.subscribeToSaveResponse(this.reviewService.update(review));
    } else {
      this.subscribeToSaveResponse(this.reviewService.create(review));
    }
  }

  private createFromForm(): IReview {
    return {
      ...new Review(),
      id: this.editForm.get(['id'])!.value,
      comment: this.editForm.get(['comment'])!.value,
      rating: this.editForm.get(['rating'])!.value,
      serviceAssociationWithShop: this.editForm.get(['serviceAssociationWithShop'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReview>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
