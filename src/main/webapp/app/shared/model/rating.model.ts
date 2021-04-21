export interface IRating {
  shopName?: string;
  shopRating?: number;
}

export class Rating implements IRating {
  constructor(shopName?: string, shopRating?: number) {}
}
