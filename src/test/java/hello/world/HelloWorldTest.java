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



    //fails w/ IllegalReferenceCountException
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
    public void testUploadByteArraySmall() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        byte[] body = new byte[10];
        MutableHttpRequest<?> request = HttpRequest.PUT("/hello/uploadByteArray", body);

        Flowable<String> responses = rxclient.retrieve(request);
        for (String content : responses.blockingIterable()) {
            System.out.println(content);
        }
    }

    //fails w/ ReadTimeoutExcepton
    @Test
    public void testUploadByteArrayLarge() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        byte[] body = new byte[10000];
        MutableHttpRequest<?> request = HttpRequest.PUT("/hello/uploadByteArray", body);

        Flowable<String> responses = rxclient.retrieve(request);
        for (String content : responses.blockingIterable()) {
            System.out.println(content);
        }
    }


    @Test
    public void testDownloadByteBufferSmall() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.GET("/hello/downloadByteBuffer/10")
                .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        Flowable<HttpResponse<ByteBuffer<?>>> responses = rxclient.exchangeStream(request);
        for (HttpResponse<ByteBuffer<?>> content: responses.blockingIterable()) {
            System.out.println(content.body().toByteArray().length);
        }


    }

    @Test
    public void testDownloadByteBufferLarge() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.GET("/hello/downloadByteBuffer/100000")
                .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        Flowable<HttpResponse<ByteBuffer<?>>> responses = rxclient.exchangeStream(request);
        for (HttpResponse<ByteBuffer<?>> content: responses.blockingIterable()) {
            System.out.println(content.body().toByteArray().length);
        }


    }



    @Test
    public void testDownloadByteArraySmall() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.GET("/hello/downloadByteArray/10")
                .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        Flowable<HttpResponse<ByteBuffer<?>>> responses = rxclient.exchangeStream(request);
        for (HttpResponse<ByteBuffer<?>> content: responses.blockingIterable()) {
            System.out.println(content.body().toByteArray().length);
        }


    }

    @Test
    public void testDownloadByteArrayLarge() throws Exception {
        URL baseURL = new URL("http://localhost:8080");
        RxStreamingHttpClient rxclient = RxStreamingHttpClient.create(baseURL);

        MutableHttpRequest<?> request = HttpRequest.GET("/hello/downloadByteArray/100000")
                .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE);

        Flowable<HttpResponse<ByteBuffer<?>>> responses = rxclient.exchangeStream(request);
        for (HttpResponse<ByteBuffer<?>> content: responses.blockingIterable()) {
            System.out.println(content.body().toByteArray().length);
        }


    }

}
