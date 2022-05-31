package com.shrek.olimpiadas.controlador;

import com.shrek.olimpiadas.dto.DisciplinaDTO;
import com.shrek.olimpiadas.servicio.SvcDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalTime;

@Controller
public class CtrDisciplina {
    @Autowired
    private SvcDisciplina svc;

    @GetMapping("/disciplinas")
    public String mostrarFormNueva(Model model){
        model.addAttribute("disciplinas", svc.mostrarDisciplinas());
        return "ver_disciplinas";
    }

    @RequestMapping("/disciplina/nueva")
    public String mostrarFormaNueva(Model model) {
        DisciplinaDTO disciplina = new DisciplinaDTO();
        model.addAttribute("tituloPagina", "Registrar disciplina");
        model.addAttribute("disciplina", disciplina);
        return "registro_disciplina";
    }

    @PostMapping(value ="/disciplinas/guardar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String agregarDisciplina(DisciplinaDTO disciplina,
                                    @RequestParam(required = false ,name = "file") MultipartFile multipartFile,
                                    RedirectAttributes ra,Model model) throws IOException {
        Integer id = disciplina.getIddisciplina();
        String respuesta = "Esta disciplina ya se encuentra registrada.";
        model.addAttribute("tituloPagina", "Disciplina");

        if (!svc.registrada(disciplina.getNombre())) {
            //Guardar imagen
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String ima = disciplina.imagen();
            if (fileName.length() != 0) {
                fileName = LocalTime.now() + fileName;

                disciplina.setImagen(fileName);
                String uploadDir = "./disciplinas-imagenes/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath))
                    Files.createDirectories(uploadPath);

                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    throw new IOException("No se pudo guardar la imagen " + fileName);
                }
            }

            // Guardar nuevo
            if (id == null) {
                if (fileName.length() == 0)
                    disciplina.setImagen("default.jpg");

                respuesta = svc.agregarDisciplina(disciplina);
                if (respuesta == null) {
                    ra.addFlashAttribute("mensaje", "La disciplina se agregó con éxito.");
                    return "redirect:/disciplinas";
                }
            } else {
                // Actualizar
                if (fileName.length() != 0 && ima.length() != 0) {
                    ima = ima.substring(22);
                    if (!ima.equals("/disciplinas-imagenes/default.jpg")) {
                        Path fileToDeletePath = Paths.get("." + ima);
                        if (Files.exists(fileToDeletePath))
                            Files.delete(fileToDeletePath);
                    }
                }
                respuesta = svc.actualizarDisciplina(disciplina);
                if (respuesta == null) {
                    ra.addFlashAttribute("mensaje", "La disciplina se actualizó con éxito.");
                    return "redirect:/disciplinas";
                }
                model.addAttribute("tituloPagina", "Editar disciplina");
            }
        }

        // Si hubo un error
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("mensaje", respuesta);
        return "registro_disciplina";
    }

    @GetMapping("/disciplina/editar/{id}")
    public String actualizarDisciplina(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
        DisciplinaDTO disciplina = svc.mostrarDisciplina(id);
        if(disciplina == null) {
            ra.addFlashAttribute("mensaje", "No se encontró la disciplina.");
            return "redirect:/disciplinas";
        }
        model.addAttribute("disciplina", disciplina);
        model.addAttribute("tituloPagina", "Editar disciplina");
        return "registro_disciplina";
    }


    @GetMapping("/disciplinas/eliminar/{id}")
    public String eliminarDisciplina(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) throws IOException {
        DisciplinaDTO disciplina = svc.mostrarDisciplina(id);
        if(disciplina == null) {
            ra.addFlashAttribute("mensaje", "No se encontró la disciplina");
            return "redirect:/disciplinas";
        }
        String ima = disciplina.imagen();
        String res = svc.eliminarDisciplina(id);
        String m = (res == null)? "La disciplina se eliminó con éxito.":res;
        if (res == null){
            if (!ima.equals("/disciplinas-imagenes/default.jpg")) {
                Path fileToDeletePath = Paths.get("./" + ima);
                if(Files.exists(fileToDeletePath))
                    Files.delete(fileToDeletePath);
            }
        }
        ra.addFlashAttribute("mensaje", m);
        return "redirect:/disciplinas";
    }

}
