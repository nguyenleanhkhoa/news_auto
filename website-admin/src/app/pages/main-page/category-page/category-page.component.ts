import { MatPaginator } from '@angular/material/paginator';
import { DialogCategoryComponent } from './component/dialog-category/dialog-category.component';
import { CategoryService } from 'src/app/services/category-service/category.service';
import {
  BaseResponse,
  PageableModel,
} from './../../../model/base_response.model';
import { Observable } from 'rxjs';
import {
  AfterContentChecked,
  AfterViewInit,
  Input,
  ViewChild,
} from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { Category } from 'src/app/model/category.model';
import { Store } from '@ngrx/store';
import { Convert } from 'src/app/helper/Commonfeature';
import { MatDialog } from '@angular/material/dialog';
import { ThemePalette } from '@angular/material/core';
import { MatSnackBar } from '@angular/material/snack-bar';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss'],
})
export class CategoryPageComponent implements OnInit, AfterViewInit {
  @ViewChild(MatTable) table: MatTable<PeriodicElement>;
  @ViewChild('paginator') paginator: MatPaginator;
  dataCategoryDisplay: MatTableDataSource<any>;
  dataCategory: Category[];
  color: ThemePalette = 'accent';
  isDisable = false;
  displayedColumns: string[] = [
    'id',
    'name',
    'category_name',
    'display',
    'function',
  ];
  result: Observable<BaseResponse<Category>>;
  tableLength: number;
  page = 0;
  private _categoryService: CategoryService;
  constructor(
    private categoryService: CategoryService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar,
    store: Store<{ result: BaseResponse<Category> }>
  ) {
    this._categoryService = categoryService;
  }
  ngAfterViewInit(): void {
    this.paginator.nextPage = () => {
      this.page++;
      this.loadData(this.page);
    };
    this.paginator.previousPage = () => {
      this.page--;
      this.loadData(this.page);
    };
  }

  ngOnInit(): void {
    this.page = 0;
    this.loadData(0);
  }

  loadData(page: number): void {
    this._categoryService.getAllCategory(page).subscribe((value) => {
      this.dataCategory = value.result.content;
      this.dataCategoryDisplay = new MatTableDataSource<any>(this.dataCategory);
      // this.paginator = this.dataCategoryDisplay.paginator;
      this.paginator.length = value.result.totalElements;
      this.paginator.pageSize = value.result.pageable.pageSize;
      this.paginator.pageIndex = page;
      this.isDisable = false;
    });
  }

  onChangeStatus(e: Event, category: string): void {
    this.isDisable = true;
    const checked = e['checked'];
    const categoryTmp = new Category().fromJson(category);
    const cateValue = {
      id: categoryTmp.id,
      name: categoryTmp.name,
      categoryName: categoryTmp.categoryName,
      display: checked ? 1 : 0,
      createdAt: categoryTmp.createdAt,
      updatedAt: categoryTmp.updatedAt,
    };
    this._categoryService.updateCategoryById(cateValue).subscribe((value) => {
      this.snackBar.open(value.message);
      this.loadData(this.page);
      setTimeout(() => {
        this.snackBar.dismiss();
      }, 2000);
    });
  }

  onDeleteCategory(id: number): void {
    this.categoryService.deleteCategoryById(id).subscribe((value) => {
      this.snackBar.open(value.message);
      this.loadData(this.page);
      setTimeout(() => {
        this.snackBar.dismiss();
      }, 2000);
    });
  }

  onUpdateCategory(category: string): void {
    const categoryTmp = new Category().fromJson(category);
    const dialogRef = this.dialog.open(DialogCategoryComponent, {
      width: '300px',
      height: '310px',
      data: categoryTmp
    });
  }

  refreshData(): void {
    this.loadData(this.page);
    this.snackBar.open('Refresh thành công!');
    this.loadData(this.page);
    setTimeout(() => {
      this.snackBar.dismiss();
    }, 2000);
  }



  openDialog(): void {
    const dialogRef = this.dialog.open(DialogCategoryComponent, {
      width: '300px',
      height: '310px',
    });
  }


}
