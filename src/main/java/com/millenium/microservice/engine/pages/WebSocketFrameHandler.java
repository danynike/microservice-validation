package com.millenium.microservice.engine.pages;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.millenium.microservice.engine.util.XmlValidation;

/**
 * Echoes uppercase content of text frames.
 */
public class WebSocketFrameHandler extends
		SimpleChannelInboundHandler<WebSocketFrame> {

	private static final Log logger = LogFactory
			.getLog(WebSocketFrameHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame)
			throws Exception {
		// ping and pong frames already handled

		if (frame instanceof TextWebSocketFrame) {
			// Send the uppercase string back.
			String request = ((TextWebSocketFrame) frame).text();
			logger.info("{} received {}" + ctx.channel() + request);

			File f = new File(request);
			File[] matchingFiles = f.listFiles(new FilenameFilter() {

				public boolean accept(File dir, String name) {
					return name.endsWith(".xpdl");
				}
			});

			if (matchingFiles != null && matchingFiles.length > 0) {
				String x = getValidation(matchingFiles);
				
				/*String x = "<p>";
				for (File file : matchingFiles) {
					x += "<h2><b>File: " + file.getName() + "</b></h2><br/>";
					x += XmlValidation.Style0122AndStyle0123(file.getPath())
							+ "<br/><br/>";
					x += XmlValidation.Style0104(file.getPath()) + "<br/><br/>";
					x += XmlValidation.Style0115(file.getPath()) + "<br/><br/>";
					x += XmlValidation.Bpmn0102(file.getPath()) + "<br/><br/>";
				}
				x += "</p>";*/
				 

				ctx.channel().writeAndFlush(
						new TextWebSocketFrame(x.toUpperCase(Locale.US)));
			} else {
				ctx.channel().writeAndFlush(
						new TextWebSocketFrame("No se encontraron archivos"
								.toUpperCase(Locale.US)));
			}

		} else {
			String message = "unsupported frame type: "
					+ frame.getClass().getName();
			throw new UnsupportedOperationException(message);
		}
	}

	private String getValidation(File[] matchingFiles)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String x = "<p>";
		for (File file : matchingFiles) {
			x += "<h2><b>File: " + file.getName() + "</b></h2><br/>";
			for (Method method : XmlValidation.class.getDeclaredMethods()) {
				method.setAccessible(true);
				if (method.getName().startsWith("getStyle")
						|| method.getName().startsWith("getBpmn")) {
					x += method.invoke(null, file.getPath()) + "<br/>";
				}
			}
			x += "</p>";
		}
		return x;
	}
}
