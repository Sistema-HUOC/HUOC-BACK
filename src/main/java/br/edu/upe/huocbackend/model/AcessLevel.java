package br.edu.upe.huocbackend.model;

import lombok.Getter;

@Getter
public enum AcessLevel {
    MEDICO("MEDICO"),ADMINISTRATOR("ADMINISTRATOR"),ENFERMAGEM("ENFERMAGEM"),PESQUISADOR("PESQUISADOR");
    public final String role;
    AcessLevel(String role){
        this.role = role;
    }
}
