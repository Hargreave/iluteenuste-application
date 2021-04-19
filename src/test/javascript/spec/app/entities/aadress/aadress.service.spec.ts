import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AadressService } from 'app/entities/aadress/aadress.service';
import { IAadress, Aadress } from 'app/shared/model/aadress.model';

describe('Service Tests', () => {
  describe('Aadress Service', () => {
    let injector: TestBed;
    let service: AadressService;
    let httpMock: HttpTestingController;
    let elemDefault: IAadress;
    let expectedResult: IAadress | IAadress[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AadressService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Aadress(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Aadress', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Aadress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Aadress', () => {
        const returnedFromService = Object.assign(
          {
            fullAadress: 'BBBBBB',
            zipCode: 'BBBBBB',
            xCoordinate: 1,
            yCoordinate: 1,
            country: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Aadress', () => {
        const returnedFromService = Object.assign(
          {
            fullAadress: 'BBBBBB',
            zipCode: 'BBBBBB',
            xCoordinate: 1,
            yCoordinate: 1,
            country: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Aadress', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
