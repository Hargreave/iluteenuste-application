import { IShop } from './shop.model';

export interface IAadress {
  id?: number;
  fullAadress?: string;
  zipCode?: string;
  xCoordinate?: number;
  yCoordinate?: number;
  city?: string;
  county?: string;
  countryCarCode?: string;
  shop?: IShop;
}

export class Aadress implements IAadress {
  constructor(
    public id?: number,
    public fullAadress?: string,
    public zipCode?: string,
    public xCoordinate?: number,
    public yCoordinate?: number,
    public city?: string,
    public county?: string,
    public countryCarCode?: string,
    public shop?: IShop
  ) {}
}
