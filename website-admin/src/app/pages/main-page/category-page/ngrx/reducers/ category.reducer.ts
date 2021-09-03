import { Category } from '../../../../../model/category.model';
import { Action, createReducer, on } from '@ngrx/store';
import { BaseResponse } from 'src/app/model/base_response.model';
import { GET_CATEGORY_ACTION } from '../actions/category.action';
import { State } from '../state/category.selectors';




export const initialState = {
  result: {
    statusCode: 400,
    message: '',
    result: []
  }
};

const featureReducer = createReducer(
  initialState,
  on(GET_CATEGORY_ACTION, state => ({ ...state, prop: state })),

);
export function categoryReducer(state: State | undefined, action: Action) {
  return featureReducer(state, action);
}


