import { Component, Input, ViewContainerRef } from '@angular/core';

@Component({
  selector: 'app-single-choice-question',
  standalone: true,
  imports: [],
  templateUrl: './single-choice-question.component.html',
  styleUrl: './single-choice-question.component.css',
})
export class SingleChoiceQuestionComponent {
  @Input('statement') statement!: string;
  @Input('choices') choices!: string[];
}
