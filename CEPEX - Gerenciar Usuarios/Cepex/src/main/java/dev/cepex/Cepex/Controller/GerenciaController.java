package dev.cepex.Cepex.Controller;

import dev.cepex.Cepex.Model.Professor;
import dev.cepex.Cepex.Model.Usuario;
import dev.cepex.Cepex.service.ProfessorService;
import dev.cepex.Cepex.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/gerencia-usuarios")
public class GerenciaController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/gerencia-usuarios/delete")
     public String deletar() {
        return "gerencia-usuarios/delete";
    }
}
