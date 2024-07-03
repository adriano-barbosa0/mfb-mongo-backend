package com.api.mfb.service;

import com.api.mfb.dto.ListagemServicosDto;
import com.api.mfb.dto.ServicosDto;
import com.api.mfb.dto.ServicosSaveDto;
import com.api.mfb.dto.ServicosUpdateDto;
import com.api.mfb.model.Servicos;
import org.bson.types.ObjectId;

import java.util.List;

public interface ServicoService {

    ListagemServicosDto getServiceById(ObjectId id);
    List<ListagemServicosDto> findAll();
    Servicos createService(ServicosSaveDto service);
    void updateService(ServicosUpdateDto dto);
    void deleteService(ObjectId id);
}
