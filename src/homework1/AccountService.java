import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountService {

    public List<Account> overCertainBalance (List<Account> accounts, double amount){
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
                .allMatch(account -> account.getBirthday().getYear() < year);
    }

    public double sumBalanceBeGender(List<Account> accounts, String gender){
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
                .max(Comparator.comparing(Account::getBirthday));
    }

    public Map<Year, Double> sortingByPlaceAndBalance(List<Account> accounts){
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> Year.of(account.getBirthday().getYear()),
                        Collectors.averagingDouble(Account::getBalance)));
    }

    public Optional<Account> longestLastName(List<Account> accounts){
        return  accounts.stream()
                .max(Comparator.comparingInt(account -> account.getLastName().length()));
    }
}
