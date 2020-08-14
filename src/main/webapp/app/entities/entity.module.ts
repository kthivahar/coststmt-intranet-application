import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'manufactring-cost-stmt',
        loadChildren: () =>
          import('./manufactring-cost-stmt/manufactring-cost-stmt.module').then(
            m => m.CostStmtIntranetApplicationManufactringCostStmtModule
          ),
      },
      {
        path: 'item',
        loadChildren: () => import('./item/item.module').then(m => m.CostStmtIntranetApplicationItemModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CostStmtIntranetApplicationEntityModule {}
