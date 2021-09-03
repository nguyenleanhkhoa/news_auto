export class BaseResponse<T> {
  statusCode?: number;
  message?: string;
  result?: T;
  constructor(statusCode: number, message: string, result: T) {
    this.statusCode = statusCode;
    this.message = message;
    this.result = result;
  }
}

export interface PageableModel<T> {
  content?: T;
  pageable?: Pageable;
  totalPages?: number;
  totalElements?: number;
  last?: boolean;
  size?: number;
  number?: number;
  sort?: Sort;
  numberOfElements?: number;
  first?: boolean;
  empty?: boolean;
}
export interface Pageable {
  sort?: Sort;
  offset?: number;
  pageNumber?: number;
  pageSize?: number;
  unpaged?: boolean;
  paged?: boolean;
}

export interface Sort {
  unsorted?: boolean;
  sorted?: boolean;
  empty?: boolean;
}
