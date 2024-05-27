import { Component, OnInit } from '@angular/core';
import { Exam } from '../../dtos/Exam';
import { UserServiceService } from '../../services/ui-services/user-service.service';
import { PendingExamItemComponent } from "../pending-exam-item/pending-exam-item.component";

@Component({
    selector: 'app-due-exams',
    standalone: true,
    templateUrl: './due-exams.component.html',
    styleUrl: './due-exams.component.css',
    imports: [PendingExamItemComponent]
})
export class DueExamsComponent implements OnInit {
  exams?:Exam[];

  constructor( private userServicesService: UserServiceService) {
    this.exams = [];
  }

  ngOnInit(): void {
    this.userServicesService.getExams().then((data) => {
      
      data.forEach(element => {

        element.forEach(exam => {
            this.exams?.push(exam);
        });

      }
      );
    });
  }
}
