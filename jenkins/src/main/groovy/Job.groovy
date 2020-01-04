import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import javaposse;

class Job {

    static void main(String ){
       def job = Jenkins.instance.createProject(FreeStyleProject, 'job-name')

        job.buildersList.add(new Shell('echo hello world'))

        job.save()

       def  build = job.scheduleBuild2(5, new hudson.model.Cause.UserIdCause())

        build.get() // Block until the build finishes

        def generatedJobs = build.getAction(javaposse.jobdsl.plugin.actions.GeneratedJobsBuildAction).getItems()

         // FIXME skip .scheduleBuild2() on Folder jobs
        generatedJobs.each { j -> j.scheduleBuild2(5, new hudson.model.Cause.UserIdCause()) }

    }
}



