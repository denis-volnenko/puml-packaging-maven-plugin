package ru.volnenko.maven.plugin.puml.goal;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mojo(name = "package", defaultPhase = LifecyclePhase.PACKAGE)
public class PumlPackage extends AbstractMojo {

    @Getter
    @Setter
    @Parameter(property = "paths")
    private List<String> paths = new ArrayList<>();

    @Getter
    @Setter
    @Parameter(property = "files")
    private List<String> files = new ArrayList<>();

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    @SneakyThrows
    public void execute() throws MojoExecutionException, MojoFailureException {
        @NonNull final File buildPath = new File(project.getBuild().getDirectory());
        buildPath.mkdirs();

        @NonNull final String sourceName = project.getBuild().getFinalName() + "." + project.getPackaging();
        @NonNull final File build = new File(project.getBuild().getDirectory(), sourceName);
        build.createNewFile();

        @NonNull final Artifact artifact =  project.getArtifact();
        artifact.setFile(build);
    }

}
