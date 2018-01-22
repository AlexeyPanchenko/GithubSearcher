package ru.alexeyp.searchrepo.utils.ui;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl", "bind:placeholder"})
    public static void loadImage(ImageView imageView, String url, Drawable placeholder) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(placeholder))
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }
}
