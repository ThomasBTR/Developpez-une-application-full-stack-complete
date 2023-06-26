import { Comment } from './comment.interface';

export interface Article {
    id: number;
    title: string;
    content: string;
    date : Date;
    author : string;
    theme : string;
    comments : Comment[];
}
