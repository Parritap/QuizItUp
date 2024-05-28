import { Injectable } from '@angular/core';
import { Course } from '../../dtos/estudiante/Course';
import { ExamDue } from '../../dtos/Exam';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  exam!: ExamDue;

  

  private course!: Course;
  constructor() { }


  setCourse(course: Course) {
    this.course = course;
  }

  getCourse() {
    return this.course;
  }

  setExam(exam: ExamDue) {
    this.exam = exam;
  }

  getExam() {
    return this.exam;
  }


}
