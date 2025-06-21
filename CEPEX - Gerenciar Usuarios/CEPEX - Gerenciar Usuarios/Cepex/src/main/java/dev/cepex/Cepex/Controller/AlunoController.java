package dev.cepex.Cepex.Controller;

import dev.cepex.Cepex.Model.Aluno;
import dev.cepex.Cepex.Model.Usuario;
import dev.cepex.Cepex.service.AlunoService;
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
public class AlunoController {

   @Autowired
   private AlunoService alunoService;

   @Autowired
   private UsuarioService usuarioService;


   @GetMapping("/novo")
    public String formularioNovoAluno(Model model) {
       model.addAttribute("aluno", new Aluno());
       return "/gerencia-usuarios/novo";
   }

   @PostMapping("/salvar")
    public String salvarAluno(Aluno aluno, RedirectAttributes attributes) {
       alunoService.salvar(aluno);
       return "redirect:/gerencia-usuarios/novo";
   }


}
