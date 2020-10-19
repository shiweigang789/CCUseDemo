package com.swg.component_a

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.billy.cc.core.component.CC

/**
 *
 * @ProjectName:    CCDemo
 * @ClassName:      LifecycleFragment
 * @Author:         Owen
 * @CreateDate:     2020/10/19 10:17
 * @UpdateUser:     更新者
 * @Description:     java类作用描述
 */
class LifecycleFragment : Fragment() {

    var index = 1
    private var curIndex = 0
    private var log: TextView? = null
    private var textView: TextView? = null

    init {
        curIndex = index++
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("TestFragment", "TestFragment.onCreate:$curIndex")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (container == null) return null
        val context = container.context
        val scrollView = ScrollView(context)
        val layout = LinearLayout(context)
        scrollView.addView(layout)
        layout.orientation = LinearLayout.VERTICAL
        textView = TextView(context)
        layout.addView(textView)
        textView!!.gravity = Gravity.CENTER
        val cc = CC.obtainBuilder("ComponentB")
            .setActionName("getNetworkData")
            .cancelOnDestroyWith(this)
            .build()
        cc.callAsyncCallbackOnMainThread { cc, result ->
            val text =
                """
                callId=${cc.callId}${JsonFormat.format(result.toString())}
                """.trimIndent()
            Toast.makeText(CC.getApplication(), text, Toast.LENGTH_SHORT).show()
            log(text)
        }
        textView!!.text = getString(R.string.demo_a_life_cycle_fragment_notice, cc.callId)
        log = TextView(context)
        layout.addView(log)
        return scrollView
    }

    private fun log(s: String) {
        if (!TextUtils.isEmpty(s)) {
            log?.text = s
        }
        Log.i("TestFragment", s)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("TestFragment.onDestroyView:$curIndex")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("TestFragment.onDestroy:$curIndex")
    }

    fun addText(text: String) {
        if (!TextUtils.isEmpty(text)) {
            textView?.post {
                textView?.append(
                    """
                    $text
                    """.trimIndent()
                )
            }
        }
    }

}