package ru.volnenko.maven.plugin.puml.goal;

import lombok.Getter;
import lombok.Setter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class PumlPackage extends AbstractMojo {

    @Getter
    @Setter
    @Parameter(property = "paths")
    private List<String> paths = new ArrayList<>();

    @Getter
    @Setter
    @Parameter(property = "files")
    private List<String> files = new ArrayList<>();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }

}
