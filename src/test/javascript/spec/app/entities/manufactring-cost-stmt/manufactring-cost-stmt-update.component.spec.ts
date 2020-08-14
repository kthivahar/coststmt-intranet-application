import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CostStmtIntranetApplicationTestModule } from '../../../test.module';
import { ManufactringCostStmtUpdateComponent } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt-update.component';
import { ManufactringCostStmtService } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt.service';
import { ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

describe('Component Tests', () => {
  describe('ManufactringCostStmt Management Update Component', () => {
    let comp: ManufactringCostStmtUpdateComponent;
    let fixture: ComponentFixture<ManufactringCostStmtUpdateComponent>;
    let service: ManufactringCostStmtService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CostStmtIntranetApplicationTestModule],
        declarations: [ManufactringCostStmtUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ManufactringCostStmtUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManufactringCostStmtUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManufactringCostStmtService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ManufactringCostStmt(123);
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
        const entity = new ManufactringCostStmt();
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
