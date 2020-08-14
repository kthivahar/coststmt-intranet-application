import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

@Component({
  selector: 'jhi-manufactring-cost-stmt-detail',
  templateUrl: './manufactring-cost-stmt-detail.component.html',
})
export class ManufactringCostStmtDetailComponent implements OnInit {
  manufactringCostStmt: IManufactringCostStmt | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufactringCostStmt }) => (this.manufactringCostStmt = manufactringCostStmt));
  }

  previousState(): void {
    window.history.back();
  }
}
