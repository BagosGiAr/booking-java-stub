package com.evalonlabs.booking.engine.protocol.http;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.engine.CallbackHandler;
import com.evalonlabs.booking.engine.model.Book;
import com.evalonlabs.booking.http.HttpResponse;
import com.evalonlabs.booking.http.RouteHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * Created by Evangelos
 */
public class CancelCall implements RouteHandler {
    private final BookSystem bookSystem;

    public CancelCall(BookSystem system) {
        this.bookSystem = system;
    }

    @Override
    public void handle(final ChannelHandlerContext ctx, final String path, final Map<String, Object> params) {
        final String id = (String) path.replaceAll("/(.*)/", "");

        bookSystem.cancel(id, new CallbackHandler<Boolean>() {
            @Override
            public void apply(Boolean wasSuccess) {
                if (wasSuccess) {
                    HttpResponse.sendOk(ctx, "");
                    return;
                }
                HttpResponse.sendNotFound(ctx, "NOT FOUND ID " + id);
            }
        });
    }
}
