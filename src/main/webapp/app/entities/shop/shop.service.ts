import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IShop } from 'app/shared/model/shop.model';

type EntityResponseType = HttpResponse<IShop>;
type EntityArrayResponseType = HttpResponse<IShop[]>;

@Injectable({ providedIn: 'root' })
export class ShopService {
  public resourceUrl = SERVER_API_URL + 'api/shops';

  constructor(protected http: HttpClient) {}

  create(shop: IShop): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shop);
    return this.http
      .post<IShop>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(shop: IShop): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(shop);
    return this.http
      .put<IShop>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IShop>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IShop[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(shop: IShop): IShop {
    const copy: IShop = Object.assign({}, shop, {
      createdDate: shop.createdDate && shop.createdDate.isValid() ? shop.createdDate.format(DATE_FORMAT) : undefined,
      modifiedDate: shop.modifiedDate && shop.modifiedDate.isValid() ? shop.modifiedDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.modifiedDate = res.body.modifiedDate ? moment(res.body.modifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((shop: IShop) => {
        shop.createdDate = shop.createdDate ? moment(shop.createdDate) : undefined;
        shop.modifiedDate = shop.modifiedDate ? moment(shop.modifiedDate) : undefined;
      });
    }
    return res;
  }
}
