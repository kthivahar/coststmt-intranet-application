import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ManufactringCostStmtService } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt.service';
import { IManufactringCostStmt, ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

describe('Service Tests', () => {
  describe('ManufactringCostStmt Service', () => {
    let injector: TestBed;
    let service: ManufactringCostStmtService;
    let httpMock: HttpTestingController;
    let elemDefault: IManufactringCostStmt;
    let expectedResult: IManufactringCostStmt | IManufactringCostStmt[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ManufactringCostStmtService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ManufactringCostStmt(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ManufactringCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ManufactringCostStmt()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ManufactringCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            companyName: 'BBBBBB',
            uniqueEntityNumberUen: 'BBBBBB',
            address: 'BBBBBB',
            nameOfContactPerson: 'BBBBBB',
            designation: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            exporterCompanyName: 'BBBBBB',
            exporterAddress: 'BBBBBB',
            euCompanyName: 'BBBBBB',
            country: 'BBBBBB',
            radio1: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ManufactringCostStmt', () => {
        const returnedFromService = Object.assign(
          {
            companyName: 'BBBBBB',
            uniqueEntityNumberUen: 'BBBBBB',
            address: 'BBBBBB',
            nameOfContactPerson: 'BBBBBB',
            designation: 'BBBBBB',
            contactNo: 'BBBBBB',
            email: 'BBBBBB',
            exporterCompanyName: 'BBBBBB',
            exporterAddress: 'BBBBBB',
            euCompanyName: 'BBBBBB',
            country: 'BBBBBB',
            radio1: 'BBBBBB',
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

      it('should delete a ManufactringCostStmt', () => {
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
