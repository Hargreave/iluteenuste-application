import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { AadressComponent } from 'app/entities/aadress/aadress.component';
import { AadressService } from 'app/entities/aadress/aadress.service';
import { Aadress } from 'app/shared/model/aadress.model';

describe('Component Tests', () => {
  describe('Aadress Management Component', () => {
    let comp: AadressComponent;
    let fixture: ComponentFixture<AadressComponent>;
    let service: AadressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [AadressComponent],
      })
        .overrideTemplate(AadressComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AadressComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AadressService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Aadress(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aadresses && comp.aadresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
