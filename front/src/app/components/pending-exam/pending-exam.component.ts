import { Component, OnInit } from '@angular/core';
import { PendingExamItemComponent } from "../pending-exam-item/pending-exam-item.component";
import { Exam } from '../../dtos/Exam';
import { UserServiceService } from '../../services/ui-services/user-service.service';

@Component({
    selector: 'app-pending-exam',
    standalone: true,
    templateUrl: './pending-exam.component.html',
    styleUrl: './pending-exam.component.css',
    imports: [PendingExamItemComponent]
})
export class PendingExamComponent implements OnInit{

    exams?:Exam[];

    constructor(private userService: UserServiceService) {
        this.exams = [];
    }

    ngOnInit(): void {
        this.userService.getExams().then((data) => {
            data.forEach(element => {
                element.forEach(exam => {
                    this.exams?.push(exam);
                });
            });
        });
    }
}
