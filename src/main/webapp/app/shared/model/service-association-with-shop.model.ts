import { IReview } from 'app/shared/model/review.model';
import { IService } from 'app/shared/model/service.model';
import { IShop } from 'app/shared/model/shop.model';

export interface IServiceAssociationWithShop {
  id?: number;
  price?: number;
  time?: number;
  reviews?: IReview[];
  service?: IService;
  shop?: IShop;
}

export class ServiceAssociationWithShop implements IServiceAssociationWithShop {
  constructor(
    public id?: number,
    public price?: number,
    public time?: number,
    public reviews?: IReview[],
    public service?: IService,
    public shop?: IShop
  ) {}
}
