import { Routes } from '@angular/router';
import { ActorsAddComponent, ActorsEditComponent, ActorsListComponent, ActorsViewComponent } from './actors';
import { CategoriesAddComponent, CategoriesListComponent, CategoriesViewComponent } from './categories';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { LanguagesAddComponent, LanguagesEditComponent, LanguagesListComponent, LanguagesViewComponent } from './languages';

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
    { path: 'languages', children: [
                { path: '', component: LanguagesListComponent},
                { path: 'add', component: LanguagesAddComponent},
                { path: ':id/edit', component: LanguagesAddComponent},
                { path: ':id', component: LanguagesViewComponent},
                { path: ':id/:kk', component: LanguagesViewComponent},
                ]},

    { path: "**", component: PageNotFoundComponent }
    ]
