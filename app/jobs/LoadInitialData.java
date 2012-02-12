/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

import models.Answer;
import models.Configuration;
import models.Question;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 *
 * @author andreas
 */
@OnApplicationStart
public class LoadInitialData extends Job {
     public void doJob() {
        play.Logger.info("Loading application inidital data from yml-files");
        play.Logger.info("Questions in db: %s",Question.count());
        play.Logger.info("Answers in db: %s",Answer.count());
        play.Logger.info("Configuratons in db: %s",Configuration.count());
        Fixtures.delete(Question.class, Answer.class, Configuration.class);
        play.Logger.info("Deleting Questions, Answers and Configurations");
        play.Logger.info("Questions in db: %s",Question.count());
        play.Logger.info("Answers in db: %s",Answer.count());
        play.Logger.info("Configuratons in db: %s",Configuration.count());
        play.Logger.info("Loading initial data with fixtures");
        Fixtures.loadModels("questions.yml");
        Fixtures.loadModels("configuration.yml");
        play.Logger.info("Questions in db: %s",Question.count());
        play.Logger.info("Answers in db: %s",Answer.count());
        play.Logger.info("Configuratons in db: %s",Configuration.count());
        //Fixtures.loadModels("test-scores.yml");
    }
}
