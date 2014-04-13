package com.evalonlabs.booking.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Evangelos
 */
public class Server implements Runnable {

    private final int port;
    private final AtomicReference<State> state;
    private final EventLoopGroup group;
    private final EventLoopGroup workerGroup;
    private final CustomInitializer initializer;

    public Server(int port) {
        this(port, null);
    }

    public Server(int port, CustomInitializer initializer) {
        this.port = port;
        this.initializer = initializer;

        this.state = new AtomicReference<State>(State.STOPPED);
        this.group = new NioEventLoopGroup(1);
        this.workerGroup = new NioEventLoopGroup();
    }

    public void stop() {
        state.compareAndSet(State.RUNNING, State.STOPPED);

        this.group.shutdownGracefully();
        this.workerGroup.shutdownGracefully();
    }

    public boolean isRunning() {
        return state.get() == State.RUNNING;
    }

    public void run() {
        if(!state.compareAndSet(State.STOPPED, State.RUNNING)) {
            return;
        }

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(this.group, this.workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer(this.initializer));

            Channel channel = bootstrap.bind(this.port).sync().channel();
            channel.closeFuture().sync();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            stop();
        }
    }

    private enum State {
        RUNNING, STOPPED
    }
}
