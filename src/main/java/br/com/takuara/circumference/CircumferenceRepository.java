package br.com.takuara.circumference;

import br.com.takuara.user.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CircumferenceRepository implements PanacheRepository<Circumference> {

    public Circumference findByUser(User user){
        return find("user", user).firstResult();
    }

}

