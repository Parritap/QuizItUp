import { Component } from '@angular/core';
import { DocenteService } from '../../services/http-services/docente.service';

@Component({
  selector: 'app-preambulo-creacion-examen',
  standalone: true,
  imports: [],
  templateUrl: './preambulo-creacion-examen.component.html',
  styleUrl: './preambulo-creacion-examen.component.css'
})
export class PreambuloCreacionExamenComponent {

  temas!:String[];

  constructor(
    private docenteService: DocenteService
  ) {
    this.temas = [];
  }

  OnInit() {

    this.docenteService.getTodoTemas(10).subscribe(
      (data) => {
        console.log(data.respuesta);
        this.temas = data.respuesta;
      },
      (error) => {
        console.log(error);
      }
    );


  }
}
