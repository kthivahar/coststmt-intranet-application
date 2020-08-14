import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

type EntityResponseType = HttpResponse<IManufactringCostStmt>;
type EntityArrayResponseType = HttpResponse<IManufactringCostStmt[]>;

@Injectable({ providedIn: 'root' })
export class ManufactringCostStmtService {
  public resourceUrl = SERVER_API_URL + 'api/manufactring-cost-stmts';

  constructor(protected http: HttpClient) {}

  create(manufactringCostStmt: IManufactringCostStmt): Observable<EntityResponseType> {
    return this.http.post<IManufactringCostStmt>(this.resourceUrl, manufactringCostStmt, { observe: 'response' });
  }

  update(manufactringCostStmt: IManufactringCostStmt): Observable<EntityResponseType> {
    return this.http.put<IManufactringCostStmt>(this.resourceUrl, manufactringCostStmt, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IManufactringCostStmt>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IManufactringCostStmt[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
