package br.com.HUOC_BACK.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Getter
@Setter
@Audited
@NoArgsConstructor
public class Pesquisador extends User {

    @ManyToOne
    @JoinColumn(name = "idInstituicao")
    private Instituicao instituicao;

    @ManyToMany
    @JoinTable(
            name = "pesquisador_area_atuacao",
            joinColumns = @JoinColumn(name = "pesquisador_id"),
            inverseJoinColumns = @JoinColumn(name = "area_atuacao_id")
    )
    private List<AreaAtuacao> areasAtuacao;
}
