package com.evalonlabs.booking.http;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.engine.protocol.http.BookCall;
import com.evalonlabs.booking.engine.protocol.http.CancelCall;
import com.evalonlabs.booking.engine.protocol.http.StatusCall;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * Created by Evangelos
 */
public enum Routes {
    ;
    public static RouteHandler get(ContextSystem system, String path) {

        if(path.matches("/seat(/?)")) {
            return new StatusCall((BookSystem) system);
        }
        if(path.matches("/seat/book(/?)")) {
            return new BookCall((BookSystem) system);
        }
        if(path.matches("/seat/cancel/(.*)(/?)")) {
            return new CancelCall((BookSystem) system);
        }

        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler post(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler put(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler patch(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler delete(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler options(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }

    public static RouteHandler any(ContextSystem system, String path) {
        return new RouteHandler() {
            @Override
            public void handle(ChannelHandlerContext ctx, String path, Map<String, Object> params) {
                HttpResponse.sendNotFound(ctx, "NOT FOUND: " + path);
            }
        };
    }
}
