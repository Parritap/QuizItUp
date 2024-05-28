import { Component, Input } from '@angular/core';
import { ExamDue } from '../../dtos/Exam';
import { Router } from '@angular/router';
import { CourseService } from '../../services/general-service/course.service';

@Component({
  selector: 'app-pending-exam-item',
  standalone: true,
  imports: [],
  templateUrl: './pending-exam-item.component.html',
  styleUrl: './pending-exam-item.component.css'
})
export class PendingExamItemComponent {


  @Input() exam!: ExamDue;

  constructor(private router: Router,
    private courseService: CourseService
  ) {

  }

  presentarExamen() {
    
    this.router.navigate(['/info-curso']);
    this.courseService.setExam(this.exam);


  }
}
