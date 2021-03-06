import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReview } from 'app/shared/model/review.model';
import { IRating } from 'app/shared/model/rating.model';

type EntityResponseType = HttpResponse<IReview>;
type EntityArrayResponseType = HttpResponse<IReview[]>;

@Injectable({ providedIn: 'root' })
export class ReviewService {
  public resourceUrl = SERVER_API_URL + 'api/reviews';

  constructor(protected http: HttpClient) {}

  create(review: IReview): Observable<EntityResponseType> {
    return this.http.post<IReview>(this.resourceUrl, review, { observe: 'response' });
  }

  update(review: IReview): Observable<EntityResponseType> {
    return this.http.put<IReview>(this.resourceUrl, review, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReview>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getShopsRating(): Observable<HttpResponse<IRating[]>> {
    return this.http.get<IRating[]>(`${this.resourceUrl}/rating`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReview[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
