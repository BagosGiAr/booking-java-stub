package com.evalonlabs.booking.engine.protocol.http;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.http.CustomInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * Created by Evangelos
 */
public class HttpInitializer implements CustomInitializer {
    private final BookSystem bookSystem;

    public HttpInitializer(BookSystem bookSystem) {
        this.bookSystem = bookSystem;
    }

    @Override
    public void apply(ChannelPipeline pipeline) {
        pipeline.addLast("handler", new HttpHandler(this.bookSystem));
    }
}
