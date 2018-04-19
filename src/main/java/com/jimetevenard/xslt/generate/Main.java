package com.jimetevenard.xslt.generate;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.jimetevenard.utils.MavenLogger;
import com.jimetevenard.xslt.GenerationScenario;
import com.jimetevenard.xslt.ScenariParser;
import com.jimetevenard.xslt.implSaxon.SaxonScenariParser;
import com.jimetevenard.xslt.implSaxon.SaxonXSLTGenerator;
import com.jimetevenard.xslt.implSaxon.SaxonXSLTGenerator.GenerationException;

import net.sf.saxon.s9api.SaxonApiException;

@Mojo(name = "generate-xsl")
public class Main extends AbstractMojo {

	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject project;
	
	@Parameter
	private String scenariXmlFilePath;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		// *******************************************************************************
		this.getLog().info("[GENERATE XSL] does actually nothing, and is proud of it !");
		// *******************************************************************************


		MavenXslGenerator generator = new MavenXslGenerator(MavenLogger.of(this.getLog()),
				this.project.getBasedir().getAbsolutePath());

		File scenariFile = new File(this.project.getBasedir(), scenariXmlFilePath); 
		ScenariParser scenariParser = ScenariParser.newInstance(scenariFile.getAbsolutePath());

		
		try {
			scenariParser.parse();
			Set<GenerationScenario> scenari = scenariParser.getScenari();

			
			
			
			/*
			 * Les paramètre XSLT sont exprimés dans le fichier des scenari sous forme
			 * d'une expression XPath.
			 * 
			 * Le Context-Item utilisé pour l'évaluation de ce XPath est la racine du fichier des scenari.
			 * (Utile notemment pour la resolution d'URI relatives)
			 * 
			 *
			 * 
			 * TODO : Pas clair du tout, Gros refacto dans Generate-xsl.
			 * 

			 */
			((SaxonXSLTGenerator) generator.getXslGenerator()).getSaxonCompiler()
					.setParamsContextItem(((SaxonScenariParser) scenariParser).getScenariFileNode());

			// TODO 
			for (GenerationScenario s : scenari) {
				generator.getXslGenerator().compile(s.getParams(), s.getSourceXslPath(), s.getTargetXslPath());
			}
		} catch (SaxonApiException | URISyntaxException | GenerationException e) {
			// TODO Auto-generated catch block
			throw new MojoExecutionException("Oupsy !", e);
		}

	}


}
