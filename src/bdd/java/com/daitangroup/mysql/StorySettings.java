package com.daitangroup.mysql;

import com.daitangroup.mysql.steps.GroupSteps;
import com.daitangroup.mysql.steps.MessageSteps;
import com.daitangroup.mysql.steps.UserSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.Steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class StorySettings extends JUnitStories {


    @Override
    public Configuration configuration() {

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass().getClassLoader()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(Format.HTML, Format.CONSOLE)
                        .withRelativeDirectory("jbehave-report")
                );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {

        return new InstanceStepsFactory(configuration(), getSteps());
    }

    @Override
    protected List<String> storyPaths() {

        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()),
                Collections.singletonList("**/stories/*.story"), Collections.singletonList(""));
    }

    private List<Steps> getSteps(){

        List<Steps> steps = new ArrayList<>();

        steps.add(new UserSteps());
        steps.add(new GroupSteps());
        steps.add(new MessageSteps());

        return steps;
    }
}
