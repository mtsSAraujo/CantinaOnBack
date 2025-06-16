package gov.fatec.cantinaOn.entity;

import gov.fatec.cantinaOn.utils.enumStatus.PrioridadeOS;
import gov.fatec.cantinaOn.utils.enumStatus.StatusOrdemDeServico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;

    private String descricaoProblema;

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    private StatusOrdemDeServico status;
    private PrioridadeOS prioridade;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Atribuicao> atribuicoes;
}
