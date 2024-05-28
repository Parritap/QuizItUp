import { Component } from '@angular/core';
import { PreambuloCreacionExamenComponent } from "../../components/preambulo-creacion-examen/preambulo-creacion-examen.component";
import { LeftBarComponent } from "../../components/left-bar/left-bar.component";
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";

@Component({
    selector: 'app-crear-examen',
    standalone: true,
    templateUrl: './crear-examen.component.html',
    styleUrl: './crear-examen.component.css',
    imports: [PreambuloCreacionExamenComponent, LeftBarComponent, NavBarComponent]
})
export class CrearExamenComponent {

}
