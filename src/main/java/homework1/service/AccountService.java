package homework1.service;

import homework1.model.Account;
import homework1.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    private List<Account> getAccounts(){
        return accountsRepository.findAll();
    }

    public List<Account> overCertainBalance(Double amount) {

        if (amount == null) {
            throw new RuntimeException("Amount cannot be null");
        }
        return getAccounts().stream()
                .filter(account -> account.getBalance() >= amount)
                .toList();
    }
    public Set<String> listCountries() {
        return getAccounts().stream()
                .map(Account::getCountry)
                .collect(Collectors.toSet());
    }

    public boolean youngerThanCertainYear(int year){
        return getAccounts().stream()
                .anyMatch(account -> account.getBirthday().isAfter(LocalDate.of(year, 1,1)));
    }

    public double sumBalanceByGender(String gender){
        return getAccounts().stream()
                .filter(account -> account.getGender().equals(gender))
                .map(Account::getBalance)
                .reduce( 0.0, Double::sum);
    }

    public Map<Month, List<Account>> groupingByMonthBirth(){
        return getAccounts().stream()
                .collect(Collectors.groupingBy(account -> account.getBirthday().getMonth()));
    }

    public double averageBalanceByCountry(String country){
        return getAccounts().stream()
                .filter(account -> account.getCountry().equals(country))
                .mapToDouble(Account::getBalance)
                .average()
                .orElse(0);
    }

    public List<String> listFirstNameCommaLastName(List<List<Account>> accountsLists){
        return accountsLists.stream()
                .flatMap(List::stream)
                .map(account -> account.getFirstName() + ", " + account.getLastName())
                .collect(Collectors.toList());
    }

    public List<Account> sortingByLastNameAndFirstName(){
        return getAccounts().stream()
                .sorted(Comparator.comparing(Account::getLastName).thenComparing(Account::getFirstName))
                .collect(Collectors.toList());
    }

    public Optional<Account> oldestAccount(){
        return getAccounts().stream()
                .min(Comparator.comparing(Account::getBirthday));
    }

    public Map<Year, Double> sortingByYearBirthdayAndBalance(){
        return getAccounts().stream()
                .collect(Collectors.groupingBy(account -> Year.of(account.getBirthday().getYear()),
                        Collectors.averagingDouble(Account::getBalance)));
    }

    public Optional<Account> longestLastName(){
        return  getAccounts().stream()
                .max(Comparator.comparingInt(account -> account.getLastName().length()));
    }
}
