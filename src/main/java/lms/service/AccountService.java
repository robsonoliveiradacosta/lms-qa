package lms.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lms.api.contract.request.AccountRequest;
import lms.api.contract.response.AccountResponse;
import lms.domain.model.Account;
import lms.domain.repository.AccountRepository;
import lms.exception.ResourceNotFoundException;
import lms.mapper.AccountMapper;

@ApplicationScoped
public class AccountService {

	@Inject
	AccountRepository repository;

	public List<AccountResponse> listAll() {
		return AccountMapper.INSTANCE.map(repository.listAll());
	}

	public AccountResponse findById(Long id) {
		Account account = repository.findByIdOptional(id).orElseThrow(ResourceNotFoundException::new);
		return AccountMapper.INSTANCE.accountToAccountResponse(account);
	}

	@Transactional
	public AccountResponse create(AccountRequest request) {
		Account account = AccountMapper.INSTANCE.accountRequestToAccount(request);
		repository.persist(account);
		return AccountMapper.INSTANCE.accountToAccountResponse(account);
	}

}
