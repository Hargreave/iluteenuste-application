import { IClient } from 'app/shared/model/client.model';

export interface IShop {
  id?: number;
  name?: string;
  aadress?: string;
  xCoordinate?: number;
  yCoordinate?: number;
  clients?: IClient[];
}

export class Shop implements IShop {
  constructor(
    public id?: number,
    public name?: string,
    public aadress?: string,
    public xCoordinate?: number,
    public yCoordinate?: number,
    public clients?: IClient[]
  ) {}
}
