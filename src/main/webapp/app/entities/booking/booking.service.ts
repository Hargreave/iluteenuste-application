import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBooking } from 'app/shared/model/booking.model';

type EntityResponseType = HttpResponse<IBooking>;
type EntityArrayResponseType = HttpResponse<IBooking[]>;

@Injectable({ providedIn: 'root' })
export class BookingService {
  public resourceUrl = SERVER_API_URL + 'api/bookings';

  constructor(protected http: HttpClient) {}

  create(booking: IBooking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(booking);
    return this.http
      .post<IBooking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(booking: IBooking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(booking);
    return this.http
      .put<IBooking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBooking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBooking[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(booking: IBooking): IBooking {
    const copy: IBooking = Object.assign({}, booking, {
      createdDate: booking.createdDate && booking.createdDate.isValid() ? booking.createdDate.format(DATE_FORMAT) : undefined,
      serviceStart: booking.serviceStart && booking.serviceStart.isValid() ? booking.serviceStart.format(DATE_FORMAT) : undefined,
      serviceEnd: booking.serviceEnd && booking.serviceEnd.isValid() ? booking.serviceEnd.format(DATE_FORMAT) : undefined,
      acceptedDate: booking.acceptedDate && booking.acceptedDate.isValid() ? booking.acceptedDate.format(DATE_FORMAT) : undefined,
      acceptedBy: booking.acceptedBy && booking.acceptedBy.isValid() ? booking.acceptedBy.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.serviceStart = res.body.serviceStart ? moment(res.body.serviceStart) : undefined;
      res.body.serviceEnd = res.body.serviceEnd ? moment(res.body.serviceEnd) : undefined;
      res.body.acceptedDate = res.body.acceptedDate ? moment(res.body.acceptedDate) : undefined;
      res.body.acceptedBy = res.body.acceptedBy ? moment(res.body.acceptedBy) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((booking: IBooking) => {
        booking.createdDate = booking.createdDate ? moment(booking.createdDate) : undefined;
        booking.serviceStart = booking.serviceStart ? moment(booking.serviceStart) : undefined;
        booking.serviceEnd = booking.serviceEnd ? moment(booking.serviceEnd) : undefined;
        booking.acceptedDate = booking.acceptedDate ? moment(booking.acceptedDate) : undefined;
        booking.acceptedBy = booking.acceptedBy ? moment(booking.acceptedBy) : undefined;
      });
    }
    return res;
  }
}
