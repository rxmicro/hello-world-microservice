import io.rxmicro.monitoring.healthcheck.EnableHttpHealthCheck;

/**
 * @author nedis
 * @link http://rxmicro.io
 * @since 0.1
 */
@EnableHttpHealthCheck
module hello.world.microservice {
    requires rxmicro.rest.server.netty;
    requires rxmicro.rest.server.exchange.json;
    requires rxmicro.monitoring;
}