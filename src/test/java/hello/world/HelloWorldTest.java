package hello.world;


import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import org.junit.jupiter.api.Test;

import java.net.URL;


public class HelloWorldTest {




    @Test
    public void testUploadByteBuffer() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.PUT("/hello/uploadByteBuffer", "body".getBytes());

        Flowable<String> responses = rxclient.retrieve(request);
        for (String content : responses.blockingIterable()) {
            System.out.println(content);
        }


    }

    @Test
    public void testUploadByteArray() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.PUT("/hello/uploadByteArray", "body".getBytes());

        Flowable<String> responses = rxclient.retrieve(request);
        for (String content : responses.blockingIterable()) {
            System.out.println(content);
        }
    }

}
