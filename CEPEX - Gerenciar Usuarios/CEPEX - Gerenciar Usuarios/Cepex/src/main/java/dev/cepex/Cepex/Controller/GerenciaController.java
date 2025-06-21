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
import java.util.List;

@RestController
@RequestMapping("/gerencia-usuarios")
public class GerenciaController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id:\\d+}")
    public String detalharUsuario(@PathVariable long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
    }

    @PostMapping("/salvar")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }
    @GetMapping("/editar/{id:\\id+}")

    @GetMapping("/")
    @DeleteMapping("/gerencia-usuarios/delete")
    public String deletar() {
        return "gerencia-usuarios/delete";
    }

}

// get mapping geral
// get byid(passar parametro id)
// post mapping(para cadastro)
// DeleteMapping
//PutMapping(dar update na lista)
//Rest controller
