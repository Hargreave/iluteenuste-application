import { Moment } from 'moment';
import { IBooking } from 'app/shared/model/booking.model';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { IAadress } from 'app/shared/model/aadress.model';
import { IClient } from 'app/shared/model/client.model';

export interface IShop {
  id?: number;
  name?: string;
  description?: string;
  imgAadress?: string;
  createdBy?: string;
  createdDate?: Moment;
  modifiedBy?: string;
  modifiedDate?: Moment;
  bookings?: IBooking[];
  serviceAssociationWithShops?: IServiceAssociationWithShop[];
  aadresses?: IAadress[];
  clients?: IClient[];
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public imgAadress?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public modifiedBy?: string,
    public modifiedDate?: Moment,
    public bookings?: IBooking[],
    public serviceAssociationWithShops?: IServiceAssociationWithShop[],
    public aadresses?: IAadress[],
    public clients?: IClient[]
  ) {}
}
