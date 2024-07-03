package com.api.mfb.dto;

import com.api.mfb.model.Servicos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicosDto {
    @NotNull(message = "O nome do Serviço não pode ser nulo!")
    private String nomeServico;
    private String descricao;
    private List<String> fotos;
    private List<String> videos;
    private String depoimentos;


}
