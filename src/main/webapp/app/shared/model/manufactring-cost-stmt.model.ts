import { IItem } from 'app/shared/model/item.model';

export interface IManufactringCostStmt {
  id?: number;
  companyName?: string;
  uniqueEntityNumberUen?: string;
  address?: string;
  nameOfContactPerson?: string;
  designation?: string;
  contactNo?: string;
  email?: string;
  exporterCompanyName?: string;
  exporterAddress?: string;
  euCompanyName?: string;
  country?: string;
  radio1?: string;
  items?: IItem[];
}

export class ManufactringCostStmt implements IManufactringCostStmt {
  constructor(
    public id?: number,
    public companyName?: string,
    public uniqueEntityNumberUen?: string,
    public address?: string,
    public nameOfContactPerson?: string,
    public designation?: string,
    public contactNo?: string,
    public email?: string,
    public exporterCompanyName?: string,
    public exporterAddress?: string,
    public euCompanyName?: string,
    public country?: string,
    public radio1?: string,
    public items?: IItem[]
  ) {}
}
