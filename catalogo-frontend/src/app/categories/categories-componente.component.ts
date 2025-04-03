import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { CategoriesViewModelService } from './categories-servicios.service';
import { DatePipe} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TypeValidator } from '../lib/my-core/directives/mis-validadores-directive'
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorMessagePipe } from '../lib/my-core';

@Component({
  selector: 'app-categories',
  templateUrl:'./tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => CategoriesAddComponent),
  forwardRef(() => CategoriesEditComponent),
  forwardRef(() => CategoriesViewComponent),
  forwardRef(() => CategoriesListComponent),
  ],
  })
  export class CategoriesComponent implements OnInit, OnDestroy {
   constructor(protected vm: CategoriesViewModelService) { }
   public get VM(): CategoriesViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }

  @Component({
    selector: 'app-categories-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class CategoriesListComponent implements OnInit, OnDestroy {
    constructor(protected vm: CategoriesViewModelService) { }
    public get VM(): CategoriesViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   

   @Component({
    selector: 'app-categories-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator,ErrorMessagePipe],
    })
    export class CategoriesAddComponent implements OnInit {
    constructor(protected vm: CategoriesViewModelService) { }
    public get VM(): CategoriesViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    }
    }
    
    @Component({
      selector: 'app-categories-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator,ErrorMessagePipe],
      })
      
      export class CategoriesEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: CategoriesViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): CategoriesViewModelService { return this.vm; }
      ngOnInit(): void {
      this.obs$ = this.route.paramMap.subscribe(
      (params: ParamMap) => {
      const id = parseInt(params?.get('id') ?? '');
      if (id) {
      this.vm.edit(id);
      } else {
      this.router.navigate(['/404.html']);
      }
      });
      }
      ngOnDestroy(): void {
      this.obs$!.unsubscribe();
      }
      }
      
      @Component({
        selector: 'app-categories-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
        imports: [DatePipe],
        })
        export class CategoriesViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: CategoriesViewModelService, protected router: Router) { }
        public get VM(): CategoriesViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const CATEGORIES_COMPONENTES = [
    CategoriesComponent, CategoriesListComponent, CategoriesAddComponent,
    CategoriesEditComponent, CategoriesViewComponent,
   ];
   


   
  
