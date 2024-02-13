package homework1.repository;

import homework1.exception.AccountNotFoundException;
import homework1.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountsRepository {

    private final List<Account> accounts;

    public AccountsRepository() {
        this.accounts = new ArrayList<>();
    }

    public List<Account> findAll(){
        return accounts;
    }

    public Account getAccountById(Long id) throws AccountNotFoundException{
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not founded"));
        }

    public void create(Account account){
        accounts.add(account);
    }

    public void update(Long id, Account updateAccount) {
        Account account = getAccountById(id);
        if (account != null) {
            account.setCountry(updateAccount.getCountry());
        } else {
            throw new AccountNotFoundException("Account was not found");
        }
    }

    public void delete(Long id){
        accounts.remove(getAccountById(id));
    }
}
