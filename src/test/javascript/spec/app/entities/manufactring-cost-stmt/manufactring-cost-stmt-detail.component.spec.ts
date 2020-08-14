import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CostStmtIntranetApplicationTestModule } from '../../../test.module';
import { ManufactringCostStmtDetailComponent } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt-detail.component';
import { ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufactringCostStmt Management Detail Component', () => {
    let comp: ManufactringCostStmtDetailComponent;
    let fixture: ComponentFixture<ManufactringCostStmtDetailComponent>;
    const route = ({ data: of({ manufactringCostStmt: new ManufactringCostStmt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CostStmtIntranetApplicationTestModule],
        declarations: [ManufactringCostStmtDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ManufactringCostStmtDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ManufactringCostStmtDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load manufactringCostStmt on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.manufactringCostStmt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
