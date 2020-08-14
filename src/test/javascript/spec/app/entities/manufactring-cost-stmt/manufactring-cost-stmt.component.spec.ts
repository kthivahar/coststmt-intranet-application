import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { CostStmtIntranetApplicationTestModule } from '../../../test.module';
import { ManufactringCostStmtComponent } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt.component';
import { ManufactringCostStmtService } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt.service';
import { ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufactringCostStmt Management Component', () => {
    let comp: ManufactringCostStmtComponent;
    let fixture: ComponentFixture<ManufactringCostStmtComponent>;
    let service: ManufactringCostStmtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CostStmtIntranetApplicationTestModule],
        declarations: [ManufactringCostStmtComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(ManufactringCostStmtComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufactringCostStmtComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufactringCostStmtService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ManufactringCostStmt(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.manufactringCostStmts && comp.manufactringCostStmts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ManufactringCostStmt(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.manufactringCostStmts && comp.manufactringCostStmts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
