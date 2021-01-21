package br.com.takuara.circumference;

import br.com.takuara.framework.BaseServiceImpl;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CircumferenceServiceImpl extends BaseServiceImpl<Circumference> implements CircumferenceService {

    @Inject private CircumferenceRepository circumferenceRepository;

    @Override
    protected PanacheRepository<Circumference> getRepository() {
        return circumferenceRepository;
    }
}
