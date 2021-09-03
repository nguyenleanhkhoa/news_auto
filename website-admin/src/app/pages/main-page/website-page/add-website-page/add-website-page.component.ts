import { SelectionModel } from '@angular/cdk/collections';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { News } from 'src/app/model/news.model';

import { WebSource } from 'src/app/model/WebSource.model';
import { WebformatService } from 'src/app/services/webformat-service/webformat.service';
import { WebsourceService } from 'src/app/services/WebSource-service/WebSource.service';

@Component({
  selector: 'app-add-website-page',
  templateUrl: './add-website-page.component.html',
  styleUrls: ['./add-website-page.component.css']
})
export class AddWebsitePageComponent implements OnInit {
  ngOnInit(): void {

  }
  // @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  // dataSource: any;
  // dataNews: any[];
  // dataColumn: string[] = [
  //   'id',
  //   'url',
  //   'name',
  //   'display',
  //   'notdisplay',
  //   'sum',
  //   'status',
  //   'action'
  // ];
  // idWebsite: number;
  // urlStr: string;
  // nameStr: string;
  // nodeRoot: string;
  // nodeTitle: string;
  // nodeDescription: string;
  // nodeImage: string;
  // categoryNameNodeStr: string;
  // timeNodeStr: string;
  // contentNodeStr: string;
  // updateSuccess = false;
  // updateFail = false;
  // updateProcessing = false;
  // saveSuccess: false;
  // selection = new SelectionModel<News>(true, []);

  // listRootNode: Array<any> = [];
  // listTitleNode: Array<any> = [];
  // listDescription: Array<any> = [];
  // listImageNode: Array<any> = [];
  // listCategoryName: Array<any> = [];
  // listTime: Array<any> = [];
  // listContent: Array<any> = [];
  // constructor(
  //   private websourceService: WebsourceService,
  //   private webformatService: WebformatService,
  //   public dialog: MatDialog,

  // ) { }



  // ngOnInit() {}

  // /**
  //  * After View init
  //  */
  // ngAfterViewInit(): void {
  //   this.onGetWebsiteData();
  // }

  // /**
  //  * On get website data
  //  */
  // onGetWebsiteData() {
  //   this.websourceService
  //     .getNewsDisplayAndNotDisplayOfWebsite()
  //     .subscribe(website => {
  //       this.dataNews = website;
  //       this.dataSource = new MatTableDataSource<any>(this.dataNews);
  //       this.dataSource.paginator = this.paginator;
  //     });
  // }

  // /**
  //  * On Selected Row
  //  */
  // onSelectedRow(dataRow: any) {
  //   // this.service.setData(dataRow);
  //   this.openDialogConfigWebsite(dataRow);
  // }

  // /**
  //  * On Delete Format
  //  */
  // onDeleteDataFormat() {
  //   this.webformatService.deleteWebformatByWebsourceId(this.idWebsite, null);
  // }

  // onDeleteWebsite(element: any) {
  //   this.idWebsite = element.id;
  //   this.webformatService.deleteWebformatByWebsourceId(
  //     element.id,
  //     this.onDeleteWebsourceSuccess
  //   );
  // }
  // onDeleteWebsourceSuccess = () => {
  //   this.websourceService.deleteWebSourceIdById(
  //     this.idWebsite,
  //     this.onDeleteWebformatSuccess
  //   );
  // }
  // onDeleteWebformatSuccess = () => {
  //   this.dataSource = [];
  //   this.websourceService.getALLNameOfNewsWebsite().subscribe(website => {
  //     this.dataNews = website;
  //     this.dataSource = new MatTableDataSource<any>(this.dataNews);
  //     this.dataSource.paginator = this.paginator;
  //   });
  // }

  // /**
  //  * On Success
  //  */
  // onSuccess = () => {
  //   this.updateSuccess = true;
  //   setTimeout(() => {
  //     this.updateProcessing = false;
  //   }, 3000);
  // }

  // /**
  //  * On Change Status
  //  */
  // onChangeStatus(row: any) {
  //   this.updateProcessing = true;
  //   const tempRow = {
  //     id: row[0],
  //     url: row[1],
  //     name: row[2],
  //     status: row[6]
  //   };
  //   const temp: any = tempRow;
  //   if (!temp.status) {
  //     temp.status = '1';
  //   } else {
  //     temp.status = '0';
  //   }
  //   this.websourceService.updateWebSourceByWebSiteId(
  //     row[0],
  //     temp,
  //     this.updateSuccessed,
  //     this.updateFailed
  //   );
  // }

  // /**
  //  * On Refresh data
  //  */
  // onRefreshData() {
  //   this.dataSource = [];
  //   this.onGetWebsiteData();
  // }

  // /**
  //  * Open dialog create website
  //  */
  // openDialogCreateWebsite() {
  //   // const dialogRef = this.dialog.open(DialogCreateWebsiteComponent, {
  //   //   width: '400px',
  //   //   height: '300px'
  //   // });

  //   // dialogRef.afterClosed().subscribe(result => {});
  // }

  // /**
  //  * Open dialog config website
  //  */
  // openDialogConfigWebsite(dataRow: any): void {
  //   // const dialogRef = this.dialog.open(DialogShowConfigWebsiteComponent, {
  //   //   width: '1000px',
  //   //   height: '800px',
  //   //   data: { data: dataRow }
  //   // });

  //   // dialogRef.afterClosed().subscribe(result => {});
  // }

  // /**
  //  * On Search
  //  */
  // onSearch() {
  //   // if (!isNullOrUndefined(this.urlStr)) {
  //   //   this.websourceService.searchByNameOrUrl(this.urlStr, this.searchSuccess);
  //   // } else {
  //   //   this.onGetWebsiteData();
  //   // }
  // }

  // /**
  //  * Search success
  //  */
  // searchSuccess = data => {
  //   const websiteList: WebSource[] = [];
  //   websiteList.push(data);
  //   this.dataSource = new MatTableDataSource<any>(websiteList);
  //   this.dataSource.paginator = this.paginator;
  // }

  // /**
  //  * Update success
  //  */
  // updateSuccessed = () => {
  //   this.updateSuccess = true;
  //   setTimeout(() => {
  //     this.updateSuccess = false;
  //   }, 3000);
  // }

  // /**
  //  * Update failed
  //  */
  // updateFailed = err => {
  //   console.log(err);
  //   this.updateFail = true;
  //   setTimeout(() => {
  //     this.updateFail = false;
  //   }, 3000);
  // }
}
