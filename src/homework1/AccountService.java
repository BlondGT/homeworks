import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class AccountService {

    public static List<Account> over500Balance (List<Account> accounts){
        return accounts.stream()
                .filter(account -> account.getBalance() >= 500)
                .toList();
    }
    public static Set<String> listCountries(List<Account> accounts) {
        return accounts.stream()
                .map(Account::getCountry)
                .collect(Collectors.toSet());
    }

    public static boolean youngerThan2000Year(List<Account> accounts){
        return accounts.stream()
                .allMatch(account -> account.getBirthday().getYear() < 2000);
    }

    public static double sumAllMaleBalance(List<Account> accounts){
        return accounts.stream()
                .filter(account -> account.getGender().equals("Male"))
                .map(Account::getBalance)
                .reduce( 0.0, Double::sum);
    }

    public static Map<Month, List<Account>> groupingByMonthBirth(List<Account> accounts){
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> account.getBirthday().getMonth()));
    }

    public static double averageBalanceByCountry(List<Account> accounts, String country){
        return accounts.stream()
                .filter(account -> account.getCountry().equals(country))
                .mapToDouble(Account::getBalance)
                .average()
                .orElse(0);
    }

    public static List<String> listFirstNameCommaLastName(List<List<Account>> accountsLists){
        return accountsLists.stream()
                .flatMap(List::stream)
                .map(account -> account.getFirstName() + ", " + account.getLastName())
                .collect(Collectors.toList());
    }

    public static List<Account> sortingByLastNameAndFirstName(List<Account> accounts){
        return accounts.stream()
                .sorted(Comparator.comparing(Account::getLastName).thenComparing(Account::getFirstName))
                .collect(Collectors.toList());
    }

    public static Optional<Account> oldestAccount(List<Account> accounts){
        return accounts.stream()
                .max(Comparator.comparing(Account::getBirthday));
    }

    public static Map<Double, Long> sortingByPlaceAndBalance(List<Account> accounts){
        return accounts.stream()
                .collect(Collectors.groupingBy(Account::getBalance, Collectors.counting()));
    }

    public static Optional<Account> longestLastName(List<Account> accounts){
        return  accounts.stream()
                .max(Comparator.comparingInt(account -> account.getLastName().length()));
    }
}
