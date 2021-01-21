package br.com.takuara.circumference;

import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;

import javax.inject.Inject;
import javax.ws.rs.Path;

@Path("circunferens")
@JsonResource
public class CircumferenceResource extends BaseResource<Circumference> {

    @Inject private CircumferenceService circumferenceService;

    @Override
    protected BaseService<Circumference> getService() {
        return circumferenceService;
    }
}

