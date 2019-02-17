package br.com.sistemapizzaria.SistemaparaPizzaria.controller;

import br.com.sistemapizzaria.SistemaparaPizzaria.dto.DetalhePedidoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional.AdicionalService;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/pizzaria")
public class DetalhesPedidoController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private AdicionalService adicionalService;

    @GetMapping(value = "/detalharPedido/{idPizza}")
    public ResponseEntity<?> detalhaPedido(@Valid @PathVariable Long idPizza) {
        DetalhePedidoDto detalhePedidoDto = new DetalhePedidoDto();
        Pizza pizza = new Pizza();
        try {
            pizza = pizzaService.findBy(idPizza);
            convertePizzaParaDetelhesPedidoDto(pizza, detalhePedidoDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi localizado pedido com id: " + idPizza + "," + HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detalhePedidoDto, HttpStatus.OK);
    }

    private void convertePizzaParaDetelhesPedidoDto(Pizza pizza, DetalhePedidoDto detalhePedidoDto) {
        detalhePedidoDto.setTamanho(pizza.getTamanho());
        detalhePedidoDto.setSabor(pizza.getSabor());
        detalhePedidoDto.setValorTotal(pizza.getValorTotal());
        detalhePedidoDto.setTempoPrepado(pizza.getTempoPreparo());
        String adcionais = "";
        pizza.getAdicionalList().forEach(a -> {
            detalhePedidoDto.addPersonalizacao(a.getPersonalizacao());
        });
    }
}
