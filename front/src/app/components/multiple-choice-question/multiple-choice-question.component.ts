import { Component, Input, QueryList, ViewChildren } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PreguntaMultipleComponent } from '../pregunta-multiple/pregunta-multiple.component';
import { OpcionRespuesta } from '../../dtos/OpcionRespuesta';

@Component({
  selector: 'app-multiple-choice-question',
  standalone: true,
  templateUrl: './multiple-choice-question.component.html',
  styleUrl: './multiple-choice-question.component.css',
  imports: [FormsModule, PreguntaMultipleComponent],
})
export class MultipleChoiceQuestionComponent {
  @Input('statement') statement!: string;
  @Input('choices') choices!: string[];
  pregunta!: string;
  constructor() {}

  ngOnInit(): void {}
}
