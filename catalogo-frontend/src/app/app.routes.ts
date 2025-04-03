import { Routes } from '@angular/router';
import { ActorsAddComponent, ActorsEditComponent, ActorsListComponent, ActorsViewComponent } from './actors';
import { CategoriesAddComponent, CategoriesListComponent, CategoriesViewComponent } from './categories';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

export const routes: Routes = [
    { path: 'actors', children: [
        { path: '', component: ActorsListComponent},
        { path: 'add', component: ActorsAddComponent},
        { path: ':id/edit', component: ActorsEditComponent},
        { path: ':id', component: ActorsViewComponent},
        { path: ':id/:kk', component: ActorsViewComponent},
        ]},
    { path: 'categories', children: [
            { path: '', component: CategoriesListComponent},
            { path: 'add', component: CategoriesAddComponent},
            { path: ':id/edit', component: CategoriesAddComponent},
            { path: ':id', component: CategoriesViewComponent},
            { path: ':id/:kk', component: CategoriesViewComponent},
            ]},

    { path: "**", component: PageNotFoundComponent }
    ]
