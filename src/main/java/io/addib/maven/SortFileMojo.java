package io.addib.maven;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * A custom maven plugin to sort lines in a file.
 * Any file path can be provided as an input.
 */
@Mojo(name="SortFile", defaultPhase = LifecyclePhase.COMPILE)
public class SortFileMojo extends AbstractMojo {

    /**
     * Input file absolute path
     */
    @Parameter(required = true)
    private String sourceFilePath;

    /**
     * Output file absolute path
     */
    @Parameter(required = true)
    private String destinationFilePath;

    /**
     * This is the overridden method that sorts the input file
     * and returns the output
     * @throws MojoExecutionException - if an unexpected problem occurs. Throwing this exception causes a "BUILD ERROR" message to be displayed.
     * @throws MojoFailureException - if an expected problem (such as a compilation failure) occurs. Throwing this exception causes a "BUILD FAILURE" message to be displayed.
     */
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            List<String> fileContent = FileUtils.readLines(new File(sourceFilePath), "UTF-8");
            Collections.sort(fileContent);
            FileUtils.writeLines(new File(destinationFilePath), "UTF-8", fileContent);
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to execute plugin", e);
        }
    }
}
