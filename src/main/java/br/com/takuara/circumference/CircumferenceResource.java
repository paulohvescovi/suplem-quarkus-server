package br.com.takuara.circumference;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;
import br.com.takuara.user.User;
import br.com.takuara.user.UserService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.function.Supplier;

@Path("circunferens")
@JsonResource
public class CircumferenceResource extends BaseResource<Circumference> {

    @Inject private CircumferenceService circumferenceService;
    @Inject private UserService userService;

    @Override
    protected BaseService<Circumference> getService() {
        return circumferenceService;
    }

}

