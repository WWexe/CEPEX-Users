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

@RestController
@RequestMapping("/gerencia-usuarios")
public class GerenciaController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping("/gerencia-usuarios/delete")
<<<<<<< HEAD
     public String deletar() {
=======
    public String deletar() {
>>>>>>> d4b07360165872038e325b931328319a0daf423d
        return "gerencia-usuarios/delete";
    }
}

// get mapping geral
// get byid(passar parametro id)
// post mapping(para cadastro)
// DeleteMapping
//PutMapping(dar update na lista)



//Rest controller
