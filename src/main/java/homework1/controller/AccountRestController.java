package homework1.controller;

import homework1.model.Account;
import homework1.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountRestController {

    private final AccountService accountService;
    private final List<Account> accounts;

    public AccountRestController(AccountService accountService, List<Account> accounts) {
        this.accountService = accountService;
        this.accounts = accounts;
    }

//    @GetMapping("/balance/{balanceAmount}")
//    public List<Account> account(@PathVariable double balanceAmount) {
//        return accountService.overCertainBalance(accounts, balanceAmount);
//    }

    @RequestMapping(value = "/balance/{balanceAmount}", method = RequestMethod.GET)
    public List<Account> account(@PathVariable double balanceAmount) {
        return accountService.overCertainBalance(accounts, balanceAmount);
    }

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public double account(@RequestBody List<Account> inputAccounts) {
        return accountService.sumBalanceByGender(inputAccounts, "Male");
    }


}
