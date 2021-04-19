import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BookingService } from 'app/entities/booking/booking.service';
import { IBooking, Booking } from 'app/shared/model/booking.model';

describe('Service Tests', () => {
  describe('Booking Service', () => {
    let injector: TestBed;
    let service: BookingService;
    let httpMock: HttpTestingController;
    let elemDefault: IBooking;
    let expectedResult: IBooking | IBooking[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BookingService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Booking(0, currentDate, 'AAAAAAA', currentDate, currentDate, currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            serviceStart: currentDate.format(DATE_FORMAT),
            serviceEnd: currentDate.format(DATE_FORMAT),
            acceptedDate: currentDate.format(DATE_FORMAT),
            acceptedBy: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Booking', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            serviceStart: currentDate.format(DATE_FORMAT),
            serviceEnd: currentDate.format(DATE_FORMAT),
            acceptedDate: currentDate.format(DATE_FORMAT),
            acceptedBy: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            serviceStart: currentDate,
            serviceEnd: currentDate,
            acceptedDate: currentDate,
            acceptedBy: currentDate,
          },
          returnedFromService
        );

        service.create(new Booking()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Booking', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            serviceStart: currentDate.format(DATE_FORMAT),
            serviceEnd: currentDate.format(DATE_FORMAT),
            acceptedDate: currentDate.format(DATE_FORMAT),
            acceptedBy: currentDate.format(DATE_FORMAT),
            additionalDesc: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            serviceStart: currentDate,
            serviceEnd: currentDate,
            acceptedDate: currentDate,
            acceptedBy: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Booking', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            serviceStart: currentDate.format(DATE_FORMAT),
            serviceEnd: currentDate.format(DATE_FORMAT),
            acceptedDate: currentDate.format(DATE_FORMAT),
            acceptedBy: currentDate.format(DATE_FORMAT),
            additionalDesc: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            serviceStart: currentDate,
            serviceEnd: currentDate,
            acceptedDate: currentDate,
            acceptedBy: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Booking', () => {
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
