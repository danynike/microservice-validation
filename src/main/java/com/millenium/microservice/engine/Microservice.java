package com.millenium.microservice.engine;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.millenium.microservice.engine.pages.WebSocketServerInitializer;

@SuppressWarnings("deprecation")
public class Microservice {
	
	public static final Logger logger = LoggerFactory.getLogger(Microservice.class);
	
	public static void main(String[] args) throws Exception{
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(2);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		Channel ch = null;
		
		try {			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					bossGroup.shutdownNow();
					workerGroup.shutdownNow();
					Runtime.getRuntime().gc();
				}
			}));
			
	        try {
	        	ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	                .channel(NioServerSocketChannel.class)
	                .handler(new LoggingHandler(LogLevel.INFO))
	                .childHandler(new WebSocketServerInitializer());

	            ch = b.bind(8443).sync().channel();
	            ch.closeFuture().sync();
	            
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	            ch.close();
	        }
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			bossGroup.shutdownNow();
			workerGroup.shutdownNow();
			Runtime.getRuntime().gc();
			Runtime.getRuntime().exit(0);
		}
		
	}
	
}
