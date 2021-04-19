import { IShop } from 'app/shared/model/shop.model';

export interface IAadress {
  id?: number;
  fullAadress?: string;
  zipCode?: string;
  xCoordinate?: number;
  yCoordinate?: number;
  country?: string;
  shop?: IShop;
}

export class Aadress implements IAadress {
  constructor(
    public id?: number,
    public fullAadress?: string,
    public zipCode?: string,
    public xCoordinate?: number,
    public yCoordinate?: number,
    public country?: string,
    public shop?: IShop
  ) {}
}
