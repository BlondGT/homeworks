package homework1.repository;

import homework1.exception.AccountNotFoundException;
import homework1.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Account read(Long id){
        for(Account account : accounts){
            if(Objects.equals(account.getId(), id))
                return account;
        }
        return null;
    }

    public void update(Long id, Account updateAccount) {
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            if (Objects.equals(account.getId(), updateAccount.getId())) {
                accounts.set(i, updateAccount);
                return;
            }
        }
            throw new AccountNotFoundException("Account was not found");
    }

    public void delete(Long id){
        Account accountDel = accounts.stream()
                .filter(account -> Objects.equals(account.getId(), id))
                .findAny()
                .orElseThrow();
        accounts.remove(accountDel);
    }
}
