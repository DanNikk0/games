package com.Games.LojaGames.controller;

import com.Games.LojaGames.model.Desconto;
import com.Games.LojaGames.repository.DescontoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/descontos")
public class DescontoController {

    private final DescontoRepository descontoRepository;

    public DescontoController(DescontoRepository descontoRepository) {
        this.descontoRepository = descontoRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public List<Desconto> listar() {
        return descontoRepository.findAll();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE
    )
    @ResponseBody
    public Desconto criar(@RequestBody Desconto desconto) {

        desconto.setAtivo(true);

        return descontoRepository.save(desconto);
    }

    @PutMapping(value = "/id/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<Desconto> atualizar(@PathVariable Long id, @RequestBody Desconto dadosAtualizados) {
        return descontoRepository.findById(id).map(desconto -> {
            desconto.setCodigo(dadosAtualizados.getCodigo());
            desconto.setDescricao(dadosAtualizados.getDescricao());
            desconto.setPercentualDesconto(dadosAtualizados.getPercentualDesconto());
            desconto.setInicioEm(dadosAtualizados.getInicioEm());
            desconto.setFimEm(dadosAtualizados.getFimEm());
            return ResponseEntity.ok(descontoRepository.save(desconto));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/id/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return descontoRepository.findById(id).map(desconto -> {
            descontoRepository.delete(desconto);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/lista")
    public String listarPagina(Model model) {
        model.addAttribute("descontos", descontoRepository.findAll());
        return "descontos";
    }
}