package br.com.takuara.user;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("users")
@JsonResource
public class UserResource extends BaseResource<User> {

    @Inject private UserService userService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Response findAll() {
        return super.findAll();
    }
}
