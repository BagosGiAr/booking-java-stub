package com.evalonlabs.booking.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by Evangelos
 */
public class HttpResponse {
    public static void sendOk(ChannelHandlerContext ctx, String content) {
        writeResponse(ctx, HttpResponseStatus.OK, content);
    }

    public static void sendNotFound(ChannelHandlerContext ctx, String content) {
        writeResponse(ctx, HttpResponseStatus.NOT_FOUND, content);
    }

    public static void sendError(ChannelHandlerContext ctx, String content) {
        writeResponse(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR, content);
    }

    private static void writeResponse(ChannelHandlerContext ctx, HttpResponseStatus status, String content) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer(content, CharsetUtil.UTF_8)
        );

        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json; charset=UTF-8");

        // ignore keep-alive connections
        ctx.write(response).addListener(ChannelFutureListener.CLOSE);
    }
}
