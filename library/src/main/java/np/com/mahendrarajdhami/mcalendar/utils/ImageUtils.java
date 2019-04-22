package np.com.mahendrarajdhami.mcalendar.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

/**
 * This class is used to load event image in a day cell
 * <p>
 */

public class ImageUtils {

    public static void loadImage(ImageView imageView, Object image) {
        if (image == null) {
            return;
        }

        Drawable drawable = null;
        if (image instanceof Drawable) {
            drawable = (Drawable) image;
        } else if (image instanceof Integer) {
            drawable = ContextCompat.getDrawable(imageView.getContext(), (Integer) image);
        }

        if (drawable == null) {
            return;
        }

        imageView.setImageDrawable(drawable);
    }

    private ImageUtils() {
    }
}
