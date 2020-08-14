import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IManufactringCostStmt, ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';
import { ManufactringCostStmtService } from './manufactring-cost-stmt.service';
import { ManufactringCostStmtComponent } from './manufactring-cost-stmt.component';
import { ManufactringCostStmtDetailComponent } from './manufactring-cost-stmt-detail.component';
import { ManufactringCostStmtUpdateComponent } from './manufactring-cost-stmt-update.component';

@Injectable({ providedIn: 'root' })
export class ManufactringCostStmtResolve implements Resolve<IManufactringCostStmt> {
  constructor(private service: ManufactringCostStmtService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IManufactringCostStmt> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((manufactringCostStmt: HttpResponse<ManufactringCostStmt>) => {
          if (manufactringCostStmt.body) {
            return of(manufactringCostStmt.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ManufactringCostStmt());
  }
}

export const manufactringCostStmtRoute: Routes = [
  {
    path: '',
    component: ManufactringCostStmtComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'costStmtIntranetApplicationApp.manufactringCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ManufactringCostStmtDetailComponent,
    resolve: {
      manufactringCostStmt: ManufactringCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'costStmtIntranetApplicationApp.manufactringCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ManufactringCostStmtUpdateComponent,
    resolve: {
      manufactringCostStmt: ManufactringCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'costStmtIntranetApplicationApp.manufactringCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ManufactringCostStmtUpdateComponent,
    resolve: {
      manufactringCostStmt: ManufactringCostStmtResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'costStmtIntranetApplicationApp.manufactringCostStmt.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
