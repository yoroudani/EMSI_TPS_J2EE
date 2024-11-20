package ma.emsi.bankaccountserver;

import ma.emsi.bankaccountserver.entities.BankAccount;
import ma.emsi.bankaccountserver.entities.Customer;
import ma.emsi.bankaccountserver.enums.AccountType;
import ma.emsi.bankaccountserver.repositories.BankAccountRepository;
import ma.emsi.bankaccountserver.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServerApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("testt","ttt","tt","test").forEach(c->{
                Customer customer = Customer.builder()
                        .name(c)
                        .build();
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i < 4; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance(10000+Math.random()*90000)
                            .createdAt(new Date())
                            .currency("DH")
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }

            });


        };
    }
}
