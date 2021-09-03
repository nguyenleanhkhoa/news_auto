import { Subscription } from 'rxjs';
import { WebSource } from 'src/app/model/WebSource.model';
import { WebsourceService } from 'src/app/services/WebSource-service/WebSource.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H' },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He' },
  { position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li' },
  { position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be' },
  { position: 5, name: 'Boron', weight: 10.811, symbol: 'B' },
  { position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C' },
  { position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N' },
  { position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O' },
  { position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F' },
  { position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne' },
];

@Component({
  selector: 'app-website-page',
  templateUrl: './website-page.component.html',
  styleUrls: ['./website-page.component.scss']
})
export class WebsitePageComponent implements OnInit {
  @ViewChild('paginator') paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'name', 'url', 'status', 'function'];
  dataTableDisplay: MatTableDataSource<any>;
  dataTable: WebSource[];
  constructor(
    private snackBar: MatSnackBar,
    private websiteService: WebsourceService
  ) { }

  ngOnInit(): void {
    this.loadData(0);
  }
  loadData(page: number): void {
    this.websiteService.getAllWebsite(page).subscribe((value) => {
      this.dataTable = value.result.content;
      this.dataTableDisplay = new MatTableDataSource<any>(this.dataTable);
      // this.paginator = this.dataCategoryDisplay.paginator;
      this.paginator.length = value.result.totalElements;
      this.paginator.pageSize = value.result.pageable.pageSize;
      this.paginator.pageIndex = page;
    });
  }
  onChangeStatus(websource: string): void {
    const websourceTmp = new WebSource().fromJson(websource);
    this.websiteService.updateWebSourceByWebSiteId(websourceTmp.id, websourceTmp).subscribe((value) => {
      this.snackBar.open(value.message);
      setTimeout(() => {
        this.snackBar.dismiss();
      }, 2000);
    });
  }

  onDeleteWebsite(id: number): void {

  }
}
