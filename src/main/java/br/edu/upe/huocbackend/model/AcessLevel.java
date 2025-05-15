package br.edu.upe.huocbackend.model;

import lombok.Getter;

@Getter
public enum AcessLevel {
    MEDICO("MEDICO"),ADMINISTRADOR("ADMINISTRADOR"),ENFERMAGEM("ENFERMAGEM"),PESQUISADOR("PESQUISADOR");
    public final String role;
    AcessLevel(String role){
        this.role = role;
    }
}
