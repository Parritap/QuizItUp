import { Component, Inject, Input } from '@angular/core';
import { Course } from '../../dtos/Course';
import { CommonModule, NgOptimizedImage } from '@angular/common';

@Component({
  selector: 'app-course',
  standalone: true,
  imports: [NgOptimizedImage, CommonModule],
  templateUrl: './course.component.html',
  styleUrl: './course.component.css'
})
export class CourseComponent {

  @Input() course?: Course;
}
