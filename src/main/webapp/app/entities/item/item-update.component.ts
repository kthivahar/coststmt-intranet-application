import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IItem, Item } from 'app/shared/model/item.model';
import { ItemService } from './item.service';
import { IManufactringCostStmt } from 'app/shared/model/manufactring-cost-stmt.model';
import { ManufactringCostStmtService } from 'app/entities/manufactring-cost-stmt/manufactring-cost-stmt.service';

@Component({
  selector: 'jhi-item-update',
  templateUrl: './item-update.component.html',
})
export class ItemUpdateComponent implements OnInit {
  isSaving = false;
  manufactringcoststmts: IManufactringCostStmt[] = [];

  editForm = this.fb.group({
    id: [],
    descriptionOfGoods: [],
    hsCode: [],
    unit: [],
    quantity: [],
    value: [],
    manufactringCostStmtId: [],
  });

  constructor(
    protected itemService: ItemService,
    protected manufactringCostStmtService: ManufactringCostStmtService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ item }) => {
      this.updateForm(item);

      this.manufactringCostStmtService
        .query()
        .subscribe((res: HttpResponse<IManufactringCostStmt[]>) => (this.manufactringcoststmts = res.body || []));
    });
  }

  updateForm(item: IItem): void {
    this.editForm.patchValue({
      id: item.id,
      descriptionOfGoods: item.descriptionOfGoods,
      hsCode: item.hsCode,
      unit: item.unit,
      quantity: item.quantity,
      value: item.value,
      manufactringCostStmtId: item.manufactringCostStmtId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const item = this.createFromForm();
    if (item.id !== undefined) {
      this.subscribeToSaveResponse(this.itemService.update(item));
    } else {
      this.subscribeToSaveResponse(this.itemService.create(item));
    }
  }

  private createFromForm(): IItem {
    return {
      ...new Item(),
      id: this.editForm.get(['id'])!.value,
      descriptionOfGoods: this.editForm.get(['descriptionOfGoods'])!.value,
      hsCode: this.editForm.get(['hsCode'])!.value,
      unit: this.editForm.get(['unit'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      value: this.editForm.get(['value'])!.value,
      manufactringCostStmtId: this.editForm.get(['manufactringCostStmtId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItem>>): void {
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

  trackById(index: number, item: IManufactringCostStmt): any {
    return item.id;
  }
}
