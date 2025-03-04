package gov.fatec.manumanager.entity;

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

    private String status;
    private String prioridade;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Atribuicao> atribuicoes;
}
