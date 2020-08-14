import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';
import { ManufactringCostStmtService } from './manufactring-cost-stmt.service';

@Component({
  templateUrl: './manufactring-cost-stmt-delete-dialog.component.html',
})
export class ManufactringCostStmtDeleteDialogComponent {
  manufactringCostStmt?: IManufactringCostStmt;

  constructor(
    protected manufactringCostStmtService: ManufactringCostStmtService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.manufactringCostStmtService.delete(id).subscribe(() => {
      this.eventManager.broadcast('manufactringCostStmtListModification');
      this.activeModal.close();
    });
  }
}
