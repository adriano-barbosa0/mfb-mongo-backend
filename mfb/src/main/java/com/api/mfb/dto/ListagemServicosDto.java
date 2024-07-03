package com.api.mfb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListagemServicosDto {
    private ObjectId idServico;
    private String nomeServico;
    private String descricao;
    private List<String> fotos;
    private List<String> videos;
    private String depoimentos;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
}
