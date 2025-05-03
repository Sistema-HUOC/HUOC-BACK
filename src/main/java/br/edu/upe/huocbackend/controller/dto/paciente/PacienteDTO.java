package br.edu.upe.huocbackend.controller.dto.paciente;

import br.edu.upe.huocbackend.model.Endereco;
import br.edu.upe.huocbackend.model.Enfermagem;
import br.edu.upe.huocbackend.model.Exame;
import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PacienteDTO {

    public UUID id;

    @NotBlank
    public String nome;

    @NotBlank
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


}
