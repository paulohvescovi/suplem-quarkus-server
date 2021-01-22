package br.com.takuara.circumferencehistory;

import br.com.takuara.enumeration.CircumferenceFields;
import br.com.takuara.framework.BaseResource;
import br.com.takuara.framework.BaseService;
import br.com.takuara.framework.annotations.JsonResource;

import javax.annotation.security.DenyAll;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("circunferens_history")
@JsonResource
public class CircumferenceHistoryResource extends BaseResource<CircumferenceHistory> {

    @Inject private CircumferenceHistoryService circumferenceHistoryService;

    @Override
    protected BaseService<CircumferenceHistory> getService() {
        return circumferenceHistoryService;
    }

    @Override
    @GET
    public Response findAll() {
        return Response.ok(circumferenceHistoryService.findAll(getAuthenticatedUser())).build();
    }

    @Override
    @POST
    @DenyAll
    public Response save(@Valid CircumferenceHistory entity) {
        return super.save(entity);
    }

    @Override
    @PUT
    @DenyAll
    public Response update(Long id, @Valid CircumferenceHistory entity) {
        return super.update(id, entity);
    }

    @GET
    @Path("findByFields")
    public Response findByFields(List<CircumferenceFields> fieldsList){
        Map<CircumferenceFields, List<CircumferenceHistory>> mapResult = new HashMap<>();

        fieldsList.forEach(field ->
                mapResult.put(
                        field,
                        circumferenceHistoryService.findByUserAndField(getAuthenticatedUser(), field)
                )
        );

        return Response.ok(mapResult).build();

    }
}

