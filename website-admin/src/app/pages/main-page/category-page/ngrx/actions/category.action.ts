import { Category } from '../../../../../model/category.model';
import { BaseResponse } from 'src/app/model/base_response.model';
import { Action, createAction, props } from '@ngrx/store';

export const GET_CATEGORY_ACTION = createAction(
  'GET_CATEGORY_ACTION',

);

