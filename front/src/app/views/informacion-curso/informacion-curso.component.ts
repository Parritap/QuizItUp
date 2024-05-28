import { Component } from '@angular/core';
import { InfoCursoComponent } from '../../components/info-curso/info-curso.component';
import { LeftBarComponent } from '../../components/left-bar/left-bar.component';
import { NavBarComponent } from '../../components/nav-bar/nav-bar.component';
import { UserServiceService } from '../../services/ui-services/user-service.service';
import { CourseInfo } from '../../dtos/CourseInfo';
import { ExamDue } from '../../dtos/Exam';
import { CourseService } from '../../services/general-service/course.service';

@Component({
  selector: 'app-informacion-curso',
  standalone: true,
  imports: [LeftBarComponent, NavBarComponent, InfoCursoComponent],
  templateUrl: './informacion-curso.component.html',
  styleUrl: './informacion-curso.component.css'
})
export class InformacionCursoComponent {

  constructor(private usrService: UserServiceService,
    private courseInfo: CourseService
  ) {

  }
  curso!: ExamDue;

  ngOnInit() {

    this.curso = this.courseInfo.getExam();
    
  }

}
