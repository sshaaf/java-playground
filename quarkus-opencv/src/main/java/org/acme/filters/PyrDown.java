package org.acme.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;



public class PyrDown implements Filter{

    @Override
    public Mat process(Mat src) {
        if(src != null) {
            int MAX_KERNEL_LENGTH = 31;
            Mat dst = new Mat();
            for (int i = 1; i < MAX_KERNEL_LENGTH; i = i + 2) {
                Imgproc.pyrDown(src, dst);
            }
            return dst;
        }
        else throw new IllegalArgumentException("Looking for Mat nothing found!, try passing org.opencv.core.Mat to process");
    }
}
