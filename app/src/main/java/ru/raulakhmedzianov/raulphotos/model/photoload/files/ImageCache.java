package ru.raulakhmedzianov.raulphotos.model.photoload.files;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;

public class ImageCache {

    private static final String TAG = "ImageCache";
    private static final String APP_FOLDER = "raulphotos";

    //сохраняем картинку
    public void saveImage(Bitmap bitmap, String login) {

    }

    public File getImageDir() {

    }

    public File getImagePath(String fileName) {
        return new File(Environment.getExternalStorageDirectory() + File.separator + APP_FOLDER + File.separator + fileName + ".png");
    }
}
