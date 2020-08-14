import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CostStmtIntranetApplicationSharedModule } from 'app/shared/shared.module';
import { ManufactringCostStmtComponent } from './manufactring-cost-stmt.component';
import { ManufactringCostStmtDetailComponent } from './manufactring-cost-stmt-detail.component';
import { ManufactringCostStmtUpdateComponent } from './manufactring-cost-stmt-update.component';
import { ManufactringCostStmtDeleteDialogComponent } from './manufactring-cost-stmt-delete-dialog.component';
import { manufactringCostStmtRoute } from './manufactring-cost-stmt.route';

@NgModule({
  imports: [CostStmtIntranetApplicationSharedModule, RouterModule.forChild(manufactringCostStmtRoute)],
  declarations: [
    ManufactringCostStmtComponent,
    ManufactringCostStmtDetailComponent,
    ManufactringCostStmtUpdateComponent,
    ManufactringCostStmtDeleteDialogComponent,
  ],
  entryComponents: [ManufactringCostStmtDeleteDialogComponent],
})
export class CostStmtIntranetApplicationManufactringCostStmtModule {}
