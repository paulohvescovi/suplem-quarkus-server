package br.com.takuara.circumference;

import br.com.takuara.circumferencehistory.CircumferenceHistory;
import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CircumferenceRepository implements PanacheRepository<Circumference> {

    public List<Circumference> findByUser(User user){
        return list("user", user);
    }

}

