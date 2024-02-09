package homework1.service;

import homework1.model.Account;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    public List<Account> overCertainBalance(List<Account> accounts, Double amount) {
        if (accounts == null) {
            throw new RuntimeException("List of accounts cannot be null");
        }
        if (amount == null) {
            throw new RuntimeException("Amount cannot be null");
        }

        return accounts.stream()
                .filter(account -> account.getBalance() >= amount)
                .toList();
    }
    public Set<String> listCountries(List<Account> accounts) {
        return accounts.stream()
                .map(Account::getCountry)
                .collect(Collectors.toSet());
    }

    public boolean youngerThanCertainYear(List<Account> accounts, int year){
        return accounts.stream()
                .anyMatch(account -> account.getBirthday().isAfter(LocalDate.of(year, 1,1)));
    }

    public double sumBalanceByGender(List<Account> accounts, String gender){
        return accounts.stream()
                .filter(account -> account.getGender().equals(gender))
                .map(Account::getBalance)
                .reduce( 0.0, Double::sum);
    }

    public Map<Month, List<Account>> groupingByMonthBirth(List<Account> accounts){
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> account.getBirthday().getMonth()));
    }

    public double averageBalanceByCountry(List<Account> accounts, String country){
        return accounts.stream()
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

    public List<Account> sortingByLastNameAndFirstName(List<Account> accounts){
        return accounts.stream()
                .sorted(Comparator.comparing(Account::getLastName).thenComparing(Account::getFirstName))
                .collect(Collectors.toList());
    }

    public Optional<Account> oldestAccount(List<Account> accounts){
        return accounts.stream()
                .min(Comparator.comparing(Account::getBirthday));
    }

    public Map<Year, Double> sortingByYearBirthdayAndBalance(List<Account> accounts){
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> Year.of(account.getBirthday().getYear()),
                        Collectors.averagingDouble(Account::getBalance)));
    }

    public Optional<Account> longestLastName(List<Account> accounts){
        return  accounts.stream()
                .max(Comparator.comparingInt(account -> account.getLastName().length()));
    }
}
