import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';

type EntityResponseType = HttpResponse<IServiceAssociationWithShop>;
type EntityArrayResponseType = HttpResponse<IServiceAssociationWithShop[]>;

@Injectable({ providedIn: 'root' })
export class ServiceAssociationWithShopService {
  public resourceUrl = SERVER_API_URL + 'api/service-association-with-shops';

  constructor(protected http: HttpClient) {}

  create(serviceAssociationWithShop: IServiceAssociationWithShop): Observable<EntityResponseType> {
    return this.http.post<IServiceAssociationWithShop>(this.resourceUrl, serviceAssociationWithShop, { observe: 'response' });
  }

  update(serviceAssociationWithShop: IServiceAssociationWithShop): Observable<EntityResponseType> {
    return this.http.put<IServiceAssociationWithShop>(this.resourceUrl, serviceAssociationWithShop, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IServiceAssociationWithShop>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServiceAssociationWithShop[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
