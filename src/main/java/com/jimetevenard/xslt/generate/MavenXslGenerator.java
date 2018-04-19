package com.jimetevenard.xslt.generate;

import java.io.File;



import com.jimetevenard.utils.AnyLogger;
import com.jimetevenard.xslt.XSLGenerator;
import com.jimetevenard.xslt.implSaxon.SaxonProcessorHolder;


// Cette classe sert à QQCHose ?? 


public class MavenXslGenerator {

	private File projectRoot;
	private String catalogPath;
	private XSLGenerator xslGenerator;


	
	



	public MavenXslGenerator(AnyLogger log, String projectPath) {
		this.projectRoot = new File(projectPath);
		log.debug("Building an XfeGenerator ! RootPath is : " + projectPath);
		this.catalogPath = new File(this.projectRoot, "catalog.xml").getAbsolutePath();
		
		
		
		System.setProperty(SaxonProcessorHolder.LICENCED_PROP, "true");
		xslGenerator = XSLGenerator.newInstance(log, this.catalogPath);
	}



	
	
	public XSLGenerator getXslGenerator() {
		return xslGenerator;
	}
	


}
