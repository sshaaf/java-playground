package org.acme.filters;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;



public class Blur implements Filter {

    @Override
    public Mat process(Mat src) {
        if(src != null) {
            Mat dst = new Mat();
            Mat kernel = new Mat();
            int ind = 0;
            int kernel_size = 3 + 2*( 11 );

            Point anchor = new Point( -1, -1);
            double delta = 0.0;
            int ddepth = -1;

            Mat ones = Mat.ones( kernel_size, kernel_size, CvType.CV_32F );
            Core.multiply(ones, new Scalar(1/(double)(kernel_size*kernel_size)), kernel);
            // Apply filter
            Imgproc.filter2D(src, dst, ddepth , kernel, anchor, delta, Core.BORDER_DEFAULT );

            return dst;
    }
        else throw new IllegalArgumentException("Looking for Mat nothing found!, try passing org.opencv.core.Mat to process");
    }
}
