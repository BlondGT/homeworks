package homework1.configuration;

import homework1.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountsConfig {

    @Bean
    public List<Account> accounts(){
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(
                "Pete",
                "Zet",
                "USA",
                LocalDate.of(1987, 3, 12),
                3600.00,
                "Male"));
        accountList.add(new Account(
                "Rose",
                "Love",
                "Canada",
                LocalDate.of(1990, 5, 28),
                13900.00,
                "Female"));
        return accountList;
    }
}
