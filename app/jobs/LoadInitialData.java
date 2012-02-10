/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jobs;

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
        Fixtures.loadModels("questions.yml");
    }
}
