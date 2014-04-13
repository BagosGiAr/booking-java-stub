package com.evalonlabs.booking.engine.protocol.http;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.engine.CallbackHandler;
import com.evalonlabs.booking.engine.model.Status;
import com.evalonlabs.booking.http.HttpResponse;
import com.evalonlabs.booking.http.RouteHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * Created by Evangelos
 */
public class StatusCall implements RouteHandler {
    private final BookSystem bookSystem;

    public StatusCall(BookSystem system) {
        this.bookSystem = system;
    }

    @Override
    public void handle(final ChannelHandlerContext ctx, final String path, final Map<String, Object> params) {
        bookSystem.getStatus(new CallbackHandler<Status>() {
            @Override
            public void apply(Status status) {
                if (status.getTotal() > 0) {
                    HttpResponse.sendOk(ctx, status.toString());
                    return;
                }
                HttpResponse.sendNotFound(ctx, "NO SEATS AVAILABLE");
            }
        });
    }
}
