package interview.heruijun.com.androidinterview.activity

import android.os.Bundle
import com.heruijun.baselibrary.activity.BaseActivity
import interview.heruijun.com.androidinterview.R
import kotlinx.android.synthetic.main.activity_next.*
import org.jetbrains.anko.intentFor

class NextActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        jump.setOnClickListener {
            startActivity(intentFor<InterviewActivity>())
        }
    }
}
