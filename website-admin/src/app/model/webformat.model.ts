import { Category } from './category.model';
import { WebSource } from './WebSource.model';

export interface WebFormat {
  id: number;
  webSource: WebSource;
  category: Category;
  categoryMenu: string;
  categoryParent: string;
  categoryFormat: string;
  format: string;
  formatContentDetail: string;
  createdAt: number;
  updatedAt: number;
}
