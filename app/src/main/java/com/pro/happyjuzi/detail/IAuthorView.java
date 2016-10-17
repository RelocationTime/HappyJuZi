package com.pro.happyjuzi.detail;

import com.pro.happyjuzi.bean.AuthorBeanInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public interface IAuthorView  {

    void showAuthor(List<AuthorBeanInfo.DataBean.ListBean> listBeen);

    void showDataList(AuthorBeanInfo.DataBean.InfoBean dataInfo );

    void showBackground(String imageUrl);
}
