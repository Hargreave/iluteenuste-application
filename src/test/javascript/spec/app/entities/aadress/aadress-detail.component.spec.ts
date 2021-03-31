import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IluteenusteSystemTestModule } from '../../../test.module';
import { AadressDetailComponent } from 'app/entities/aadress/aadress-detail.component';
import { Aadress } from 'app/shared/model/aadress.model';

describe('Component Tests', () => {
  describe('Aadress Management Detail Component', () => {
    let comp: AadressDetailComponent;
    let fixture: ComponentFixture<AadressDetailComponent>;
    const route = ({ data: of({ aadress: new Aadress(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [IluteenusteSystemTestModule],
        declarations: [AadressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AadressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AadressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load aadress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.aadress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
