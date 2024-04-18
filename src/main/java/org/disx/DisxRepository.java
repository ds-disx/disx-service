package org.disx;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisxRepository implements PanacheRepository<Disx> {

}
