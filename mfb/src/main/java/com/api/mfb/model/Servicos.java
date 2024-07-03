package com.api.mfb.model;

import com.api.mfb.dto.ListagemServicosDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "servicos")
public class Servicos {
    @Id
    private ObjectId idServico;
    @NotNull(message = "O nome do Serviço não pode ser nulo!")
    private String nomeServico;
    private String descricao;
    private List<String> fotos;
    private List<String> videos;
    private String depoimentos;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;


    public void atualizaServico(ListagemServicosDto servicos) {
        this.nomeServico = servicos.getNomeServico();
        this.descricao = servicos.getDescricao();
        this.fotos = servicos.getFotos();
        this.videos = servicos.getVideos();
        this.depoimentos = servicos.getDepoimentos();
        this.dataInclusao = servicos.getDataInclusao();
        this.dataAlteracao = servicos.getDataAlteracao();
    }
}
