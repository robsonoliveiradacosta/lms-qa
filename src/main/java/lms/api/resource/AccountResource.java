package lms.api.resource;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import lms.api.contract.request.AccountRequest;
import lms.api.contract.response.AccountResponse;
import lms.service.AccountService;

@Path("/v1/accounts")
public class AccountResource {

	@Inject
	AccountService service;

	@POST
	public Response create(@Valid AccountRequest request) {
		return Response.ok(service.create(request)).status(Status.CREATED).build();
	}

	@GET
	public List<AccountResponse> listAll() {
		return service.listAll();
	}

	@Path("/{id}")
	@GET
	public AccountResponse findById(@PathParam("id") Long id) {
		return service.findById(id);
	}

}
