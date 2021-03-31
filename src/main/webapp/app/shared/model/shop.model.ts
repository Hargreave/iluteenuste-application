import { Moment } from 'moment';
import { IAadress } from './aadress.model';
import { IClient } from './client.model';

export interface IShop {
  id?: number;
  name?: string;
  description?: string;
  createdBy?: string;
  createdDate?: Moment;
  modifiedBy?: string;
  modifiedDate?: Moment;
  aadresses?: IAadress[];
  clients?: IClient[];
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public name?: string,
    public description?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public modifiedBy?: string,
    public modifiedDate?: Moment,
    public aadresses?: IAadress[],
    public clients?: IClient[]
  ) {}
}
