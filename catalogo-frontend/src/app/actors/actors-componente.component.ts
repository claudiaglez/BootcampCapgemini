import { Component, forwardRef, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { ActorsViewModelService } from './actors-servicios.service';
import { DatePipe, JsonPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TypeValidator } from '../lib/my-core/directives/mis-validadores-directive'
import { ActivatedRoute, ParamMap, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorMessagePipe } from '../lib/my-core';

@Component({
  selector: 'app-actors',
  templateUrl: './tmpl-anfitrion.component.html',
  styleUrls: ['./componente.component.css'],
  imports: [
  forwardRef(() => ActorsAddComponent),
  forwardRef(() => ActorsEditComponent),
  forwardRef(() => ActorsViewComponent),
  forwardRef(() => ActorsListComponent),
  ],
  })
  export class ActorsComponent implements OnInit, OnDestroy {
   constructor(protected vm: ActorsViewModelService) { }
   public get VM(): ActorsViewModelService { return this.vm; }
   ngOnInit(): void { this.vm.list(); }
  ngOnDestroy(): void { this.vm.clear(); }
  }

  @Component({
    selector: 'app-actors-list',
    templateUrl: './tmpl-list.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [RouterLink]
   })
   export class ActorsListComponent implements OnInit, OnDestroy {
    constructor(protected vm: ActorsViewModelService) { }
    public get VM(): ActorsViewModelService { return this.vm; }
    ngOnInit(): void { this.vm.list(); }
   ngOnDestroy(): void { this.vm.clear(); }
   }
   

   @Component({
    selector: 'app-actors-add',
    templateUrl: './tmpl-form.component.html',
    styleUrls: ['./componente.component.css'],
    imports: [FormsModule, TypeValidator,ErrorMessagePipe],
    })
    export class ActorsAddComponent implements OnInit {
    constructor(protected vm: ActorsViewModelService) { }
    public get VM(): ActorsViewModelService { return this.vm; }
    ngOnInit(): void {
    this.vm.add();
    }
    }
    
    @Component({
      selector: 'app-actors-edit',
      templateUrl: './tmpl-form.component.html',
      styleUrls: ['./componente.component.css'],
      imports: [FormsModule, TypeValidator,ErrorMessagePipe],
      })
      
      export class ActorsEditComponent implements OnInit, OnDestroy {
      private obs$?: Subscription;
      constructor(protected vm: ActorsViewModelService,
      protected route: ActivatedRoute, protected router: Router) { }
      public get VM(): ActorsViewModelService { return this.vm; }
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
        selector: 'app-actors-view',
        templateUrl: './tmpl-view.component.html',
        styleUrls: ['./componente.component.css'],
        imports: [DatePipe],
        })
        export class ActorsViewComponent implements OnChanges {
        @Input() id?: string;
        constructor(protected vm: ActorsViewModelService, protected router: Router) { }
        public get VM(): ActorsViewModelService { return this.vm; }
        ngOnChanges(changes: SimpleChanges): void {
        if (this.id) {
        this.vm.view(+this.id);
        } else {
        this.router.navigate(['/404.html']);
        }
        }
        }
   export const ACTORS_COMPONENTES = [
    ActorsComponent, ActorsListComponent, ActorsAddComponent,
    ActorsEditComponent, ActorsViewComponent,
   ];
   


   
  
