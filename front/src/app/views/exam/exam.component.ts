import { Component, Input, OnInit } from '@angular/core';
import { NavBarComponent } from '../../components/nav-bar/nav-bar.component';
import { CardComponent } from '../../components/card/card.component';
import { SingleChoiceQuestionComponent } from '../../components/single-choice-question/single-choice-question.component';
import { MultipleChoiceQuestionComponent } from '../../components/multiple-choice-question/multiple-choice-question.component';
import { MultipartQuestionComponent } from '../../components/multipart-question/multipart-question.component';

type SimpleQuestion = {
  type: 'single-choice' | 'multiple-choice';
  statement: string;
  choices: string[];
};

type MultipartQuestion = {
  type: 'multipart';
  statement: string;
  subquestions: SimpleQuestion[];
};
@Component({
  selector: 'app-exam',
  standalone: true,
  imports: [
    NavBarComponent,
    CardComponent,
    SingleChoiceQuestionComponent,
    MultipleChoiceQuestionComponent,
    MultipartQuestionComponent,
  ],
  templateUrl: './exam.component.html',
  styleUrl: './exam.component.css',
})
export class ExamComponent implements OnInit {
  @Input('exam_title') exam_title!: string;
  time: number = 3600;
  checked = [
    true,
    false,
    true,
    false,
    false,
    true,
    false,
    true,
    false,
    true,
    false,
    true,
    false,
  ];
  initialTime: number = this.time;
  timeString: string = '';
  gradesStyle!: string;

  questions: (SimpleQuestion | MultipartQuestion)[] = [
    {
      type: 'single-choice',
      statement: 'ip del DNS de Google',
      choices: ['8.8.8.8', '192.168.0.1', '0.0.0.0'],
    },
    {
      type: 'multiple-choice',
      statement: 'herramientas en Devops',
      choices: ['Docker', 'Jenkins', 'React Native', 'Postgresql'],
    },
    {
      type: 'multipart',
      statement: 'responda los siguientes conceptos',
      subquestions: [
        {
          type: 'multiple-choice',
          statement: 'hello world',
          choices: ['1', '2', '3', '4'],
        },
        {
          type: 'single-choice',
          statement: 'what is an ip address',
          choices: [
            'a house address',
            'a host address',
            'a route',
            'a ping message',
          ],
        },
      ],
    },
  ];

  ngOnInit(): void {
    if (!this.exam_title) {
      this.exam_title = 'ip address quizwez';
    }

    setInterval(() => {
      this.updateTime();
    }, 1000);
  }

  updateTime() {
    if (this.time <= 0) return;
    this.time = this.time - 1;
    let hours = Math.floor(this.time / 3600);
    let minutes = Math.floor((this.time - hours * 3600) / 60);
    let seconds = Math.floor(this.time - hours * 3600 - minutes * 60);
    this.timeString = `${hours.toString().padStart(2, '0')}:${minutes
      .toString()
      .padStart(2, '0')}:${seconds.toString().padStart(2, '0')} `;

    let grades = this.parseTimeToGrades(this.time);
    this.gradesStyle = `clip-path: path("${this.parseGradesToPath(
      grades,
      120,
      120
    )}");`;
  }

  parseTimeToGrades(time: number) {
    return (this.time / this.initialTime) * 360;
  }

  parseGradesToPath(grades: number, height: number, width: number) {
    let { x, y } = { x: width / 2, y: height / 2 };
    let path = 'M' + x + ',' + y + ' L0,0';
    if (grades >= 90) path += ' L0,' + height;
    else {
      path += ' L0,' + height * ((grades % 90) / 90);
      path += ' Z';
      console.log(path, '==', grades);
      return path;
    }
    if (grades >= 180) path += ' L' + width + ',120';
    else {
      path += ' L' + width * ((grades % 90) / 90) + ',' + height;
      path += ' Z';
      console.log(path, '==', grades);
      return path;
    }
    if (grades >= 270) path += ' L' + width + ',0';
    else {
      path += ' L' + width + ',' + height * (1 - (grades % 90) / 90);
      path += ' Z';
      console.log(path, '==', grades);
      return path;
    }
    path += ' L' + width * (1 - (grades % 90) / 90) + ',0';
    path += ' Z';
    console.log(path, '==', grades);
    return path;
  }
}
