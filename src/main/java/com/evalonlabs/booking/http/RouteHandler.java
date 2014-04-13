package com.evalonlabs.booking.http;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * Created by Evangelos
 */
public interface RouteHandler {
    public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params);
}
