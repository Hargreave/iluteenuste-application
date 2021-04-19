import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { ServiceAssociationWithShopDetailComponent } from 'app/entities/service-association-with-shop/service-association-with-shop-detail.component';
import { ServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';

describe('Component Tests', () => {
  describe('ServiceAssociationWithShop Management Detail Component', () => {
    let comp: ServiceAssociationWithShopDetailComponent;
    let fixture: ComponentFixture<ServiceAssociationWithShopDetailComponent>;
    const route = ({ data: of({ serviceAssociationWithShop: new ServiceAssociationWithShop(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [ServiceAssociationWithShopDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ServiceAssociationWithShopDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ServiceAssociationWithShopDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load serviceAssociationWithShop on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.serviceAssociationWithShop).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
