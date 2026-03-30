package com.Games.LojaGames.jobs;

import com.Games.LojaGames.model.Desconto;
import com.Games.LojaGames.repository.DescontoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DescontoJob {

    private final DescontoRepository descontoRepository;

    public DescontoJob(DescontoRepository descontoRepository) {
        this.descontoRepository = descontoRepository;
    }

    @Scheduled(fixedRate = 3600000)
    @Async
    public void desativarDescontosExpirados() {

        System.out.println("Verificando descontos expirados...");

        try {
            List<Desconto> descontosExpirados =
                    descontoRepository.findAllByFimEmBeforeAndAtivoTrue(LocalDateTime.now());
                    
            for (Desconto desconto : descontosExpirados) {
                desconto.setAtivo(false);
                descontoRepository.save(desconto);

                System.out.println("Desconto desativado: " + desconto.getCodigo());
            }

            System.out.println("Total desativados: " + descontosExpirados.size());

        } catch (Exception e) {
            System.out.println("Erro ao processar descontos: " + e.getMessage());
        }
    }
}