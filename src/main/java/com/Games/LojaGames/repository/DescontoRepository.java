package com.Games.LojaGames.repository;

import com.Games.LojaGames.model.Desconto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DescontoRepository extends JpaRepository<Desconto, Long> {

    List<Desconto> findAllByFimEmBeforeAndAtivoTrue(LocalDateTime agora);

}