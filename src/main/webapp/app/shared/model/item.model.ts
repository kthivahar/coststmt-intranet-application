import { IManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';

export interface IItem {
  id?: number;
  descriptionOfGoods?: string;
  hsCode?: string;
  unit?: string;
  quantity?: number;
  value?: number;
  manufactringCostStmtId?: string;
  manufactringCostStmt?: IManufactringCostStmt;
}

export class Item implements IItem {
  constructor(
    public id?: number,
    public descriptionOfGoods?: string,
    public hsCode?: string,
    public unit?: string,
    public quantity?: number,
    public value?: number,
    public manufactringCostStmtId?: string,
    public manufactringCostStmt?: IManufactringCostStmt
  ) {}
}
