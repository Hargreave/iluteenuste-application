import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { ServiceAssociationWithShopUpdateComponent } from 'app/entities/service-association-with-shop/service-association-with-shop-update.component';
import { ServiceAssociationWithShopService } from 'app/entities/service-association-with-shop/service-association-with-shop.service';
import { ServiceAssociationWithShop } from 'app/shared/model/service-association-with-shop.model';

describe('Component Tests', () => {
  describe('ServiceAssociationWithShop Management Update Component', () => {
    let comp: ServiceAssociationWithShopUpdateComponent;
    let fixture: ComponentFixture<ServiceAssociationWithShopUpdateComponent>;
    let service: ServiceAssociationWithShopService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [ServiceAssociationWithShopUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ServiceAssociationWithShopUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceAssociationWithShopUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceAssociationWithShopService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ServiceAssociationWithShop(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ServiceAssociationWithShop();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
