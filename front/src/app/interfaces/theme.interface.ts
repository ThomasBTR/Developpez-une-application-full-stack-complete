import {UserThemeInfo} from "./userThemeInfo.interface";

export interface Theme {
  id: number;
  title: string;
  description: string;
  users: UserThemeInfo[];
}
