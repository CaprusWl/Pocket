package com.example.pocket.util;

import android.media.MediaPlayer;
import androidx.annotation.Keep;

import java.io.IOException;

public class MediaPlayerHelper {
    private MediaPlayer mMediaPlayer;

    @Keep
    public enum STATUS {
        NOT_INIT, INITIALIZED, START, STOP, RELEASED;
    }

    private STATUS mSTATUS = STATUS.NOT_INIT;

    public interface CallBack {
        void OnMediaCompleteListener();
    }

    public CallBack mCallBack;

    public void initialize(String filePath) {
        try {
            mMediaPlayer = new MediaPlayer();
            if (mCallBack != null) {
                mMediaPlayer.setOnCompletionListener(mp -> {
                    mCallBack.OnMediaCompleteListener();
                    mSTATUS = STATUS.STOP;
                    mMediaPlayer.stop();
                });
            }
            mMediaPlayer.setDataSource(filePath);
            mSTATUS = STATUS.INITIALIZED;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    public void start() {
        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mSTATUS = STATUS.START;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        mSTATUS = STATUS.STOP;
        mMediaPlayer.stop();
    }

    public void release() {
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    public STATUS getSTATUS() {
        return mSTATUS;
    }
}
