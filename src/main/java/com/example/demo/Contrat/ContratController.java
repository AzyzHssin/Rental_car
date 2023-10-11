package com.example.demo.Contrat;

import com.example.demo.Client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ContratController {
    private ContratService contratService;
    @Autowired

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }
    /////////////////////////////////////////////////////:
    @GetMapping
    public List<Contrat> getAllContrat() {
        return contratService.getAllContrat();
    }
    @GetMapping("/{id}")
    public Optional<Contrat> getContratById(@PathVariable Long id) {
        return contratService.getContratById(id);
    }

    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {

        System.out.println("update client is made succefully");
        return contratService.createContrat(contrat);
    }

    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable Long id, @RequestBody Contrat updatedContrat) {
        return contratService.updateContrat(id, updatedContrat);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
    }
}

