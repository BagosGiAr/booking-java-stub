package com.evalonlabs.booking.http;

import io.netty.channel.ChannelPipeline;

/**
 * Created by Evangelos
 */
public interface CustomInitializer {
    public void apply(ChannelPipeline pipeline);
}
