import { Routes } from '@angular/router';
import { ContactosAddComponent, ContactosEditComponent, ContactosListComponent, ContactosViewComponent } from './contactos';
import { HomeComponent } from './main';
import { DemosComponent } from './demos/demos.component';
import { FormulariosComponent } from './ejemplos/formularios/formularios.component';
import { PageNotFoundComponent } from './main/page-not-found/page-not-found.component';

export const routes: Routes = [
    
    { path: 'contactos', children: [
        { path: '', component: ContactosListComponent},
        { path: 'add', component: ContactosAddComponent},
        { path: ':id/edit', component: ContactosEditComponent},
        { path: ':id', component: ContactosViewComponent},
        { path: ':id/:kk', component: ContactosViewComponent},
        ]},

        {path: '', component: HomeComponent, pathMatch: 'full' },
        {path: 'inicio', component: HomeComponent, },
        {path: 'demos', component: DemosComponent, title: 'Demostración'},
        {path: 'esto/es/un/formulario', component: FormulariosComponent},
        {path: 'personas', component: FormulariosComponent},
        {path: 'personas/add', component: FormulariosComponent},
        {path: 'personas/:id/edit', component: FormulariosComponent},
        {path: 'personas/:id', component: FormulariosComponent},
        {path: 'personas/:id/:kk', component: FormulariosComponent},
        {path: 'pepito/grillo', redirectTo: '/persona/2'},
        {path: 'libros', children: [
          {path: '', component: FormulariosComponent},
          {path: 'add', component: FormulariosComponent},
          {path: ':id/edit', component: FormulariosComponent},
          {path: ':id', component: FormulariosComponent},
          {path: ':id/:kk', component: FormulariosComponent},
        ]},
        {path: '404.html', component: PageNotFoundComponent},
        {path: '**', component: PageNotFoundComponent}
      ];
       
