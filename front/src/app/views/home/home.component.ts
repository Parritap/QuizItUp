import { Component, Inject, OnInit } from '@angular/core';
import { LeftBarComponent } from "../../components/left-bar/left-bar.component";
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";
import { Course } from '../../dtos/Course';
import { CourseComponent } from "../../components/course/course.component";
import { UserServiceService } from '../../services/ui-services/user-service.service';
import { PendingExamComponent } from "../../components/pending-exam/pending-exam.component";
import { EstudianteService } from '../../services/http-services/estudiante.service';
import { UserActivoService } from '../../services/user-activo/user-activo.service';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  imports: [LeftBarComponent, NavBarComponent, CourseComponent, PendingExamComponent]
})
export class HomeComponent implements OnInit {

  courses?: Course[];
  nombre: string;

  constructor(private estudianteService: EstudianteService,
    private userActivo: UserActivoService
  ) {
    this.courses = [];
    this.nombre = '';
  }

  ngOnInit(): void {



    this.loadName();
    this.loadCourses();




  }

  loadCourses() {

    this.estudianteService.getCourses(this.userActivo.getId(), this.userActivo.getRol()).subscribe(
      (data) => {
        if (data.error == false) {
          data.respuesta.forEach((element: Course) => {
            this.courses?.push(element);
          });
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }


  loadName() {
    this.estudianteService.getName(this.userActivo.getId(), this.userActivo.getRol()).subscribe(

      (data) => {

        if (data.error == false) this.nombre = data.mensaje;

      },
      (error) => {
        console.log(error);
      }
    );
  }


}
