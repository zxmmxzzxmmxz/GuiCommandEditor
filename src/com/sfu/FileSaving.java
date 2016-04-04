package com.sfu;

import java.io.*;
import java.util.List;

public class FileSaving {
	static PrintWriter commandsWriter;
	public static void saveCommands(List<String> commands) throws FileNotFoundException, UnsupportedEncodingException{
		String baseDir = System.getProperty("user.dir");
		commandsWriter = new PrintWriter(baseDir + "\\commands.txt", "UTF-8");
		commandsWriter.println(commands.size());
		for(String cmd:commands){
			commandsWriter.println(cmd);
		}
		commandsWriter.close();
	}
}
