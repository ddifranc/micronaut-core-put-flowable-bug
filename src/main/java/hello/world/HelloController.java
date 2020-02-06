package hello.world;

import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;

@Controller("/hello")
public class HelloController {

    private final RxStreamingHttpClient rxclient;

    public HelloController() throws MalformedURLException {
        URL baseURL = new URL("http://httpbin.org");
        rxclient = RxStreamingHttpClient.create(baseURL);
    }


    @Put(value = "/uploadByteBuffer", consumes = MediaType.ALL, produces = MediaType.TEXT_PLAIN)
    public Flowable<HttpResponse<String>> uploadByteBuffer(@Body final Flowable<ByteBuffer> contents) {

        MutableHttpRequest<?> request = HttpRequest.PUT("/put", contents)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        return rxclient.exchange(request, String.class);

    }


    @Put(value = "/uploadByteArray", consumes = MediaType.ALL, produces = MediaType.TEXT_PLAIN)
    public Flowable<HttpResponse<String>> uploadByteArray(@Body final Flowable<byte[]> contents) {

        MutableHttpRequest<?> request = HttpRequest.PUT("/put", contents)
                .contentType(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        return rxclient.exchange(request, String.class);

    }


}