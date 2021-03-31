import { IUser } from '../../core/user/user.model';
import { IShop } from './shop.model';

export interface IClient {
  id?: number;
  isShopOwner?: boolean;
  tel?: string;
  user?: IUser;
  shops?: IShop[];
}

export class Client implements IClient {
  constructor(public id?: number, public isShopOwner?: boolean, public tel?: string, public user?: IUser, public shops?: IShop[]) {
    this.isShopOwner = this.isShopOwner || false;
  }
}
