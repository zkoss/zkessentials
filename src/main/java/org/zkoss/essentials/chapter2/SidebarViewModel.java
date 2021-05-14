/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.essentials.chapter2;

import org.zkoss.bind.annotation.*;
import org.zkoss.essentials.services.*;
import org.zkoss.zk.ui.Executions;

import java.util.List;

/**MVVM(Model-View-ViewModel)
 * Model负责在执行通常由用户实现的业务逻辑时公开数据。
 * View负责显示ZUL所做的数据。
 * ViewModel包含页面的状态，就像一个特殊的Controller，负责将模型中的数据公开给view，并为View的用户请求提供所需的操作和逻辑。
 *
 *ViewModel是一个View抽象，其中包含View的状态和行为。与MVC中的Controller的最大区别在于，ViewModel不包含对UI组件的任何引用，
 * 并且对View的可视元素一无所知。应该有一个角色来同步数据，并处理View和ViewModel之间的事件和相应。该角色消除了View和ViewModel
 * 之间的依赖性。因此，View和ViewModel之间的清晰区分将ViewModel与View分离开，并使ViewModel更可重用且更抽象。
 *
 * */
public class SidebarViewModel {
/**ViewModel只是一个普通的POJO，不需要拓展任何父类。也不需要事项任何的接口。*/


    //构建数据
    private SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();

    public List<SidebarPage> getSidebarPages() {
        return pageConfig.getPages();
    }

    /*绑定前端方法onClick*/
    @Command                /*@BindingParam("参数")前端绑定方法传参  对象 参数名*/
    public void navigate(@BindingParam("page") SidebarPage page) {
        Executions.getCurrent().sendRedirect(page.getUri());
    }
}
