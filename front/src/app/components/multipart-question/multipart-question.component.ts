import { Component, Input } from '@angular/core';
import { CardComponent } from '../card/card.component';
import { SingleChoiceQuestionComponent } from '../single-choice-question/single-choice-question.component';
import { MultipleChoiceQuestionComponent } from '../multiple-choice-question/multiple-choice-question.component';
type SimpleQuestion = {
  type: 'single-choice' | 'multiple-choice';
  statement: string;
  choices: string[];
};

@Component({
  selector: 'app-multipart-question',
  standalone: true,
  imports: [
    CardComponent,
    SingleChoiceQuestionComponent,
    MultipleChoiceQuestionComponent,
  ],
  templateUrl: './multipart-question.component.html',
  styleUrl: './multipart-question.component.css',
})
export class MultipartQuestionComponent {
  @Input('statement') statement!: string;
  @Input('subquestions') subquestions!: SimpleQuestion[];
}
