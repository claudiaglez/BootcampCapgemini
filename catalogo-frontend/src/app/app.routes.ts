import { Routes } from '@angular/router';
import { ActorsAddComponent, ActorsEditComponent, ActorsListComponent, ActorsViewComponent } from './actors';

export const routes: Routes = [
    { path: 'actors', children: [
        { path: '', component: ActorsListComponent},
        { path: 'add', component: ActorsAddComponent},
        { path: ':id/edit', component: ActorsEditComponent},
        { path: ':id', component: ActorsViewComponent},
        { path: ':id/:kk', component: ActorsViewComponent},
        ]},
    ]
