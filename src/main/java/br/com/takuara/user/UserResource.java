package br.com.takuara.user;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("users")
@JsonResource
public class UserResource extends BaseResource<User> {

    @Inject private UserService userService;

    @Override
    protected BaseService<User> getService() {
        return userService;
    }
}
