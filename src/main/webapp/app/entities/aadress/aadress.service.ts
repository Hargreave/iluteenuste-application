import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAadress } from 'app/shared/model/aadress.model';

type EntityResponseType = HttpResponse<IAadress>;
type EntityArrayResponseType = HttpResponse<IAadress[]>;

@Injectable({ providedIn: 'root' })
export class AadressService {
  public resourceUrl = SERVER_API_URL + 'api/aadresses';

  constructor(protected http: HttpClient) {}

  create(aadress: IAadress): Observable<EntityResponseType> {
    return this.http.post<IAadress>(this.resourceUrl, aadress, { observe: 'response' });
  }

  update(aadress: IAadress): Observable<EntityResponseType> {
    return this.http.put<IAadress>(this.resourceUrl, aadress, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAadress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAadress[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
