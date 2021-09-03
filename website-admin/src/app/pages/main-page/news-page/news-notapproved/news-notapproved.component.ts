import { SelectionModel } from '@angular/cdk/collections';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatRadioGroup } from '@angular/material/radio';
import { MatTableDataSource } from '@angular/material/table';
import { News } from 'src/app/model/news.model';
import { CategoryService } from 'src/app/services/category-service/category.service';
import { NewsService } from 'src/app/services/news-service/news.service';

@Component({
  selector: 'app-news-notapproved',
  templateUrl: './news-notapproved.component.html',
  styleUrls: ['./news-notapproved.component.scss']
})
export class NewsNotapprovedComponent implements OnInit {
  @ViewChild('matPaginator', { static: true }) paginator: MatPaginator;
  @ViewChild(MatRadioGroup, { static: true }) radiogroup: MatRadioGroup;
  @Input() data: News;

  titleStr: string;
  isLoadingResults = true;
  isRateLimitReached = false;
  isPostApproved = 1;
  selection = new SelectionModel<News>(true, []);
  dataSourceNotApproved: any;
  dataSourceCategory: any;
  dataNews: any[];
  dataColumn: string[] = [
    'id',
    'title',
    'category',
    'link',
    'display',
    'action'
  ];
  dataColumnCategory: string[] = ['name'];
  saveSuccess = false;
  saveFailed = false;
  titleNews: string;
  searchText: string;
  idButtonCategory: string;
  categorySelected: string;
  listNewsChanged: Array<News> = [];
  totalPage = 0 ;
  totalNews = 0 ;
  pageIndex = 0 ;
  lastPage = 0;
  statusOfNews = 0;
  displayOfCategory = 1;
  constructor(private newsService: NewsService,
    private categoryService: CategoryService,
    private adminNewsService: NewsService,
     public dialog: MatDialog) { }

 /**
   * On init
   */
  ngOnInit() {
    this.getAllCategoryByStatus(1);
    this.getAllNewsNotApproved(0);
  }

  /**
   * After Content checked
   */
  ngAfterContentChecked(): void {
    this.paginator.nextPage = () => {
      this.pageIndex++;
      this.getAllNewsNotApproved(this.pageIndex);
    };
    this.paginator.previousPage = () => {
      this.pageIndex--;
      this.getAllNewsNotApproved(this.pageIndex);
    }
    this.paginator.firstPage = () => {
      this.pageIndex = 0;
      this.getAllNewsNotApproved(this.pageIndex);
    }
    this.paginator.lastPage = () => {
      this.pageIndex = this.totalPage;
      this.getAllNewsNotApproved(this.pageIndex);
    }
  }

  /**
   * Get all news not approved
   */
  getAllNewsNotApproved(page: number) {
    this.newsService.getAllNewsByWebsiteHasStatus(0, page).subscribe(data => {
      this.totalPage = data['totalPages'] - 1;
      this.totalNews = data['totalElements'];
      this.dataNews = data['content'];
      this.paginator.pageIndex = data['number'];
      this.paginator.length = data['totalElements'];
      this.pageIndex = this.paginator.pageIndex;
      this.dataSourceNotApproved = new MatTableDataSource<any>(this.dataNews);
      // this.dataSourceApproved.paginator = this.paginator;
    });
  }

  /**
   * Get all category by status
   * @param status
   */
  getAllCategoryByStatus(status: number) {
    this.categoryService.getAllCategoryByStatus(status).subscribe(value => {
      this.dataSourceCategory = new MatTableDataSource<any>(value);
    });
  }

  /**
   * Refresh data not approved
   */
  onRefreshDataNotApproved() {
    this.titleStr = '';
    this.dataNews = [];
    // if (!isNullOrUndefined(this.categorySelected)) {
    //   this.getNewsByCategory(this.categorySelected,this.pageIndex);
    // } else {
    //   this.getAllNewsNotApproved(this.pageIndex);
    // }
  }

