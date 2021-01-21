package br.com.takuara.circumference;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;
import br.com.takuara.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("circunferens")
@JsonResource
public class CircumferenceResource extends BaseResource<Circumference> {

    @Inject private CircumferenceService circumferenceService;
    @Inject private UserService userService;

    @Override
    protected BaseService<Circumference> getService() {
        return circumferenceService;
    }

    @Override
    @GET
    public Response findAll() {
        return Response.ok(circumferenceService.findAll(getAuthenticatedUser())).build();
    }

}

