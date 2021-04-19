import { IServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';
import { IClient } from 'app/shared/model/client.model';

export interface IReview {
  id?: number;
  comment?: string;
  rating?: number;
  erviceAssociationWithShop?: IServiceAssociationWithShop;
  client?: IClient;
}

export class Review implements IReview {
  constructor(
    public id?: number,
    public comment?: string,
    public rating?: number,
    public erviceAssociationWithShop?: IServiceAssociationWithShop,
    public client?: IClient
  ) {}
}
