package com.api.mfb.service.impl;

import com.api.mfb.dto.ListagemServicosDto;
import com.api.mfb.dto.ServicosSaveDto;
import com.api.mfb.dto.ServicosUpdateDto;
import com.api.mfb.exception.ValidacaoException;
import com.api.mfb.model.Servicos;
import com.api.mfb.repository.ServicosRepository;
import com.api.mfb.service.ServicoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServicoServiceImpl implements ServicoService {

    private ServicosRepository repository;
    private  ModelMapper mapper;

    @Autowired
    public ServicoServiceImpl(ServicosRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public ListagemServicosDto getServiceById(ObjectId id) {
        try{
            boolean existe = repository.existsById(id);
            if(!existe){
                throw new ValidacaoException("Id não existe na base de dados.");
            }
        }catch (Exception e){
            String msg = e.getMessage();
            log.error(msg);
                throw new ValidacaoException("Problema ao buscar Id");
        }
            Servicos servicos = this.repository.findById(id).get();
            return this.mapper.map(servicos, ListagemServicosDto.class);
    }

    @Override
    public List<ListagemServicosDto> findAll() {
        List<Servicos> servicos;
        try {
            servicos = this.repository.findAll();

        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg);
            throw new ValidacaoException("Erro ao buscar serviços.");
        }
        return servicos.stream().map(serv -> this.mapper.map(serv, ListagemServicosDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Servicos createService(ServicosSaveDto servicosDto) {
        try{Servicos service = new Servicos();
        service.setNomeServico(servicosDto.getNomeServico());
        service.setDescricao(servicosDto.getDescricao());
        service.setFotos(servicosDto.getFotos());
        service.setVideos(servicosDto.getVideos());
        service.setDepoimentos(servicosDto.getDepoimentos());
        service.setDataInclusao(LocalDateTime.now());

        return repository.save(service);
    }catch (Exception e){
            String msg = e.getMessage();
            log.error(msg);
            throw new ValidacaoException("Ocorreu um erro ao salvar, tente novamente mais tarde!");
        }
    }

    @Override
    public void updateService(ServicosUpdateDto dto) {
        try {
            boolean existe = repository.existsById(dto.getIdServico());
            if(!existe){
                throw new ValidacaoException("Id informado não existe");
            }
            Servicos service = this.repository.findById(dto.getIdServico()).get();
            service.setNomeServico(dto.getNomeServico());
            service.setDescricao(dto.getDescricao());
            service.setFotos(dto.getFotos());
            service.setVideos(dto.getVideos());
            service.setDepoimentos(dto.getDepoimentos());
            service.setDataAlteracao(LocalDateTime.now());
            this.repository.save(service);
        }catch (Exception e){
            String msg = e.getMessage();
            log.error(msg);
            throw new ValidacaoException("Ocorreu um erro ao atualizar, tente novamente mais tarde!");
        }

    }

    @Override
    public void deleteService(ObjectId id) {
        try{
            boolean existe = this.repository.existsById(id);
            if(!existe){
                throw new ValidacaoException("Id informado não existe");
            }
            this.repository.deleteById(id);
        }catch (Exception e){
            String msg = e.getMessage();
            log.error(msg);
            throw new ValidacaoException("Ocorreu um erro ao excluir, tente novamente mais tarde!");
        }
    }
}
