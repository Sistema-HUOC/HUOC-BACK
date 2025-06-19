package br.edu.upe.huocbackend.controller.dto.paciente;

import br.edu.upe.huocbackend.model.FormularioSintomatologia;
import br.edu.upe.huocbackend.model.Paciente;
import br.edu.upe.huocbackend.model.categoriaSintoma.*;

public class PacienteMapper {

    public static PacienteResponse toResponse(Paciente paciente) {
        PacienteResponse dto = new PacienteResponse();

        dto.nome = paciente.getNome();
        dto.cpf = paciente.getCpf();
        dto.dataNasc = paciente.getDataNasc();
        dto.sexo = paciente.getSexo();
        dto.telefone = paciente.getTelefone();
        dto.htvl1 = paciente.getHtvl1();
        dto.htvl2 = paciente.getHtvl2();
        dto.numProntuario = paciente.getNumProntuario();

        if (paciente.getEnfermagem() != null) {
            PacienteResponse.Enfermagem enf = new PacienteResponse.Enfermagem();
            enf.nome = paciente.getEnfermagem().getNome();
            enf.coren = paciente.getEnfermagem().getCoren();
            dto.enfermagem = enf;
        }

        if (paciente.getEndereco() != null) {
            var end = new PacienteResponse.Endereco();
            end.rua = paciente.getEndereco().getRua();
            end.numero = paciente.getEndereco().getNumero();
            end.bairro = paciente.getEndereco().getBairro();
            end.cidade = paciente.getEndereco().getCidade();
            end.estado = paciente.getEndereco().getEstado();
            end.cep = paciente.getEndereco().getCep();
            end.complemento = paciente.getEndereco().getComplemento();
            dto.endereco = end;
        }

        if (paciente.getExames() != null) {
            dto.exames = paciente.getExames().stream().map(exame -> {
                var ex = new PacienteResponse.Exame();
                ex.data = exame.getData();
                ex.anexo = exame.getAnexo();
                ex.tipoExame = exame.getTipoExame();
                return ex;
            }).toList();
        }

        if (paciente.getFormularioSintomatologia() != null) {
            dto.formularioSintomatologia = paciente.getFormularioSintomatologia().stream()
                    .map(PacienteMapper::fromEntity)
                    .toList();
        }

        return dto;
    }
    public static PacienteResponse.FormularioSintomatologiaDTO fromEntity(FormularioSintomatologia entity) {
        PacienteResponse.FormularioSintomatologiaDTO dto = new PacienteResponse.FormularioSintomatologiaDTO();

        dto.data = entity.getData();
        dto.numProntuario = entity.getNumProntuario();
        dto.observacoes = entity.getObservacoes();

        Geral geral = entity.getCatGeral();
        if (geral != null) {
            PacienteResponse.FormularioSintomatologiaDTO.GeralDTO g = new PacienteResponse.FormularioSintomatologiaDTO.GeralDTO();
            g.febre = geral.isFebre();
            g.temperatura = geral.getTemperatura();
            g.cansaço = geral.isCansaço();
            g.sudorese = geral.isSudorese();
            g.perdaPeso = geral.isPerdaPeso();
            g.edema = geral.isEdema();
            g.observacao = geral.getObservacao();
            dto.catGeral = g;
        }

        Respiratorio r = entity.getCatRespiratorio();
        if (r != null) {
            PacienteResponse.FormularioSintomatologiaDTO.RespiratorioDTO rr = new PacienteResponse.FormularioSintomatologiaDTO.RespiratorioDTO();
            rr.tosse = r.isTosse();
            rr.faltadeAr = r.isFaltadeAr();
            rr.doraoRespirar = r.isDoraoRespirar();
            rr.espirro = r.isEspirro();
            rr.coriza = r.isCoriza();
            rr.observacao = r.getObservacao();
            dto.catRespiratorio = rr;
        }

        Inflamatorio i = entity.getCatInflamatorio();
        if (i != null) {
            PacienteResponse.FormularioSintomatologiaDTO.InflamatorioDTO inf = new PacienteResponse.FormularioSintomatologiaDTO.InflamatorioDTO();
            inf.dordeGarganta = i.isDordeGarganta();
            inf.linfonodosInchados = i.getLinfonodosInchados();
            inf.erupcaoCutanea = i.isErupcaoCutanea();
            inf.ulceraBocal = i.isUlceraBocal();
            inf.observacao = i.getObservacao();
            dto.catInflamatorio = inf;
        }

        Cardiovascular c = entity.getCatCardiovascular();
        if (c != null) {
            PacienteResponse.FormularioSintomatologiaDTO.CardiovascularDTO cv = new PacienteResponse.FormularioSintomatologiaDTO.CardiovascularDTO();
            cv.palpitações = c.isPalpitações();
            cv.dorToraxica = c.getDorToraxica();
            cv.tontura = c.isTontura();
            cv.pressaoAlta = c.isPressaoAlta();
            cv.extremidadesFrias = c.isExtremidadesFrias();
            cv.observacao = c.getObservacao();
            dto.catCardiovascular = cv;
        }

        Gastrointestinal g = entity.getCatGastrointestinal();
        if (g != null) {
            PacienteResponse.FormularioSintomatologiaDTO.GastrointestinalDTO gi = new PacienteResponse.FormularioSintomatologiaDTO.GastrointestinalDTO();
            gi.nausea = g.isNausea();
            gi.diarreia = g.getDiarreia();
            gi.prisaodeVentre = g.isPrisaodeVentre();
            gi.dorAbdominal = g.isDorAbdominal();
            gi.perdadeApetite = g.isPerdadeApetite();
            gi.constipação = g.isConstipação();
            gi.observacao = g.getObservacao();
            dto.catGastrointestinal = gi;
        }

        Neurologico n = entity.getCatNeurologico();
        if (n != null) {
            PacienteResponse.FormularioSintomatologiaDTO.NeurologicoDTO neu = new PacienteResponse.FormularioSintomatologiaDTO.NeurologicoDTO();
            neu.dordeCabeça = n.isDordeCabeça();
            neu.confusaoMental = n.getConfusaoMental();
            neu.convulsoes = n.isConvulsoes();
            neu.dorNeurotipica = n.isDorNeurotipica();
            neu.parestesia = n.isParestesia();
            neu.paresia = n.isParesia();
            neu.plegia = n.isPlegia();
            neu.observacao = n.getObservacao();
            dto.catNeurologico = neu;
        }

        Musculoesqueletico m = entity.getCatMusculoesqueletico();
        if (m != null) {
            PacienteResponse.FormularioSintomatologiaDTO.MusculoesqueleticoDTO musc = new PacienteResponse.FormularioSintomatologiaDTO.MusculoesqueleticoDTO();
            musc.dorArticular = m.isDorArticular();
            musc.rigidezMuscular = m.getRigidezMuscular();
            musc.fraquezaMuscular = m.isFraquezaMuscular();
            musc.articulacaoInchada = m.isArticulacaoInchada();
            musc.observacao = m.getObservacao();
            dto.catMusculoesqueletico = musc;
        }

        Hematologico h = entity.getCatHematologico();
        if (h != null) {
            PacienteResponse.FormularioSintomatologiaDTO.HematologicoDTO hem = new PacienteResponse.FormularioSintomatologiaDTO.HematologicoDTO();
            hem.sangramentoAnormal = h.isSangramentoAnormal();
            hem.hematomasFaceis = h.getHematomasFaceis();
            hem.palides = h.isPalides();
            hem.observacao = h.getObservacao();
            dto.catHematologico = hem;
        }

        Psiquiatrico p = entity.getCatPsiquiatrico();
        if (p != null) {
            PacienteResponse.FormularioSintomatologiaDTO.PsiquiatricoDTO psi = new PacienteResponse.FormularioSintomatologiaDTO.PsiquiatricoDTO();
            psi.ansiedade = p.isAnsiedade();
            psi.depressao = p.getDepressao();
            psi.alucinacao = p.isAlucinacao();
            psi.insonia = p.isInsonia();
            psi.observacao = p.getObservacao();
            dto.catPsiquiatrico = psi;
        }

        return dto;
    }

}

