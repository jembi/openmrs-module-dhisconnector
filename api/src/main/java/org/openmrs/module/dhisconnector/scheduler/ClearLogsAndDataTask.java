package org.openmrs.module.dhisconnector.scheduler;

import org.openmrs.api.context.Context;
import org.openmrs.module.dhisconnector.Configurations;
import org.openmrs.scheduler.tasks.AbstractTask;
import org.openmrs.util.OpenmrsUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.openmrs.module.dhisconnector.api.impl.DHISConnectorServiceImpl.DHISCONNECTOR_DATA_FOLDER;
import static org.openmrs.module.dhisconnector.api.impl.DHISConnectorServiceImpl.DHISCONNECTOR_LOGS_FOLDER;

/**
 * Created by k-joseph on 25/07/2017.
 */
public class ClearLogsAndDataTask  extends AbstractTask {

    @Override
    public void execute() {
        Integer storePeriod = new Configurations().getDataAndLogsStoragePeriod();
        File logFolder = new File(OpenmrsUtil.getApplicationDataDirectory() + DHISCONNECTOR_LOGS_FOLDER);
        File dataFolder = new File(OpenmrsUtil.getApplicationDataDirectory() + DHISCONNECTOR_DATA_FOLDER);
        Calendar cal = Calendar.getInstance(Context.getLocale());
        ArrayList<File> fs = new ArrayList<File>();

        cal.add(Calendar.MONTH, -storePeriod);
        listf(dataFolder, fs);
        deleteFilesBeforeDate(logFolder.listFiles(), cal);
        deleteFilesBeforeDate(fs.toArray(new File[fs.size()]), cal);
    }

    private void deleteFilesBeforeDate(File[] files, Calendar cal) {
        for(File f: files) {
            if(new Date(f.lastModified()).before(cal.getTime()))
                f.delete();
        }
    }

    public void listf(File directory, ArrayList<File> files) {
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file, files);
            }
        }
    }
}
