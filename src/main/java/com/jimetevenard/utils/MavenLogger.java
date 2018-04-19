package com.jimetevenard.utils;

import org.apache.maven.plugin.logging.Log;

import com.jimetevenard.utils.AnyLogger;

public class MavenLogger implements AnyLogger{
	private Log l;
	
	private MavenLogger(){
		
	}
	
	public static MavenLogger of(Log l){
		MavenLogger instance = new MavenLogger();
		instance.l = l;
		return instance;
	}
	
	@Override
	public void debug(String msg) {
		l.debug(msg);
		
	}

	@Override
	public void info(String msg) {
		l.info(msg);		
	}

	@Override
	public void warn(String msg) {
		l.warn(msg);
	}

	@Override
	public void error(String msg) {
		l.error(msg);		
	}

}
