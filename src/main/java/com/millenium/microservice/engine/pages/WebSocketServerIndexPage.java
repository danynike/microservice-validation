package com.millenium.microservice.engine.pages;

import java.nio.charset.Charset;

public final class WebSocketServerIndexPage {
	
    private static final String NEWLINE = "\r\n";

    public static String getContent(String webSocketLocation, String parameter) {
    	StringBuilder html = new StringBuilder("");
    	
			html.append("<html><head><title>Motor de Validacion</title></head>" + NEWLINE +
					"<body>" + NEWLINE +
					"<style type=\"text/css\">" + NEWLINE +
					"caption {" + NEWLINE +
					"   padding: 0.3em;" + NEWLINE +
					"}" + NEWLINE +
					"table {" + NEWLINE +
					"   width: 100%;" + NEWLINE +
					"  border-collapse: separate;" + NEWLINE +
					"  border-spacing: 4px;" + NEWLINE +
					"}" + NEWLINE +
					"table," + NEWLINE +
					"th," + NEWLINE +
					"td {" + NEWLINE +
					"  border: 1px solid #cecfd5;" + NEWLINE +
					"}" + NEWLINE +
					"th," + NEWLINE +
					"td {" + NEWLINE +
					"  padding: 10px 15px;" + NEWLINE +
					"}" + NEWLINE +
					"input.text, select.text, textarea.text {" + NEWLINE +
					"	border: 1px solid #393939;" + NEWLINE +
					"	border-radius: 5px 5px 5px 5px;" + NEWLINE +
					"	color: #393939;" + NEWLINE +
					"	font-size: 12px;" + NEWLINE +
					"	padding: 5px;" + NEWLINE +
					"}" + NEWLINE +
					".button {" + NEWLINE +
					"  padding:5px;" + NEWLINE +
					"  background-color: #dcdcdc;" + NEWLINE +
					"  border: 1px solid #666;" + NEWLINE +
					"  color:#000;" + NEWLINE +
					"  text-decoration:none;" + NEWLINE +
					"}" + NEWLINE +
					"</style>" + NEWLINE +
					"<script type=\"text/javascript\">" + NEWLINE +
					"window.onload = function() {" + NEWLINE +
					"  document.getElementById(\"search\").focus();" + NEWLINE +
					"};" + NEWLINE +
					"var socket;" + NEWLINE +
					"if (!window.WebSocket) {" + NEWLINE +
					"  window.WebSocket = window.MozWebSocket;" + NEWLINE +
					'}' + NEWLINE +
					"if (window.WebSocket) {" + NEWLINE +
					"  	socket = new WebSocket(\"" + webSocketLocation + "\");" + NEWLINE +
					"  	socket.onmessage = function(event) {" + NEWLINE +
					"		var div = document.getElementById('resp');" + NEWLINE +
					"		while(div.firstChild){" + NEWLINE +
					"			div.removeChild(div.firstChild);" + NEWLINE +
					"		}" + NEWLINE +
					"		" + NEWLINE +
					"		var divTmp = document.createElement('div');" + NEWLINE +
					"		divTmp.innerHTML = event.data;" + NEWLINE +
					"		document.getElementById('resp').appendChild(divTmp);" + NEWLINE +
					"  		document.getElementById(\"search\").value = '';" + NEWLINE +
					"  		document.getElementById(\"search\").focus();" + NEWLINE +
					"  };" + NEWLINE +
					"  socket.onopen = function(event) { };" + NEWLINE +
					"  socket.onclose = function(event) { };" + NEWLINE +
					"} else {" + NEWLINE +
					"  alert(\"Your browser does not support Web Socket.\");" + NEWLINE +
					"}" + NEWLINE +
					NEWLINE +
					"function send(message) {" + NEWLINE +
					"  if (!window.WebSocket) { return; }" + NEWLINE +
					"  if (socket.readyState == WebSocket.OPEN) {" + NEWLINE +
					"    socket.send(message );" + NEWLINE +
					"  } else {" + NEWLINE +
					"    alert(\"The socket is not open.\");" + NEWLINE +
					"  }" + NEWLINE +
					'}' + NEWLINE + "</script>");
			
		html.append("<form onsubmit=\"return false;\"><div id=\"content\"><TABLE><TABLE><thead>" + NEWLINE +
				"<tr><th colspan=\"100%\" align=\"left\">Ingrese la ruta de archivos .xpdl: <input type=\"text\" class=\"text\"  id=\"search\" name=\"search\" value=\"\" /> <input type=\"button\" class=\"button\" value=\"VALIDAR\" onclick=\"send(this.form.search.value)\" /></th></tr>" + NEWLINE +
				"</thead>" + NEWLINE +
				"<tbody></tbody></table>" + NEWLINE );
		
		html.append("</div><div id=\"resp\"></div>" + NEWLINE +
				"</form>" + NEWLINE +
				"</body>" + NEWLINE +
				"</html>" + NEWLINE);
    	
        return html.toString();
    }
    
    public static String removeCharacter(String input) {
	    String original = new String( Charset.forName("UTF-8").encode("Ã¡Ã©Ã­Ã³ÃºÃ±Ã�Ã‰Ã�Ã“ÃšÃ‘").array());
	    String[] ascii = new String[]{"&aacute;","&eacute;","&iacute;","&oacute;","&uacute;","&ntilde;","&Aacute;","&Eacute;","&Iacute;","&Oacute;","&Uacute;","&Ntilde;"};
	    String output = input;
	    for (int i=0; i<12; i++)
	        output = output.replace(""+original.charAt(i), ascii[i]);


	    original = new String( Charset.forName("UTF-8").encode("Ã Ã¤Ã¨Ã«Ã¬Ã¯Ã²Ã¶Ã¹Ã€Ã„ÃˆÃ‹ÃŒÃ�Ã’Ã–Ã™ÃœÃ§Ã‡").array());
	    String asciiChar = "aaeeiioouAAEEIIOOUUcC";
	    for (int i=0; i<21; i++)
	        output = output.replace(original.charAt(i), asciiChar.charAt(i));

	    return output;
	}

    private WebSocketServerIndexPage() {}
}

