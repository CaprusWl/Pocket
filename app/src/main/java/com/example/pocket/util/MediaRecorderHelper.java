package com.example.pocket.util;

import android.media.MediaRecorder;
import androidx.annotation.Keep;

import java.io.File;
import java.io.IOException;

public class MediaRecorderHelper {
    private MediaRecorder mMediaRecorder;

    @Keep
    public enum STATUS {
        NOT_INIT, INITIALIZED, START, STOP, RELEASED;
    }
    private STATUS mSTATUS = STATUS.NOT_INIT;

    public void initialize(String filePath) {
        try {
            File file = new File(filePath);
            File parent = new File(file.getParent());
            if (!parent.exists()) {
                parent.mkdirs();
            }
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.prepare();
            mSTATUS = STATUS.INITIALIZED;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        mSTATUS = STATUS.START;
        mMediaRecorder.start();
    }

    public void stop() {
        mSTATUS = STATUS.STOP;
        mMediaRecorder.stop();
    }

    public void release() {
        mSTATUS = STATUS.RELEASED;
        mMediaRecorder.release();
        mMediaRecorder = null;
    }

    public STATUS getSTATUS() {
        return mSTATUS;
    }
}
