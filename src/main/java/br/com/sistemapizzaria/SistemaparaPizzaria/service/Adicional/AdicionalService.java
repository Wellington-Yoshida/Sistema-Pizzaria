package br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicinalPersonalizadoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicionalDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.DetalhePedidoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Service;

public interface AdicionalService extends Service<Adicional> {

    AdicinalPersonalizadoDto validaPersonalizacao(AdicionalDto adicionalDto);

    Adicional converteDtoParaAdicional(AdicinalPersonalizadoDto adicinalPersonalizadoDto, Pizza pizza);

    AdicionalDto converteAdicinalParaDto(Adicional adicional);
}
