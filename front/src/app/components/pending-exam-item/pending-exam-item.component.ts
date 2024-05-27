import { Component, Input } from '@angular/core';
import { Exam } from '../../dtos/Exam';

@Component({
  selector: 'app-pending-exam-item',
  standalone: true,
  imports: [],
  templateUrl: './pending-exam-item.component.html',
  styleUrl: './pending-exam-item.component.css'
})
export class PendingExamItemComponent {

  @Input() exam?: Exam;
}
