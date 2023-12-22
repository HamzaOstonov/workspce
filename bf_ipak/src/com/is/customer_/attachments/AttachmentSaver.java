package com.is.customer_.attachments;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Execution;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 20.07.2017.
 * 11:47
 */
public class AttachmentSaver implements Runnable{
    private final Logger logger = Logger.getLogger(AttachmentSaver.class);
    private final Attachment attachment;
    private final Execution execution;

    public AttachmentSaver(Execution execution, Attachment attachment){
        this.attachment = attachment;
        this.execution = execution;
    }

    @Override
    public void run() {
        final File temporaryDirectory = Files.createTempDir();
        final File temporaryFile = new File(temporaryDirectory,attachment.getFileName());
        final ByteSink byteSink = Files.asByteSink(temporaryFile);
        try{
            byteSink.write(attachment.getData());
            System.out.println(temporaryFile.getAbsolutePath());
            //execution.sendRedirect(temporaryFile.getAbsolutePath(),"_blank");
            //deleteDir(temporaryFile);
        }
        catch (IOException e){
            Thread.currentThread().interrupt();

            logger.error(CheckNull.getPstr(e));

            if (temporaryFile.exists()) {
                deleteDir(temporaryFile);
            }

            throw new RuntimeException
                    (String.format
                            ("%s %s",
                                    e.getClass(),
                                    e.getCause() != null ?
                                            e.getCause().getMessage()
                                            : e.getMessage()));
        }
    }

    void deleteDir(File file){
        if (file.listFiles() != null) {
            for (File tempFile : file.listFiles()) {
                deleteDir(tempFile);
            }
        }
        file.delete();
    }
}
