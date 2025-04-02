import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import { LoggerService } from '@my/core';
import { NotificationModalComponent } from './main';
import { FormulariosComponent } from './ejemplos/formularios/formularios.component';
import { HeaderComponent } from "./header/header.component";
import { AjaxWaitComponent } from './main/ajax-wait';


@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, NotificationModalComponent, FormulariosComponent, HeaderComponent, AjaxWaitComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'world';

  // constructor(out: LoggerService) {
  //   out.error('Es un error')
  //   out.warn('Es un warn')
  //   out.info('Es un info')
  //   out.log('Es un log')
  // }


}
