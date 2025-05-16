package br.edu.upe.huocbackend.controller.dto.paciente;

import br.edu.upe.huocbackend.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "PacienteCreateDTO")
public class PacienteCreateDTO {
    @NotBlank
    public String nome;
    @NotBlank @CPF
    public String cpf;
    @NotBlank
    public LocalDate dataNasc;
    @NotBlank
    public String sexo;
    @NotBlank
    public String telefone;
    @NotNull
    public Endereco endereco;
    @NotNull
    public Boolean htvl1;
    @NotNull
    public Boolean htvl2;
    @NotNull
    public Integer numProntuario;

    public PacienteCreateDTO(String nome, String cpf, LocalDate dataNasc, String sexo, String telefone, Boolean htvl1, Boolean htvl2, Endereco endereco, Integer numProntuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.telefone = telefone;
        this.htvl1 = htvl1;
        this.htvl2 = htvl2;
        this.endereco = endereco;
        this.numProntuario = numProntuario;
    }
}
