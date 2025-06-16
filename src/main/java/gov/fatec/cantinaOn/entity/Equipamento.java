package gov.fatec.cantinaOn.entity;

import gov.fatec.cantinaOn.utils.enumStatus.StatusEquipamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String tipo;
    private String localizacao;
    private StatusEquipamento status;

    @Column(name = "data_instalacao")
    private LocalDateTime dataInstalacao;

    @Column(name = "ultima_manutencao")
    private LocalDateTime ultimaManutencao;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrdemServico> ordensServico;
}
