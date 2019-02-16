package br.com.sistemapizzaria.SistemaparaPizzaria.service.Adicional;

import br.com.sistemapizzaria.SistemaparaPizzaria.Repository.Adicional.AdicionalRepository;
import br.com.sistemapizzaria.SistemaparaPizzaria.Repository.Pizza.PizzaRepository;
import br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicinalPersonalizadoDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.dto.Adicional.AdicionalDto;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Adicional;
import br.com.sistemapizzaria.SistemaparaPizzaria.model.Pizza;
import br.com.sistemapizzaria.SistemaparaPizzaria.service.Pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.util.ObjectUtils;
import sun.util.calendar.CalendarDate;
import sun.util.calendar.CalendarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao.BANCON_EXTRA;
import static br.com.sistemapizzaria.SistemaparaPizzaria.arquivosuteis.Personalizacao.BORDA_RECHEADA;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Long.parseLong;

@Service
@Transactional
public class AdicionalServiceImpl implements AdicionalService {

    Predicate<String> validaPizzaIDIsNotNull = p -> !ObjectUtils.isEmpty(p);
    Predicate<List<String>> validaAdicionaisIsNotNull = p -> !ObjectUtils.isEmpty(p);

    @Autowired
    private AdicionalRepository adicionalRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @Override
    public void salvar(Adicional objeto) {
        adicionalRepository.salvar(objeto);
    }

    @Override
    public void atualizar(Adicional objeto) {
        adicionalRepository.atualizar(objeto);
    }

    @Override
    public void deletar(Long id) {
        adicionalRepository.deletar(id);
    }

    @Override
    public List<Adicional> findAll() {
        return adicionalRepository.findAll();
    }

    @Override
    public Adicional findBy(Long id) {
        return adicionalRepository.findBy(id);
    }

    private boolean verificaAdicionais(AdicionalDto adicionalDto) {
        return (validaPizzaIDIsNotNull.test(adicionalDto.getPizza_id()) && validaAdicionaisIsNotNull.test(adicionalDto.getPersonalizações())) ? TRUE : FALSE;
    }

    @Override
    public AdicinalPersonalizadoDto validaPernalizacao(AdicionalDto adicionalDto) {
        AdicinalPersonalizadoDto adicinalPersonalizadoDto = new AdicinalPersonalizadoDto();
        if (verificaAdicionais(adicionalDto)) {
            adicinalPersonalizadoDto.setPizza_id(adicionalDto.getPizza_id());
            addPersonalizacoes(adicionalDto.getPersonalizações(), adicinalPersonalizadoDto);
        }
        return adicinalPersonalizadoDto;
    }

    @Override
    public Adicional converteDtoParaAdicional(AdicinalPersonalizadoDto adicinalPersonalizadoDto , Pizza pizza) {
        final Adicional adicional = new Adicional();
        atualizaPizza(pizza, adicinalPersonalizadoDto);
        adicional.setPizza(pizza);
        adicional.setPersonalizacao("");
        adicinalPersonalizadoDto.getPersonalizações().forEach(p -> {
            adicional.setPersonalizacao(adicional.getPersonalizacao().concat(p).concat(","));
        });
        return adicional;
    }

    private void atualizaPizza(Pizza pizza, AdicinalPersonalizadoDto adicinalPersonalizadoDto) {
        Double valorPizza = adicinalPersonalizadoDto.getValorAdicional() + pizza.getValorPizza();
        pizza.setValorTotal(valorPizza);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date tempo = sdf.parse(adicinalPersonalizadoDto.getTempoAdicional());
            Date tempoPizza = sdf.parse(pizza.getTempoPreparo());
            int tempoTotal = tempo.getMinutes() + tempoPizza.getMinutes();
            pizza.setTempoPreparo("00:" + tempoTotal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addPersonalizacoes(List<String> personalizações, AdicinalPersonalizadoDto adicinalPersonalizadoDto) {
        personalizações.forEach(p -> {
            if (p.equals(BANCON_EXTRA)) {
                adicinalPersonalizadoDto.setValorAdicional(5.00);
            } else if (p.equals(BORDA_RECHEADA)) {
                Double valorAdicional = adicinalPersonalizadoDto.getValorAdicional();
                valorAdicional =  valorAdicional + 5.00;
                adicinalPersonalizadoDto.setValorAdicional(valorAdicional);
                adicinalPersonalizadoDto.setTempoAdicional("00:05");
            }
            adicinalPersonalizadoDto.addPersonalizacao(p);
        });
    }
}
