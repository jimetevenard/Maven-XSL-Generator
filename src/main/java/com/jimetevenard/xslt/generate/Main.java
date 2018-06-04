package com.jimetevenard.xslt.generate;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.jimetevenard.xslt.Driver;
import com.jimetevenard.xslt.GenerationException;

@Mojo(name = "generate-xsl")
public class Main extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject project;
	
	@Parameter
	private String scenariXmlFilePath;
	
	/**
	 * 
	 * By default, the intermediate XSL remains in memory.
	 * For debug purpose, we could want to store the intermediate XSL
	 * in a temp dir.
	 */
	@Parameter
	private String intermediateXslDirPath;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// *******************************************************************************
		this.getLog().info("[GENERATE XSL] does actually nothing, and is proud of it !");
		// *******************************************************************************
			
		File catalog = new File(this.project.getBasedir(), "catalog.xml");					
		File scenariFile = new File(this.project.getBasedir(), scenariXmlFilePath);
		
		Driver driver = new Driver( scenariFile, catalog);
		
		driver.setLicencedSaxon(true); // TODO whoooooo !!
		
		
		debugDir(driver);
			
		// Allez, zou !
		try {
			driver.launch();
		} catch (GenerationException e) {
			throw new MojoFailureException("Oupsy !", e);
		}
		

	}
	
	private void debugDir(Driver driver){
		if(intermediateXslDirPath != null){
			File debugDir = new File(intermediateXslDirPath);
			if(debugDir.exists()){
				driver.setDirForIntermediates(debugDir);
			} else {
				this.getLog().warn(debugDir.getAbsolutePath() + "is not an existing directory. intermediate XSLs will not be stored.");
			}
		} 
	}
	



}
