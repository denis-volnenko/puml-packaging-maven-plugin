package ru.volnenko.maven.plugin.puml.goal;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import ru.volnenko.maven.plugin.puml.parser.RootParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class PumlGenerate extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    private final RootParser rootParser = new RootParser();

    @Parameter(property = "paths")
    private List<String> paths = new ArrayList<>();

    @Parameter(property = "files")
    private List<String> files = new ArrayList<>();

    @Parameter(property = "src")
    private String src = "";

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        final File file = new File(project.getBasedir(), "/src/main/plantuml");
        final File buildPath = new File(project.getBuild().getDirectory());
        buildPath.mkdirs();

        final String sourceName = project.getBuild().getFinalName() + "." + project.getPackaging();
        final File build = new File(project.getBuild().getDirectory(), sourceName);
        rootParser.file(file).save(build);
    }

}
