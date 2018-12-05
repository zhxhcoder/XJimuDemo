package com.luojilab.share.kotlin

import android.os.Bundle
import com.luojilab.component.componentlib.router.ui.UIRouter
import com.luojilab.component.componentlib.service.JsonService
import com.luojilab.router.facade.annotation.Autowired
import com.luojilab.router.facade.annotation.RouteNode
import com.xlab.componentservice.share.bean.AuthorKt
import com.xlab.core.app.BaseActivity
import kotlinx.android.synthetic.main.kotlin_activity_share.*

/**
 * Created by mrzhang on 2017/12/29.
 */
@RouteNode(path = "/shareMagazine", desc = "分享杂志页面")
class ShareMessageActivity : BaseActivity() {

    override fun setLayout() {
        setContentView(R.layout.kotlin_activity_share)
    }

    @Autowired(name = "bookName")
    @JvmField
    var magazineName: String? = null

    @Autowired
    @JvmField
    var author: AuthorKt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        share_title.text = "Magazine"
        share_tv_tag.setText(magazineName)
        share_tv_author.setText(author?.name ?: "zmq")
        share_tv_county.setText(author?.county ?: "China")

        share_title.setOnClickListener {

            val author = AuthorKt("Barack Obama", 65, "New York")
            UIRouter.getInstance().openUri(this,
                    "DDComp://kotlin/javatest?bookName=NYTIME&author=" + JsonService.Factory.getSingletonImpl().toJsonString(author), null)
        }
    }
}