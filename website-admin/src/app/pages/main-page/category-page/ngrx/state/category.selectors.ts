import { createSelector } from "@ngrx/store";
import { Category } from "src/app/model/category.model";

export interface State {
  result: {
    statusCode: 400,
    message: '',
    result: []
  };
}

export const selectCategory = createSelector(
  (state: State) => state.result.result,
  (category: Array<Category>) => category
);
