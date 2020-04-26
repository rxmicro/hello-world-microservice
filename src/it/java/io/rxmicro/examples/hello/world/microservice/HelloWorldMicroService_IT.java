package io.rxmicro.examples.hello.world.microservice;

import io.rxmicro.http.client.ClientHttpResponse;
import io.rxmicro.test.BlockingHttpClient;
import io.rxmicro.test.junit.RxMicroIntegrationTest;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static io.rxmicro.test.json.JsonFactory.jsonObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author nedis
 * @link http://rxmicro.io
 * @since 0.1
 */
@RxMicroIntegrationTest
@Testcontainers
final class HelloWorldMicroService_IT {

    @Container
    private static final DockerComposeContainer<?> compose =
            new DockerComposeContainer<>(new File("docker-compose.yml").getAbsoluteFile())
                    .withLocalCompose(true)
                    .withPull(false)
                    .withTailChildContainers(true)
                    .waitingFor("hello-world-microservice", Wait.forHttp("/http-health-check"));

    private BlockingHttpClient blockingHttpClient;

    @Test
    void Should_return_Hello_World() {
        final ClientHttpResponse response = blockingHttpClient.get("/");

        assertEquals(jsonObject("message", "Hello World!"), response.body());
        assertEquals(200, response.statusCode());
    }
}
