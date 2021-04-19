import { Moment } from 'moment';
import { IClient } from 'app/shared/model/client.model';
import { IService } from 'app/shared/model/service.model';
import { IShop } from 'app/shared/model/shop.model';

export interface IBooking {
  id?: number;
  createdDate?: Moment;
  status?: string;
  serviceStart?: Moment;
  serviceEnd?: Moment;
  acceptedDate?: Moment;
  acceptedBy?: Moment;
  additionalDesc?: string;
  client?: IClient;
  service?: IService;
  shop?: IShop;
}

export class Booking implements IBooking {
  constructor(
    public id?: number,
    public createdDate?: Moment,
    public status?: string,
    public serviceStart?: Moment,
    public serviceEnd?: Moment,
    public acceptedDate?: Moment,
    public acceptedBy?: Moment,
    public additionalDesc?: string,
    public client?: IClient,
    public service?: IService,
    public shop?: IShop
  ) {}
}
