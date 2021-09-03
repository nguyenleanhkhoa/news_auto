import { CategoryService } from 'src/app/services/category-service/category.service';
import { Category } from 'src/app/model/category.model';
import { Component, Inject, OnInit, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-dialog-category',
  templateUrl: './dialog-category.component.html',
  styleUrls: ['./dialog-category.component.css'],
})
export class DialogCategoryComponent implements OnInit {

  name: string;
  categoryName: string;
  status: number;
  categoryService: CategoryService;
  isUpdate = false;

  constructor(
    public dialogRef: MatDialogRef<DialogCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Category,
    private _categoryService: CategoryService,
    private _snackBar: MatSnackBar,
  ) {
    this.categoryService = _categoryService;
  }

  ngOnInit(): void {
    if (this.data !== null) {
      this.isUpdate = true;
      this.name = this.data.name;
      this.categoryName = this.data.categoryName;
    }

  }
  addCategory(): void {
    const cateValue = {
      name: this.name,
      categoryName: this.categoryName,
      display: this.status ? 1 : 0,
    };
    this._categoryService.createNewCategory(cateValue).subscribe((value) => {
      this._snackBar.open(value.message);
      setTimeout(() => {
        this._snackBar.dismiss();
      }, 2000);
    });
  }
  updateCategory(): void {
    const cateValue = {
      id: this.data.id,
      name: this.name,
      categoryName: this.categoryName,
      display: this.status ? 1 : 0,
    };
    this._categoryService.updateCategoryById(cateValue).subscribe((value) => {
      this._snackBar.open(value.message);
      setTimeout(() => {
        this._snackBar.dismiss();
      }, 2000);
    });
  }
}
