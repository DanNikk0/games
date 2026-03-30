package com.Games.LojaGames.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "descontos")
@JacksonXmlRootElement(localName = "Desconto")
public class Desconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @Column(length = 80, nullable = false, unique = true)
    @JacksonXmlProperty(localName = "codigo")
    private String codigo;

    @Column(length = 255)
    @JacksonXmlProperty(localName = "descricao")
    private String descricao;

    @Column(nullable = false, precision = 5, scale = 2)
    @JacksonXmlProperty(localName = "percentualDesconto")
    private BigDecimal percentualDesconto;

    @Column(nullable = false)
    @JacksonXmlProperty(localName = "inicioEm")
    private LocalDateTime inicioEm;

    @Column(nullable = false)
    @JacksonXmlProperty(localName = "fimEm")
    private LocalDateTime fimEm;

    @Column(nullable = false, updatable = false)
    @JacksonXmlProperty(localName = "criadoEm")
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    @JacksonXmlProperty(localName = "ativo")
    private boolean ativo = true;

    @PrePersist
    public void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(BigDecimal percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public LocalDateTime getInicioEm() { return inicioEm; }
    public void setInicioEm(LocalDateTime inicioEm) { this.inicioEm = inicioEm; }

    public LocalDateTime getFimEm() { return fimEm; }
    public void setFimEm(LocalDateTime fimEm) { this.fimEm = fimEm; }

    public LocalDateTime getCriadoEm() { return criadoEm; }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}