  refreshAllData() {
    this.titleStr = '';
    this.dataNews = [];
    this.dataSourceNotApproved = [];
    this.dataSourceCategory = [];
    this.getAllNewsNotApproved(this.pageIndex);
    this.getAllCategoryByStatus(1);
  }

  /**
   * On Selected Row
   * @param data
   */
  onSelectedRow(data: any) {
    // this.adminNewsService.setData(data);
    // const dialogRef = this.dialog.open(DialogShowNewsInformationComponent, {
    //   width: '700px',
    //   height: '900px'
    // });

    // dialogRef.afterClosed().subscribe(result => {});
  }

  /**
   * On Category Selected
   * @param categoryData
   */
  onCategorySelected(categoryData: any) {

    this.dataNews = [];
    this.dataSourceNotApproved = [];
    this.getNewsByCategory(categoryData.categoryName,this.pageIndex);
  }

  /**
   * On Category selected for news not approved
   * @param categoryData
   */
  onCategorySelectedForNewsNotApproved(categoryData: any, event: any) {
    if (this.idButtonCategory !== event.target.id) {
    //   $('#' + this.idButtonCategory).css({
    //     backgroundColor: 'white',
    //     color: 'black'
    //   });
    // }
    // $('#' + event.target.id).css({
    //   backgroundColor: 'rgb(65, 179, 179)',
    //   color: 'white'
    // });
    this.categorySelected = categoryData.categoryName;
    this.idButtonCategory = event.target.id;
    this.dataNews = [];
    this.dataSourceNotApproved = [];
    this.getNewsByCategory(categoryData.categoryName,this.pageIndex);
  }
}

  /**
   * Get News By Category
   * @param categoryName category name
   */
  getNewsByCategory(categoryName: string, page: number) {
    this.newsService
    .getNewsNotApprovedByCategory(
      categoryName, page)
    .subscribe(data => {
      this.totalPage = data['totalPages'] - 1;
      this.totalNews = data['totalElements'];
      this.dataNews = data['content'];
      this.paginator.pageIndex = data['number'];
      this.paginator.length = data['totalElements'];
      this.dataSourceNotApproved = new MatTableDataSource<any>(this.dataNews);
      // this.dataSourceNotApproved.paginator = this.paginator;
    });
  }

  /**
   * Check is all row is selected
   */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSourceNotApproved.data.length;
    return numSelected === numRows;
  }

  masterToggle() {
    this.isAllSelected()
      ? this.selection.clear()
      : this.dataSourceNotApproved.data.forEach(row => this.selection.select(row));
  }

  /**
   * On Change news
   */
  onChangeNews(row: News) {
    let currentState = false;
    if (row.status === 1) {
      currentState = true;
    }
    row.status = !currentState ? 1 : 0;
    this.listNewsChanged.push(row);
    this.onSaveNews();
  }

  /**
   * On Search title
   */
  onSearchTitle() {
    // if (!isNullOrUndefined(this.titleStr)) {
    //   this.newsService.getNewsByTitle(this.titleStr).subscribe(data => {
    //     this.totalPage = data['totalPages'] - 1;
    //     this.totalNews = data['totalElements'];
    //     this.dataNews = data['content'];
    //     this.paginator.pageIndex = data['number'];
    //     this.paginator.length = data['totalElements'];
    //     this.dataSourceNotApproved = new MatTableDataSource<any>(this.dataNews);
    //   });
    // } else {
    //   this.onRefreshDataNotApproved();
    // }
  }

  /**
   * On Save News
   */
  onSaveNews() {
    if (this.listNewsChanged.length !== 0) {
      this.listNewsChanged.forEach(value => {
        const news: News = value;
        if (news.status) {
          news.status = 1;
        } else {
          news.status = 0;
        }
        this.titleNews = news.title;
        this.newsService.updateNewsById(value.id, news, this.onSaveNewsSuccess);
      });
    }
  }

  /**
   * On Save news success
   */
  onSaveNewsSuccess = () => {
    this.listNewsChanged = [];
    this.saveSuccess = true;
    this.onRefreshDataNotApproved();
    setTimeout(() => {
      this.saveSuccess = false;
    }, 2000);
  }

}
