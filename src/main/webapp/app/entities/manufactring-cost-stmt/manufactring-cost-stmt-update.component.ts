import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IManufactringCostStmt, ManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';
import { ManufactringCostStmtService } from './manufactring-cost-stmt.service';

@Component({
  selector: 'jhi-manufactring-cost-stmt-update',
  templateUrl: './manufactring-cost-stmt-update.component.html',
})
export class ManufactringCostStmtUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    companyName: [],
    uniqueEntityNumberUen: [],
    address: [],
    nameOfContactPerson: [],
    designation: [],
    contactNo: [],
    email: [],
    exporterCompanyName: [],
    exporterAddress: [],
    euCompanyName: [],
    country: [],
    radio1: [],
  });

  constructor(
    protected manufactringCostStmtService: ManufactringCostStmtService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ manufactringCostStmt }) => {
      this.updateForm(manufactringCostStmt);
    });
  }

  updateForm(manufactringCostStmt: IManufactringCostStmt): void {
    this.editForm.patchValue({
      id: manufactringCostStmt.id,
      companyName: manufactringCostStmt.companyName,
      uniqueEntityNumberUen: manufactringCostStmt.uniqueEntityNumberUen,
      address: manufactringCostStmt.address,
      nameOfContactPerson: manufactringCostStmt.nameOfContactPerson,
      designation: manufactringCostStmt.designation,
      contactNo: manufactringCostStmt.contactNo,
      email: manufactringCostStmt.email,
      exporterCompanyName: manufactringCostStmt.exporterCompanyName,
      exporterAddress: manufactringCostStmt.exporterAddress,
      euCompanyName: manufactringCostStmt.euCompanyName,
      country: manufactringCostStmt.country,
      radio1: manufactringCostStmt.radio1,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const manufactringCostStmt = this.createFromForm();
    if (manufactringCostStmt.id !== undefined) {
      this.subscribeToSaveResponse(this.manufactringCostStmtService.update(manufactringCostStmt));
    } else {
      this.subscribeToSaveResponse(this.manufactringCostStmtService.create(manufactringCostStmt));
    }
  }

  private createFromForm(): IManufactringCostStmt {
    return {
      ...new ManufactringCostStmt(),
      id: this.editForm.get(['id'])!.value,
      companyName: this.editForm.get(['companyName'])!.value,
      uniqueEntityNumberUen: this.editForm.get(['uniqueEntityNumberUen'])!.value,
      address: this.editForm.get(['address'])!.value,
      nameOfContactPerson: this.editForm.get(['nameOfContactPerson'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      contactNo: this.editForm.get(['contactNo'])!.value,
      email: this.editForm.get(['email'])!.value,
      exporterCompanyName: this.editForm.get(['exporterCompanyName'])!.value,
      exporterAddress: this.editForm.get(['exporterAddress'])!.value,
      euCompanyName: this.editForm.get(['euCompanyName'])!.value,
      country: this.editForm.get(['country'])!.value,
      radio1: this.editForm.get(['radio1'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManufactringCostStmt>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
