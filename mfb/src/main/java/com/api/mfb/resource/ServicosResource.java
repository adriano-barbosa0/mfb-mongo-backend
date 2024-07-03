package com.api.mfb.resource;

import com.api.mfb.dto.ListagemServicosDto;
import com.api.mfb.dto.ServicosSaveDto;
import com.api.mfb.dto.ServicosUpdateDto;
import com.api.mfb.model.Servicos;
import com.api.mfb.service.impl.ServicoServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServicosResource {
    private final ServicoServiceImpl service;

    public ServicosResource(ServicoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Servicos> save (@Valid @RequestBody ServicosSaveDto servicosDto,
                                          UriComponentsBuilder uriComponentsBuilder){
        Servicos newService = this.service.createService(servicosDto);
        URI uri = uriComponentsBuilder.path("/service/{id}").buildAndExpand(newService.getIdServico()).toUri();
        return ResponseEntity.created(uri).body(newService);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ListagemServicosDto>> find (){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ListagemServicosDto> findId(@PathVariable ObjectId id){
        ListagemServicosDto dto = this.service.getServiceById(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@Valid @RequestBody ServicosUpdateDto dto){
        this.service.updateService(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id")ObjectId id){
        this.service.deleteService(id);
    }

}
