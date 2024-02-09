package homework1.controller;

import homework1.model.Account;
import homework1.repository.AccountsRepository;
import homework1.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountRestController {

    private final AccountService accountService;
    private final List<Account> accounts;
    private  final AccountsRepository accountsRepository;

    public AccountRestController(AccountService accountService, List<Account> accounts, AccountsRepository accountsRepository) {
        this.accountService = accountService;
        this.accounts = accounts;
        this.accountsRepository = accountsRepository;
    }

    @RequestMapping(value = "/balance/{balanceAmount}", method = RequestMethod.GET)
    public List<Account> account(@PathVariable double balanceAmount) {
        return accountService.overCertainBalance(accounts, balanceAmount);
    }

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public double account(@RequestBody List<Account> inputAccounts) {
        return accountService.sumBalanceByGender(inputAccounts, "Male");
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
    return accountsRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(@PathVariable("id") Long accountId) {
        return accountsRepository.getAccountById(accountId);
    }

    @PostMapping("/accounts")
    public void createAccount(@RequestBody Account account) {
        accountsRepository.create(account);
    }

    @PutMapping("/accounts/{id}")
    public void updateAccountById(@PathVariable("id") Long accountId, @RequestBody Account account) {
        accountsRepository.update(accountId, account);
    }

    @DeleteMapping("/accounts/{id}")
    public void deleteAccountById(@PathVariable("id") Long accountId) {
        accountsRepository.delete(accountId);
    }
}
