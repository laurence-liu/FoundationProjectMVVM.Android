package liuuu.laurence.foundationprojectmvvmandroid.github

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import liuuu.laurence.foundationprojectmvvmandroid.R

@BindingAdapter("githubUserImage")
fun bindGitHubUserImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Picasso.get().load(imgUrl)
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(imageView)
    }
}