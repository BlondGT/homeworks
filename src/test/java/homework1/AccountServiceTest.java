package homework1;

import homework1.model.Account;
import homework1.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    AccountService accountService;
    List<Account> accounts;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();

        accounts = List.of(
                new Account(1L, "Pete", "Zet", "USA", LocalDate.of(1987, 3, 12), 3600.00, "Male"),
                new Account(2L, "Rose", "Love", "Canada", LocalDate.of(1990, 5, 28), 13900.00, "Female"),
                new Account(3L, "Grace", "Creek", "Great Britain", LocalDate.of(1999, 3, 4), 600.00, "Female"),
                new Account(4L, "Ron", "Smith", "Canada", LocalDate.of(1990, 5, 21), 2400.00, "Male"),
                new Account(5L, "Pride", "James", "Mexico", LocalDate.of(2000, 5, 30), 10000.00, "Male"),
                new Account(6L,"Hope", "Toronto", "USA", LocalDate.of(1979, 5, 9), 5800.00, "Female"),
                new Account(7L, "Liza", "Junior", "Great Britain", LocalDate.of(2000, 3, 16), 1500.00, "Female")
        );
    }

    @Test
    void shouldOverCertainBalance() {
        List<Account> expectedAccounts = List.of(
                new Account(2L, "Rose", "Love", "Canada", LocalDate.of(1990, 5, 28), 13900.00, "Female"),
                new Account(5L, "Pride", "James", "Mexico", LocalDate.of(2000, 5, 30), 10000.00, "Male"));

        assertEquals(expectedAccounts, accountService.overCertainBalance(accounts, 10000.00));
        assertNotNull(accountService.overCertainBalance(accounts, -1.0));

    }

        @Test
        void shouldOverCertainBalanceThrowExceptionAmountNull() {
            Throwable exception = assertThrows(RuntimeException.class,
                    () -> accountService.overCertainBalance(accounts, null));
            assertEquals("Amount cannot be null", exception.getMessage());
        }

    @Test
    void shouldOverCertainBalanceThrowExceptionAccountsNull() {
        Throwable exception1 = assertThrows(RuntimeException.class,
                () -> accountService.overCertainBalance(null, 10000.0));
        assertEquals("List of accounts cannot be null", exception1.getMessage());
    }

    @Test
    void shouldListCountries() {

        Set<String> countries = new HashSet<>(List.of("USA", "Canada", "Great Britain", "Mexico"));

        assertEquals(countries, accountService.listCountries(accounts));
    }

    @Test
    void shouldYoungerThanCertainYear() {

        assertTrue(accountService.youngerThanCertainYear(accounts, 2000));
        assertFalse(accountService.youngerThanCertainYear(accounts, 2006));
    }

    @Test
    void shouldSumBalanceByGender() {

        assertEquals(16000.00, accountService.sumBalanceByGender(accounts, "Male"));
        assertEquals(21800.0, accountService.sumBalanceByGender(accounts, "Female"));
    }

    @Test
    void shouldGroupingByMonthBirth() {

        Map<Month, List<Account>> map = new HashMap<>();

        map.put(Month.of(3), List.of(
                new Account(1L, "Pete", "Zet", "USA", LocalDate.of(1987, 3,12), 3600.00, "Male"),
                new Account(3L, "Grace", "Creek", "Great Britain", LocalDate.of(1999, 3,4), 600.00, "Female"),
                new Account(7L, "Liza", "Junior", "Great Britain", LocalDate.of(2000, 3,16), 1500.00, "Female")
        ));
        map.put(Month.of(5), List.of(
                new Account(2L, "Rose", "Love", "Canada", LocalDate.of(1990, 5,28), 13900.00, "Female"),
                new Account(4L, "Ron", "Smith", "Canada", LocalDate.of(1990, 5,21), 2400.00, "Male"),
                new Account(5L, "Pride", "James", "Mexico", LocalDate.of(2000, 5,30), 10000.00, "Male"),
                new Account(6L ,"Hope", "Toronto", "USA", LocalDate.of(1979, 5,9), 5800.00, "Female")
        ));
        Map<Month, List<Account>> result = accountService.groupingByMonthBirth(accounts);
        assertEquals(map, result);
        assertNotNull(result.entrySet());
        assertEquals(result.entrySet().size(), 2);
    }

    @Test
    void shouldAverageBalanceByCountry() {

        assertEquals(4700.00, accountService.averageBalanceByCountry(accounts, "USA"));
        assertEquals(0.0, accountService.averageBalanceByCountry(accounts, "Chili"));
    }

    @Test
    void shouldListFirstNameCommaLastName() {

        List<Account> list1 = Arrays.asList(
                new Account(1L, "Pete", "Zet", "USA", LocalDate.of(1987, 3,12), 3600.00, "Male"),
                new Account(2L, "Rose", "Love", "Canada", LocalDate.of(1990, 5,28), 13900.00, "Female"),
                new Account(3L, "Grace", "Creek", "Great Britain", LocalDate.of(1999, 3,4), 600.00, "Female"),
                new Account(4L, "Ron", "Smith", "Canada", LocalDate.of(1990, 5,21), 2400.00, "Male")
        );
        List<Account> list2 = Arrays.asList(
                new Account(5L, "Pride", "James", "Mexico", LocalDate.of(2000, 5,30), 10000.00, "Male"),
                new Account(6L, "Hope", "Toronto", "USA", LocalDate.of(1979, 5,9), 5800.00, "Female"),
                new Account(7L, "Liza", "Junior", "Great Britain", LocalDate.of(2000, 3,16), 1500.00, "Female")
        );

        List<List<Account>> accountsLists = Arrays.asList(list1, list2);
        List<String> expectedList = Arrays.asList("Pete, Zet", "Rose, Love", "Grace, Creek", "Ron, Smith",
                "Pride, James", "Hope, Toronto", "Liza, Junior");

        assertEquals(expectedList, accountService.listFirstNameCommaLastName(accountsLists));
    }

    @Test
    void shouldSortingByLastNameAndFirstName() {

        List<Account> expectedList = List.of(
                new Account(3L, "Grace", "Creek", "Great Britain", LocalDate.of(1999, 3,4), 600.00, "Female"),
                new Account(5L, "Pride", "James", "Mexico", LocalDate.of(2000, 5,30), 10000.00, "Male"),
                new Account(7L, "Liza", "Junior", "Great Britain", LocalDate.of(2000, 3,16), 1500.00, "Female"),
                new Account(2L, "Rose", "Love", "Canada", LocalDate.of(1990, 5,28), 13900.00, "Female"),
                new Account(4L, "Ron", "Smith", "Canada", LocalDate.of(1990, 5,21), 2400.00, "Male"),
                new Account(6L, "Hope", "Toronto", "USA", LocalDate.of(1979, 5,9), 5800.00, "Female"),
                new Account(1L, "Pete", "Zet", "USA", LocalDate.of(1987, 3,12), 3600.00, "Male")
        );

        assertEquals(expectedList, accountService.sortingByLastNameAndFirstName(accounts));
    }

    @Test
    void shouldOldestAccount() {

        Optional<Account> expectedAccount = Optional.of(new Account(6L, "Hope", "Toronto", "USA", LocalDate.of(1979, 5, 9), 5800.00, "Female"));

        assertEquals(expectedAccount, accountService.oldestAccount(accounts));
    }

    @Test
    void shouldSortingByYearBirthdayAndBalance() {
        Map<Year, Double> map = new HashMap<>();
        map.put(Year.of(1979),5800.0);
        map.put(Year.of(1987), 3600.0);
        map.put(Year.of(1990), 8150.0);
        map.put(Year.of(1999), 600.0);
        map.put(Year.of(2000), 5750.0);

        assertEquals(map, accountService.sortingByYearBirthdayAndBalance(accounts));
    }

    @Test
    void shouldLongestLastName() {

        Optional<Account> expectedAccount = Optional.of(new Account(6L, "Hope", "Toronto", "USA", LocalDate.of(1979, 5, 9), 5800.00, "Female"));

        assertEquals(expectedAccount, accountService.longestLastName(accounts));
    }
}