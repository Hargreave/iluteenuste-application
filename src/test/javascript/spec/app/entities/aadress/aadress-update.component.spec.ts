import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { AadressUpdateComponent } from 'app/entities/aadress/aadress-update.component';
import { AadressService } from 'app/entities/aadress/aadress.service';
import { Aadress } from 'app/shared/model/aadress.model';

describe('Component Tests', () => {
  describe('Aadress Management Update Component', () => {
    let comp: AadressUpdateComponent;
    let fixture: ComponentFixture<AadressUpdateComponent>;
    let service: AadressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [AadressUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AadressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AadressUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AadressService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Aadress(123);
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
        const entity = new Aadress();
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
