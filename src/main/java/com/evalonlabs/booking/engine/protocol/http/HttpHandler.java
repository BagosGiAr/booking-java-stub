package com.evalonlabs.booking.engine.protocol.http;


import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.http.Routes;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Evangelos
 */
public class HttpHandler extends SimpleChannelInboundHandler<Object> {

    private final BookSystem bookSystem;
    private HttpRequest request;
    private QueryStringDecoder decoder;
    private Map<String, Object> normalizedParams = new HashMap<String, Object>();

    public HttpHandler(BookSystem bookSystem) {
        this.bookSystem = bookSystem;
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            this.request = (HttpRequest) msg;

            if (HttpHeaders.is100ContinueExpected(this.request)) {
                send100(ctx);
            }

            this.decoder = new QueryStringDecoder(this.request.getUri());

            Map<String, List<String>> params = this.decoder.parameters();

            if (!params.isEmpty()) {
                for (Map.Entry<String, List<String>> param : params.entrySet()) {
                    String key = param.getKey();
                    List<String> vals = param.getValue();
                    for (String val : vals) {
                        this.normalizedParams.put(key, val);
                    }
                }
            }
        }

        if (msg instanceof LastHttpContent) {
            try {
                String path = decoder.path();

                if (this.request.getMethod().name().equals("GET")) {
                    Routes.get(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else if (this.request.getMethod().name().equals("POST")) {
                    Routes.post(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else if (this.request.getMethod().name().equals("PUT")) {
                    Routes.put(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else if (this.request.getMethod().name().equals("OPTIONS")) {
                    Routes.options(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else if (this.request.getMethod().name().equals("DELETE")) {
                    Routes.delete(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else if (this.request.getMethod().name().equals("PATCH")) {
                    Routes.patch(this.bookSystem, path).handle(ctx, path, normalizedParams);
                } else {
                    Routes.any(this.bookSystem, path).handle(ctx, path, normalizedParams);
                }

            } catch (Exception e) {
                e.printStackTrace();
                com.evalonlabs.booking.http.HttpResponse.sendError(ctx, "{\"status\":\"500\", \"message\": \"" + e + "\"}");
            }
        }
    }

    private void send100(ChannelHandlerContext ctx) {
        ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
