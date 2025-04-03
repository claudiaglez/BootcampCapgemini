import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { FilmsViewModelService } from './films-servicios.service';
import { DatePipe, JsonPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TypeValidator } from '../lib/my-core/directives/mis-validadores-directive'
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorMessagePipe } from '../lib/my-core';

@Component({
  selector: 'app-films',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => FilmsAddComponent),
  forwardRef(() => FilmsEditComponent),
  forwardRef(() => FilmsViewComponent),
  forwardRef(() => FilmsListComponent),
  ],
  })
  export class FilmsComponent implements OnInit, OnDestroy {
   constructor(protected vm: FilmsViewModelService) { }
   public get VM(): FilmsViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }

  @Component({
    selector: 'app-films-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class FilmsListComponent implements OnInit, OnDestroy {
    constructor(protected vm: FilmsViewModelService) { }
    public get VM(): FilmsViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   

   @Component({
    selector: 'app-films-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator,ErrorMessagePipe],
    })
    export class FilmsAddComponent implements OnInit {
    constructor(protected vm: FilmsViewModelService) { }
    public get VM(): FilmsViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    if (this.VM.Elemento) {}
    }
    }
    
    @Component({
      selector: 'app-films-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator,ErrorMessagePipe],
      })
      
      export class FilmsEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: FilmsViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): FilmsViewModelService { return this.vm; }
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
        selector: 'app-films-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
        imports: [DatePipe],
        })
        export class FilmsViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: FilmsViewModelService, protected router: Router) { }
        public get VM(): FilmsViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const FILMS_COMPONENTES = [
    FilmsComponent, FilmsListComponent, FilmsAddComponent,
    FilmsEditComponent, FilmsViewComponent,
   ];
   


   
  

   
  
