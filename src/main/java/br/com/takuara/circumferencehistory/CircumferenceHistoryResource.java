package br.com.takuara.circumferencehistory;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("circunferens")
@JsonResource
public class CircumferenceHistoryResource extends BaseResource<CircumferenceHistory> {

    @Inject private CircumferenceHistoryService circumferenceHistoryService;

    @Override
    protected BaseService<CircumferenceHistory> getService() {
        return circumferenceHistoryService;
    }

    @Override
    //todo nao sei pq o find all chama do pai e nao desse aqui
    public Response findAll() {
        return Response.ok(circumferenceHistoryService.findAll(getAuthenticatedUser())).build();
    }
}

