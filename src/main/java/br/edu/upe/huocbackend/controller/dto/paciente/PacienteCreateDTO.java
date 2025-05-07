package br.edu.upe.huocbackend.controller.dto.paciente;

import br.edu.upe.huocbackend.model.Endereco;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.Exame;
import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteCreateDTO {

    public UUID id;

    @NotBlank
    public String nome;

    @NotBlank
    @CPF
    public String cpf;

    @NotBlank
    public Date dataNasc;

    @NotBlank
    public String sexo;

    @NotBlank
    public String numero;

    @NotBlank
    public String htvl1;

    @NotBlank
    public String htvl2;

    @NotBlank
    public Integer numProntuario;

    public Enfermagem enfermagem;

    public Endereco endereco;

    public List<Exame> exames;

    public List<FormularioSintomatologia> formularioSintomatologia;

    public PacienteCreateDTO(String nome, String cpf, Date dataNasc, String sexo, String numero, String htvl1, String htvl2, Integer numProntuario,
                             Enfermagem enfermagem, Endereco endereco, List<Exame> exames, List<FormularioSintomatologia> formularioSintomatologia) {
        this(null, nome, cpf, dataNasc, sexo, numero, htvl1, htvl2, numProntuario, enfermagem, endereco, exames, formularioSintomatologia);
    }
}
