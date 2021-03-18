import { IUser } from 'app/core/user/user.model';

export interface IClient {
  id?: number;
  isShopOwner?: boolean;
  tel?: string;
  user?: IUser;
}

export class Client implements IClient {
  constructor(public id?: number, public isShopOwner?: boolean, public tel?: string, public user?: IUser) {
    this.isShopOwner = this.isShopOwner || false;
  }
}
