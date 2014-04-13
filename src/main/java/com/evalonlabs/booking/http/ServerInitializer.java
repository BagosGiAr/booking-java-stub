package com.evalonlabs.booking.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by Evangelos
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final CustomInitializer initializer;

    public ServerInitializer() {
        this(null);
    }

    public ServerInitializer(CustomInitializer initializer) {
        this.initializer = initializer;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("http_encoder", new HttpResponseEncoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));

        if(this.initializer != null) {
            this.initializer.apply(pipeline);
        }
    }
}