package br.com.luizmrd.repository;

import br.com.luizmrd.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    Exchange findByFromAndTo(String from, String to);
}
