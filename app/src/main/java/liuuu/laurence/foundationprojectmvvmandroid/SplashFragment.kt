package liuuu.laurence.foundationprojectmvvmandroid

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.hide()

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                findNavController()
                    .navigate(R.id.github,
                        null,
                        NavOptions.Builder()
                            .setPopUpTo(R.id.splash,
                                true).build()
                    )
            }
        }
        timer.start()
    }
}