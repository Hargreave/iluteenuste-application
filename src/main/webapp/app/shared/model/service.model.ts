import { IBooking } from 'app/shared/model/booking.model';
import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';

export interface IService {
  id?: number;
  name?: string;
  sex?: Sex;
  forChild?: boolean;
  bookings?: IBooking[];
  serviceAssociationWithShops?: IServiceAssociationWithShop[];
}

export class Service implements IService {
  constructor(
    public id?: number,
    public name?: string,
    public sex?: Sex,
    public forChild?: boolean,
    public bookings?: IBooking[],
    public serviceAssociationWithShops?: IServiceAssociationWithShop[]
  ) {
    this.forChild = this.forChild || false;
  }
}
