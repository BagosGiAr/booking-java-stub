package com.evalonlabs.booking.engine.protocol.http;

import com.evalonlabs.booking.engine.BookSystem;
import com.evalonlabs.booking.engine.CallbackHandler;
import com.evalonlabs.booking.engine.IDGenerator;
import com.evalonlabs.booking.engine.model.Book;
import com.evalonlabs.booking.http.ContextSystem;
import com.evalonlabs.booking.http.HttpResponse;
import com.evalonlabs.booking.http.RouteHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;

/**
 * Created by Evangelos
 */
public class BookCall implements RouteHandler {
    private final BookSystem bookSystem;

    public BookCall(BookSystem system) {
        this.bookSystem = system;
    }

    @Override
    public void handle(final ChannelHandlerContext ctx, final String path, final Map<String, Object> params) {
        final String id = (String) IDGenerator.generate();
        final String name = "test test";

        bookSystem.book(id, name, new CallbackHandler<Book>() {
            @Override
            public void apply(Book book) {
                if (book != null) {
                    HttpResponse.sendOk(ctx, book.toString());
                    return;
                }
                HttpResponse.sendError(ctx, "UNABLE TO BOOK " + id);
            }
        });
    }
}
