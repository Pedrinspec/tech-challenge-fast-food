package com.fiap.fast_food_tc.infrastructure.config;

import com.fiap.fast_food_tc.infrastructure.persistence.entity.CustomerPersistenceEntity;
import com.fiap.fast_food_tc.infrastructure.persistence.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public DataInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {
        log.info("Iniciando verificação de dados iniciais...");

        final String guestDocumentNumber = "00000000000";

        if (customerRepository.findByDocumentNumber(guestDocumentNumber).isEmpty()) {
            log.info("Cliente convidado não encontrado. Criando um novo...");

            CustomerPersistenceEntity guestCustomer = CustomerPersistenceEntity.builder()
                    .documentNumber(guestDocumentNumber)
                    .firstName("UNKNOWN")
                    .lastName("GUEST")
                    .email("guest@fastfoodtc.com")
                    .build();

            customerRepository.save(guestCustomer);
            log.info("Cliente convidado padrão criado com sucesso.");
        } else {
            log.info("Cliente convidado padrão já existe. Nenhuma ação necessária.");
        }
    }
}