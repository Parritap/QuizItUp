import { Component, Input } from '@angular/core';
import { ExamDue } from '../../dtos/Exam';
import { CourseService } from '../../services/general-service/course.service';

interface CourseData {
  [key: string]: any; 
}

@Component({
  selector: 'app-info-curso',
  standalone: true,
  imports: [],
  templateUrl: './info-curso.component.html',
  styleUrl: './info-curso.component.css'
})
export class InfoCursoComponent {
  
  exam!: ExamDue;

  constructor(
    private courseService: CourseService
  ){

  }

  ngOnInit() {
    this.exam = this.courseService.getExam();
  }
}
