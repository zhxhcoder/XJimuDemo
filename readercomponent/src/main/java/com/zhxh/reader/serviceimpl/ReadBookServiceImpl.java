package com.zhxh.reader.serviceimpl;

import android.support.v4.app.Fragment;

import com.xlab.componentservice.readerbook.ReadBookService;
import com.zhxh.reader.ReaderFragment;

/**
 * Created by zhxh on 2018/6/15.
 */

public class ReadBookServiceImpl implements ReadBookService {
    @Override
    public Fragment getReadBookFragment() {
        return new ReaderFragment();
    }
}
