package br.com.luizmrd.controller;

import br.com.luizmrd.environment.InstanceInformationService;
import br.com.luizmrd.model.Exchange;

import br.com.luizmrd.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;



@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    @Autowired
    InstanceInformationService informationService;

    @Autowired
    ExchangeRepository repository;

    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {

        Exchange exchange = repository.findByFromAndTo(from, to);

        if(exchange == null) throw new RuntimeException("Currency Unsupported");

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT "+ informationService.retrieveServerPort());

        return  exchange;
    }
//    @GetMapping(value = "/{amonut}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Exchange getExchange(
//            @PathVariable("amonut") BigDecimal amonut,
//            @PathVariable("from") String from,
//            @PathVariable("to") String to) {
//        return new Exchange(1L,from, to,BigDecimal.ONE, BigDecimal.ONE, "Port " + informationService.retrieveServerPort());
//    }
}
