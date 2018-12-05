package com.zhxh.reader.serviceimpl

import android.support.v4.app.Fragment
import com.xlab.componentservice.readerbook.ReadBookService
import com.zhxh.reader.ReaderFragment

/**
 * Created by zhxh on 2018/2/9.
 */
class ReadBookServiceImplKotlin : ReadBookService {
    override fun getReadBookFragment(): Fragment {
        return ReaderFragment()
    }
}
