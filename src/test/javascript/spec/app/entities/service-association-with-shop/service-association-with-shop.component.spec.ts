import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { ServiceAssociationWithShopComponent } from 'app/entities/service-association-with-shop/service-association-with-shop.component';
import { ServiceAssociationWithShopService } from 'app/entities/service-association-with-shop/service-association-with-shop.service';
import { ServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';

describe('Component Tests', () => {
  describe('ServiceAssociationWithShop Management Component', () => {
    let comp: ServiceAssociationWithShopComponent;
    let fixture: ComponentFixture<ServiceAssociationWithShopComponent>;
    let service: ServiceAssociationWithShopService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [ServiceAssociationWithShopComponent],
      })
        .overrideTemplate(ServiceAssociationWithShopComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceAssociationWithShopComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceAssociationWithShopService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ServiceAssociationWithShop(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.serviceAssociationWithShops && comp.serviceAssociationWithShops[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
