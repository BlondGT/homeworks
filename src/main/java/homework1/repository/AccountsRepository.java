package homework1.repository;

import homework1.exception.AccountNotFoundException;
import homework1.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountsRepository {

   private final SessionFactory sessionFactory;

    public AccountsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Account> findAll(){
        try(Session session = sessionFactory.openSession()){
           return session.createQuery("FROM Account", Account.class).list();
        }
    }

    public Optional<Account> getAccountById(Long id) throws AccountNotFoundException {
        Account account;
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, id);
        }
        return Optional.ofNullable(account);
    }


        public void create(Account account){
        try(Session session = sessionFactory.openSession()){
            session.save(account);
        }
    }

    public void update(Long id, Account updateAccount) {
        Optional<Account> account = getAccountById(id);
        try(Session session = sessionFactory.openSession()) {
            if (account.isPresent()) {
                Account gettingAccount = account.get();
                gettingAccount.setCountry(updateAccount.getCountry());
                session.update(gettingAccount);
            }
        }
    }

    public void delete(Long id){
        Optional<Account> account = getAccountById(id);
        try(Session session = sessionFactory.openSession()){
            session.delete(account);
        }
    }
}
