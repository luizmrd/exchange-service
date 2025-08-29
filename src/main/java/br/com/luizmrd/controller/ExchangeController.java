package br.com.luizmrd.controller;

import br.com.luizmrd.model.Exchange;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;



@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

    @GetMapping(value = "/{amonut}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(
            @PathVariable("amonut") BigDecimal amonut,
            @PathVariable("from") String from,
            @PathVariable("to") String to) {
        return new Exchange(1L,from, to,BigDecimal.ONE, BigDecimal.ONE, "Port 8000");
    }
}
