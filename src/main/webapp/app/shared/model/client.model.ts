import { IUser } from 'app/core/user/user.model';
import { IBooking } from 'app/shared/model/booking.model';
import { IReview } from 'app/shared/model/review.model';
import { IShop } from 'app/shared/model/shop.model';

export interface IClient {
  id?: number;
  isShopOwner?: boolean;
  tel?: string;
  user?: IUser;
  bookings?: IBooking[];
  reviews?: IReview[];
  shops?: IShop[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public isShopOwner?: boolean,
    public tel?: string,
    public user?: IUser,
    public bookings?: IBooking[],
    public reviews?: IReview[],
    public shops?: IShop[]
  ) {
    this.isShopOwner = this.isShopOwner || false;
  }
}
