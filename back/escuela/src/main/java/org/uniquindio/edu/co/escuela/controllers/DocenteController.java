package org.uniquindio.edu.co.escuela.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniquindio.edu.co.escuela.DTO.CursoDTO;
import org.uniquindio.edu.co.escuela.DTO.ExamenDTO;
import org.uniquindio.edu.co.escuela.DTO.MensajeDTO;
import org.uniquindio.edu.co.escuela.DTO.PreguntaBancoDTO;
import org.uniquindio.edu.co.escuela.services.interfaces.DocenteService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/docente")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DocenteController {

    private final DocenteService docenteService;

    @PostMapping("/listarBancoPreguntas")
    public ResponseEntity<MensajeDTO<List<PreguntaBancoDTO>>> listarBancoPreguntas(@RequestBody Long id_tema) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerBancoPreguntas(id_tema)));
    }


    @PostMapping("/crearRespuesta")
    public ResponseEntity<MensajeDTO<String>> crearRespuesta(@RequestBody String descripcion, Character esVerdadera, Long id_pregunta) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearRespuesta(descripcion, esVerdadera, id_pregunta)));
    }

    @PostMapping("/crearExamen")
    public ResponseEntity<MensajeDTO<String>> crearExamen(@RequestBody Integer tiempo_max, Integer numero_preguntas,
                                                          Integer porcentajeCurso, String nombre, Integer porcentaje_aprobatorio,
                                                          java.util.Date fecha_hora_inicio, Date fecha_hora_fin, Integer num_preguntas_aleatorias,
                                                          Integer id_tema, Integer id_docente, Integer id_grupo) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearExamen(tiempo_max,numero_preguntas,porcentajeCurso,
                nombre, porcentaje_aprobatorio, fecha_hora_inicio, fecha_hora_fin, num_preguntas_aleatorias, id_tema, id_docente, id_grupo)));
    }

    @PostMapping("/crearPregunta")
    public ResponseEntity<MensajeDTO<String>> crearPregunta(@RequestBody String enunciado, Character es_publica, String tipoPregunta,
                                                            Integer id_tema, Integer id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.crearPregunta(enunciado,es_publica,tipoPregunta,id_tema,id_docente)));
    }

    @PostMapping("/calificarExamen")
    public ResponseEntity<MensajeDTO<String>> calificarExamen(@RequestBody   Long id_presentacion_examen) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.calificarExamen(id_presentacion_examen)));
    }


    @PostMapping("/obtenerPreguntasDocente")
    public ResponseEntity<MensajeDTO<List<PreguntaBancoDTO>>> obtenerPreguntasDocente (@RequestBody  Long id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerPreguntasDocente(id_docente)));
    }

    @PostMapping("/obtenerExamenesDocente")
    public ResponseEntity<MensajeDTO<List<ExamenDTO>>> obtenerExamenesDocente (@RequestBody  Long id_docente) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerExamenesDocente(id_docente)));
    }

    @GetMapping("/nombre/{id}/{rol}")
    public ResponseEntity<MensajeDTO<String>> obtenerNombre(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerNombre(id, rol)));
    }
    @GetMapping("/cursos/{id}/{rol}")
    public ResponseEntity<MensajeDTO<List<CursoDTO>>> obtenerCursos(@PathVariable String id, @PathVariable String rol) {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "", docenteService.obtenerCursos(id, rol)));
    }
}
