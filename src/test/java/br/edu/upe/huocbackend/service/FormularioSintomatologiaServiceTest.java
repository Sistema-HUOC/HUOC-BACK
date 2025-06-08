package br.edu.upe.huocbackend.service;


import br.edu.upe.huocbackend.controller.dto.formSintomatologia.FormularioSintomatologiaDTO;
import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.model.categoriaSintoma.*;
import br.edu.upe.huocbackend.repository.IFormularioSintomatologiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FormularioSintomatologiaServiceTest {


    @Mock
    private IFormularioSintomatologiaRepository formSintomatologiaRepository;

    @InjectMocks
    private FormularioSintomatologiaService formSintomatologiaService;

    private FormularioSintomatologia formSintomatologia;


    @BeforeEach
            public void setUp() {
        Geral geral = new Geral();
        geral.setId(UUID.randomUUID());

        Respiratorio respiratorio = new Respiratorio();
        respiratorio.setId(UUID.randomUUID());

        Inflamatorio inflamatorio = new Inflamatorio();
        inflamatorio.setId(UUID.randomUUID());

        Cardiovascular cardiovascular = new Cardiovascular();
        cardiovascular.setId(UUID.randomUUID());

        Gastrointestinal gastrointestinal = new Gastrointestinal();
        gastrointestinal.setId(UUID.randomUUID());

        Neurologico neurologico = new Neurologico();
        neurologico.setId(UUID.randomUUID());

        Musculoesqueletico musculo = new Musculoesqueletico();
        musculo.setId(UUID.randomUUID());

        Hematologico hematologico = new Hematologico();
        hematologico.setId(UUID.randomUUID());

        Psiquiatrico psiquiatrico = new Psiquiatrico();
        psiquiatrico.setId(UUID.randomUUID());

        Paciente paciente = new Paciente();
        paciente.setId(UUID.randomUUID());
        paciente.setNome("João da Silva");


        formSintomatologia = new FormularioSintomatologia(
                geral,
                respiratorio,
                inflamatorio,
                cardiovascular,
                gastrointestinal,
                neurologico,
                musculo,
                hematologico,
                psiquiatrico,
                LocalDateTime.now(),
                123456,
                "Paciente com sintomas gripais e cansaço.",
                paciente
        );
    }


    @Test
    public void devePreencherFormSintomatologiaComSucesso(){

        formSintomatologiaService.preencherFormSintomatologia(new FormularioSintomatologiaDTO(formSintomatologia.getCatGeral(), formSintomatologia.getCatRespiratorio()
        ,formSintomatologia.getCatInflamatorio(), formSintomatologia.getCatCardiovascular(), formSintomatologia.getCatGastrointestinal(),formSintomatologia.getCatNeurologico(),
                formSintomatologia.getCatMusculoesqueletico(),formSintomatologia.getCatHematologico(),formSintomatologia.getCatPsiquiatrico()
        ,formSintomatologia.getData(),formSintomatologia.getNumProntuario(),formSintomatologia.getObservacoes(),formSintomatologia.getPaciente()));

        verify(formSintomatologiaRepository).save(any(FormularioSintomatologia.class));

    }
}
