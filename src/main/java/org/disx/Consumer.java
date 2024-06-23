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
    public void consume(JsonObject message) {
        log.info("Message received: {}", message);

        Long disxId = message.getLong("disxId");
        Boolean isDeleted = message.getBoolean("isDeleted");

        Disx disx = disxRepository.findById(disxId);

        if (disx != null) {
            if (isDeleted) {
                disx.setCommentCount(disx.getCommentCount() - 1);
                log.info("Decremented comment count for disxId {}. New count: {}", disxId, disx.getCommentCount());
            } else {
                disx.setCommentCount(disx.getCommentCount() + 1);
                log.info("Incremented comment count for disxId {}. New count: {}", disxId, disx.getCommentCount());
            }
            disxRepository.persist(disx);
        }
    }
}
