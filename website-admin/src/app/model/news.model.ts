import { Category } from 'src/app/model/category.model';
import { Content } from './content.model';
import { WebSource } from './WebSource.model';

export interface News {
  id: number;
  title: string;
  link: string;
  category: Category;
  webSource: WebSource;
  image: string;
  description: string;
  href: string;
  status: number;
  numberOfAccess: number;
  createdAt: number;
  updatedAt: number;
}
