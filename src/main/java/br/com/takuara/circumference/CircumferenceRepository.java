package br.com.takuara.circumference;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CircumferenceRepository implements PanacheRepository<Circumference> {

}

