import { IAadress } from 'app/shared/model/aadress.model';
import { IBooking } from 'app/shared/model/booking.model';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { IClient } from 'app/shared/model/client.model';

export interface IShop {
  id?: number;
  name?: string;
  description?: string;
  imgAadress?: string;
  createdBy?: string;
  createdDate?: Date;
  modifiedBy?: string;
  modifiedDate?: Date;
  aadress?: IAadress;
  bookings?: IBooking[];
  serviceAssociationWithShops?: IServiceAssociationWithShop[];
  clients?: IClient[];
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public imgAadress?: string,
    public createdBy?: string,
    public createdDate?: Date,
    public modifiedBy?: string,
    public modifiedDate?: Date,
    public aadress?: IAadress,
    public bookings?: IBooking[],
    public serviceAssociationWithShops?: IServiceAssociationWithShop[],
    public clients?: IClient[]
  ) {}
}
