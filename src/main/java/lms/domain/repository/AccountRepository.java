package lms.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lms.domain.model.Account;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

}
