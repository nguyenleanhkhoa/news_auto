import { Category } from './category.model';
import { News } from './news.model';

export interface Content {
  id: number;
  timeContent: string;
  category: Category;
  content: string;
  news: News;
  createdAt: number;
  updatedAt: number;
}
