package com.api.mfb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicosSaveDto {
    @NotNull(message = "O nome do Serviço não pode ser nulo!")
    private String nomeServico;
    private String descricao;
    private List<String> fotos;
    private List<String> videos;
    private String depoimentos;
    private LocalDateTime dataInclusao;
}
