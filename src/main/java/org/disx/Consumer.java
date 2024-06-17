package org.disx;

import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Inject
    DisxRepository disxRepository;

    @Incoming("comment-queue")
    @Transactional
    public void consume(JsonObject json) {
        Long disxId = json.getLong("disxId");
        Disx disx = disxRepository.findById(disxId);
        disx.setCommentCount(disx.getCommentCount() + 1);
        disxRepository.persist(disx);
    }
}
